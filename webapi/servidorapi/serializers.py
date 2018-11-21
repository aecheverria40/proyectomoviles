#Importar modelos de la BD
from django.contrib.auth.models import User, Group
from .models import Alumno, Boleta, Clase, Coordinador, Docente, Escuela, Parcial
from django.contrib.auth.models import User
#Importar la clase serializers para trabajar con la API
from rest_framework import serializers
from rest_framework.decorators import api_view, permission_classes
from django.contrib.auth import get_user_model

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

class UserApiSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ['username', 'password', 'email',]
        extra_kwargs = {"password":
                            {"write_only":True}
                        }

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


# De aqui para abajo los cools que si funcionan

class CoordinadorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Coordinador
        fields = ('id','user','apellidoMaternoCoordinador',
        'nombreCoordinador', 'direccionCoordinador', 'telefonoCoordinador',
        'email_Coordinador')

class EscuelaSerializer(serializers.ModelSerializer):
    class Meta:
        model = Escuela
        fields = ('clave', 'direccion', 'telefono', 'director', 'coordinador')

class DocenteSerializer(serializers.ModelSerializer):
    class Meta:
        model = Docente
        fields = ('id', 'IdDocente', 'apellidoPaternoDocente', 'apellidoMaterno',
        'nombreDocente', 'direccionDocente', 'telefonoDocente', 'emailDocente',
        'escuelas')

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
