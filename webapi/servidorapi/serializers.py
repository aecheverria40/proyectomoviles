#Importar modelos de la BD
from django.contrib.auth.models import User, Group
from .models import Alumno, Boleta, Clase, Coordinador, Docente, Escuela, Parcial
from django.contrib.auth.models import User
#Importar la clase serializers para trabajar con la API
from rest_framework import serializers
from rest_framework.decorators import api_view, permission_classes
from django.contrib.auth import get_user_model
from django.db.models import Q
from django import forms

#Se utilizan relaciones Hyperlinked pero tambien se puede usar la llave primaria
class UserSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = User
        fields = ('url', 'username', 'email', 'groups')

class GroupSerializer(serializers.HyperlinkedModelSerializer):
    class Meta:
        model = Group
        fields = ('url', 'name')

#De aqui para abajo los avances para la diapositiva
User = get_user_model()

#Creacion de usuario
class UserCreateSerializer(serializers.ModelSerializer):
    #Sobre escribir el campo de email para que sea requerido
    email = serializers.EmailField(label='Email address')
    #Pido un segundo email para validar
    email2 = serializers.EmailField(label='Confirm Email')
    class Meta:
        model = User
        fields = ['username', 'email', 'email2', 'password']
        extra_kwargs = {"password":
                            {"write_only":True}
                        }

    #Valida que el usurio no exista
    def validate(self, data):
        email = data['email']
        user_qs = User.objects.filter(email=email)
        if user_qs.exists():
            raise forms.ValidationError('This user has already registered.')
        return data

    #Validar que el correo 1 sea identico al segundo
    def validate_email1(self, value):
        #Obtener los valores
        data = self.get_initial()
        email1 = data.get("email2")
        email2 = value
        #Verificar que el email1 sea igual a email2
        if email1 != email2:
            raise forms.ValidatinError("Emails must match.")
            print(data)
        return value

    #Valida que el segundo sea identico al primero
    def validate_email2(self, value):
        #Obtener los valores
        data = self.get_initial()
        email1 = data.get("email")
        email2 = value
        #Verificar que el email2 sea igual a email1
        if email1 != email2:
            raise forms.ValidationError("Emails must match.")
        return value

    #POST Crear un usuario nuevo
    def create(self, validated_data):
        print(validated_data)
        username = validated_data['username']
        email = validated_data['email']
        password = validated_data['password']
        user_obj = User(
                username = username,
                email = email
            )
        user_obj.set_password(password)
        user_obj.save()
        return validated_data


    # def create(self, validated_data):
    #     user = super(UserApiSerializer, self).create(validated_data)
    #     coordinador = super(CoordinadorSerializer, self).create(validated_data)
    #     user.set_password(validated_data['password'])
    #     user.save()
    #     coordinador.IdCoordinador = user.id
    #     coordinador.save()
    #     return user

#Logueo de usuario
class UserLoginSerializer(serializers.ModelSerializer):
    token = serializers.CharField(allow_blank=True, read_only=True)
    username = serializers.CharField(required=False, allow_blank=True)
    email = serializers.EmailField(label='Email address', required=False, allow_blank=True)
    #Sobre escribir el campo de email para que sea requerido
    # email = serializers.EmailField(label='Email address')
    class Meta:
        model = User
        fields = ['username', 'email', 'password', 'token']
        extra_kwargs = {"password":
                            {"write_only":True}
                        }
    def validate(self, data):
        user_obj = None
        email = data.get("email", None)
        username = data.get("username", None)
        password = data["password"]
        if not email and not username:
            raise forms.ValidationError("A username or email is required to login.")

        user = User.objects.filter(
                Q(email=email) |
                Q(username=username)
            ).distinct()
        #Ignorar los usuarios que no tengan correo
        user = user.exclude(email__isnull=True).exclude(email__iexact='')

        if user.exists() and user.count() == 1:
            user_obj = user.first()
        else:
            raise froms.ValidationError("This username/email is not valid.")

        if user_obj:
            if not user_obj.check_password(password):
                raise forms.ValidationError("Incorrect credentials please try again.")

        data["token"] = "SOME RANDOM TOKEN"

        return data


# De aqui para abajo los cools que si funcionan

#Serializador para los nombres de usuario
class UsuariosSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('username', 'email')


class CoordinadorSerializer(serializers.ModelSerializer):
    user = UsuariosSerializer(read_only=True)
    class Meta:
        model = Coordinador
        fields = ('__all__')

class EscuelaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Escuela
        fields = ('clave', 'direccion', 'telefono', 'director', 'coordinador')

class DocenteSerializer(serializers.ModelSerializer):
    user = UsuariosSerializer(read_only=True)
    class Meta:
        model = Docente
        fields = ('__all__')

class ClaseSerializer(serializers.ModelSerializer):
    class Meta:
        model = Clase
        fields = ('IdClase', 'docenteImpartiendo', 'escuelaImparte', 'Materia',
        'Horario')

class AlumnoSerializer(serializers.ModelSerializer):
    class Meta:
        model = Alumno
        fields = ('IdAlumno', 'apellidoPaternoAlumno', 'apellidoMaternoAlumno',
        'nombreAlumno', 'direccionAlumno', 'telefonoAlumno', 'emailAlumno',
        'clases')

class BoletaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Boleta
        fields = ('id', 'perteneceAlumno', 'perteneceClase')

class ParcialSerializer(serializers.ModelSerializer):
    class Meta:
        model = Parcial
        fields = ('id', 'boletaPertenece', 'calificacion', 'faltas', 'comentarios')
