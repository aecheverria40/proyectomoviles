from django.shortcuts import render
from django.contrib.auth.models import User, Group
#Importar clase de rest_framework
from rest_framework import viewsets
#Importamos las cases de serializers.py
from servidorapi.serializers import UserSerializer, GroupSerializer

#Cosas a importar
from rest_framework import status
from rest_framework.decorators import api_view
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
def alumno_list(request):
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
def alumno_detail(request, pk):
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
Coordinadores
'''
@api_view(['GET','POST'])
def coordinadores_list(request):
    if request.method == 'GET':
        pass

    elif request.method == 'POST':
        pass
    pass

'''
https://www.django-rest-framework.org/tutorial/2-requests-and-responses/
https://www.django-rest-framework.org/tutorial/1-serialization/
https://www.django-rest-framework.org/api-guide/viewsets/
https://docs.google.com/document/d/1ToFBBf13G12IY5y3R74MaA0LBV-lJDt5TfqiAJQrruw/edit
https://www.django-rest-framework.org/tutorial/quickstart/
'''
