#Importar modelos de la BD
from django.contrib.auth.models import User, Group
from .models import Alumno, Boleta, Clase, Coordinador, Docente, Escuela, Parcial
#Importar la clase serializers para trabajar con la API
from rest_framework import serializers

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
class CoordinadorSerializer(serializers.ModelSerializer):
    class Meta:
        model = Coordinador
        fields = ('id','IdCoordinador', 'apellidoPaternoCoordinador', 'apellidoMaternoCoordinador',
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
