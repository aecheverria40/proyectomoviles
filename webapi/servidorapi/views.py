from django.shortcuts import render
from django.contrib.auth.models import User, Group
#Importar clase de rest_framework
from rest_framework import viewsets
#Importamos las cases de serializers.py
from servidorapi.serializers import UserSerializer, GroupSerializer

#Cosas a importar
from rest_framework import status, permissions
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
from .models import (Alumno, Boleta, Clase, Coordinador, Docente, Escuela,
Parcial)

#Importar Serializers
from .serializers import (AlumnoSerializer, BoletaSerializer,
ClaseSerializer, CoordinadorSerializer, DocenteSerializer, EscuelaSerializer)

# Create your views here.
class UserViewSet(viewsets.ModelViewSet):
    '''
    Parte de la API que permite a los usuarios ser vistos o editados.
    '''
    queryset = User.objects.all().order_by('-date_joined')
    serializer_class = UserSerializer

class GroupViewSet(viewsets.ModelViewSet):
    '''
    Parte de la API que permite a los grupos ser vistos o editados.
    '''
    queryset = Group.objects.all()
    serializer_class = GroupSerializer

#De aqui para abajo los avances para la diapositiva
@api_view(['GET', 'POST'])
#@permission_classes((permissions.AllowAny,))
def alumno_list(request, format=None):
    '''
    Para listar todos los alumnos o dar de alta alumnos
    '''
    if request.method == 'GET':
        alumno = Alumno.objects.all()
        seralizer = AlumnoSerializer(alumno, many=True)
        return Response(seralizer.data)

    elif request.method == 'POST':
        seralizer = AlumnoSerializer(data=request.data)
        if seralizer.is_valid():
            seralizer.save()
            return Response(seralizer.data, status=status.HTTP_201_CREATED)
        return Response(seralizer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['GET', 'PUT', 'DELETE'])
def alumno_detail(request, pk, format=None):
    '''
    Detalles, actualizar o eliminar alumno
    '''
    try:
        alumno = Alumno.objects.get(pk=pk)
    except Alumno.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = AlumnoSerializer(alumno)
        return Response(serializer.data)

    elif request.method == 'PUT':
        serializer = AlumnoSerializer(alumno, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif request.method == 'DELETE':
        alumno.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)


'''
Coordinador
'''
@api_view(['GET','POST'])
def coordinador_list(request):
    if request.method == 'GET':
        coordinador = Coordinador.objects.all()
        seralizer = CoordinadorSerializer(coordinador, many=True)
        return Response(seralizer.data)

    elif request.method == 'POST':
        seralizer = CoordinadorSerializer(data=request.data)
        if seralizer.is_valid():
            seralizer.save()
            return Response(seralizer.data, status=status.HTTP_201_CREATED)
        return Response(seralizer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['GET', 'PUT', 'DELETE'])
def coordinador_detail(request, pk):
    try:
        coordinador = Coordinador.objects.get(pk=pk)
    except Coordinador.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = CoordinadorSerializer(coordinador)
        return Response(serializer.data)

    elif request.method == 'PUT':
        serializer = CoordinadorSerializer(coordinador, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif request.method == 'DELETE':
        coordinador.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
