from django.shortcuts import render
from django.contrib.auth.models import User, Group
#Importar clase de rest_framework
from rest_framework import viewsets
#Importamos las cases de serializers.py
from servidorapi.serializers import UserSerializer, GroupSerializer, UserApiSerializer

#Cosas a importar
from rest_framework import status, permissions
from rest_framework.decorators import api_view, permission_classes
from rest_framework.response import Response
from .models import (Alumno, Boleta, Clase, Coordinador, Docente, Escuela,
Parcial)

#Importar Serializers
from .serializers import (AlumnoSerializer, BoletaSerializer,
ClaseSerializer, CoordinadorSerializer, DocenteSerializer, EscuelaSerializer)

from rest_framework.generics import CreateAPIView, ListAPIView
from django.contrib.auth import get_user_model

User = get_user_model()

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

class UserCreateAPI(CreateAPIView):
    serializer_class = UserApiSerializer
    queryset = User.objects.all()
        
                       

# Este era de prueba para crear nuevos usuarios pero se uso CreateAPIView
@api_view(['GET','POST'])
def user_view(request, format=None):
    if request.method == 'GET':
        user = User.objects.all()
        seralizer = UserApiSerializer(user, many=True)
        return Response(seralizer.data)

    elif request.method == 'POST':
        seralizer = UserApiSerializer(data=request.data)
        if seralizer.is_valid():
            seralizer.save()
            # user.refresh_from_db()
            # user.coordinador.apellidoMaternoCoordinador = "Mario"
            # user.coordinador.nombreCoordinador = "Mario"
            # user.coordinador.direccionCoordinador = "Mario"
            # user.coordinador.telefonoCoordinador = "6641234567"
            # coordinador.IdCoordinador = user.id
            # user.first_name = seralizer.coordinador.nombreCoordinador
            # user.last_name = seralizer.coordinador.apellidoPaternoCoordinador
            # user.coordinador.email_Coordinador = user.email
            # user.set_password(validated_data['password'])
            # user.save()
            return Response(seralizer.data, status=status.HTTP_201_CREATED)
        return Response(seralizer.errors, status=status.HTTP_400_BAD_REQUEST)


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

'''Vistas Brenda'''
'''
Escuela
'''
@api_view(['GET','POST'])
def escuela_list(request):
    if request.method == 'GET':
        escuela = Escuela.objects.all()
        seralizer = EscuelaSerializer(escuela, many=True)
        return Response(seralizer.data)

    elif request.method == 'POST':
        seralizer = EscuelaSerializer(data=request.data)
        if seralizer.is_valid():
            seralizer.save()
            return Response(seralizer.data, status=status.HTTP_201_CREATED)
        return Response(seralizer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['GET', 'PUT', 'DELETE'])
def escuela_detail(request, pk):
    try:
        escuela = Escuela.objects.get(pk=pk)
    except Escuela.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = EscuelaSerializer(escuela)
        return Response(serializer.data)

    elif request.method == 'PUT':
        serializer = EscuelaSerializer(escuela, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif request.method == 'DELETE':
        escuela.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)

'''
Docente
'''
@api_view(['GET','POST'])
def docente_list(request):
    if request.method == 'GET':
        docente = Docente.objects.all()
        seralizer = DocenteSerializer(docente, many=True)
        return Response(seralizer.data)

    elif request.method == 'POST':
        seralizer = DocenteSerializer(data=request.data)
        if seralizer.is_valid():
            seralizer.save()
            return Response(seralizer.data, status=status.HTTP_201_CREATED)
        return Response(seralizer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['GET', 'PUT', 'DELETE'])
def docente_detail(request, pk):
    try:
        docente = Docente.objects.get(pk=pk)
    except Docente.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = DocenteSerializer(docente)
        return Response(serializer.data)

    elif request.method == 'PUT':
        serializer = DocenteSerializer(docente, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif request.method == 'DELETE':
        docente.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
		
'''
Boleta
'''
@api_view(['GET','POST'])
def boleta_list(request):
    if request.method == 'GET':
        boleta = Boleta.objects.all()
        seralizer = BoletaSerializer(boleta, many=True)
        return Response(seralizer.data)

    elif request.method == 'POST':
        seralizer = BoletaSerializer(data=request.data)
        if seralizer.is_valid():
            seralizer.save()
            return Response(seralizer.data, status=status.HTTP_201_CREATED)
        return Response(seralizer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['GET', 'PUT', 'DELETE'])
def boleta_detail(request, pk):
    try:
        boleta = Boleta.objects.get(pk=pk)
    except Boleta.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = BoletaSerializer(boleta)
        return Response(serializer.data)

    elif request.method == 'PUT':
        serializer = BoletaSerializer(boleta, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif request.method == 'DELETE':
        boleta.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
		
'''
Clase
'''
@api_view(['GET','POST'])
def clase_list(request):
    if request.method == 'GET':
        clase = Clase.objects.all()
        seralizer = ClaseSerializer(clase, many=True)
        return Response(seralizer.data)

    elif request.method == 'POST':
        seralizer = ClaseSerializer(data=request.data)
        if seralizer.is_valid():
            seralizer.save()
            return Response(seralizer.data, status=status.HTTP_201_CREATED)
        return Response(seralizer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['GET', 'PUT', 'DELETE'])
def clase_detail(request, pk):
    try:
        clase = Clase.objects.get(pk=pk)
    except Clase.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = ClaseSerializer(Clase)
        return Response(serializer.data)

    elif request.method == 'PUT':
        serializer = ClaseSerializer(clase, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif request.method == 'DELETE':
        clase.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)
		
'''
Parcial
'''
@api_view(['GET','POST'])
def parcial_list(request):
    if request.method == 'GET':
        parcial = Parcial.objects.all()
        seralizer = ParcialSerializer(parcial, many=True)
        return Response(seralizer.data)

    elif request.method == 'POST':
        seralizer = ParcialSerializer(data=request.data)
        if seralizer.is_valid():
            seralizer.save()
            return Response(seralizer.data, status=status.HTTP_201_CREATED)
        return Response(seralizer.errors, status=status.HTTP_400_BAD_REQUEST)

@api_view(['GET', 'PUT', 'DELETE'])
def parcial_detail(request, pk):
    try:
        parcial = Parcial.objects.get(pk=pk)
    except Parcial.DoesNotExist:
        return Response(status=status.HTTP_404_NOT_FOUND)

    if request.method == 'GET':
        serializer = ParcialSerializer(Parcial)
        return Response(serializer.data)

    elif request.method == 'PUT':
        serializer = ParcialSerializer(parcial, data=request.data)
        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data)
        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    elif request.method == 'DELETE':
        parcial.delete()
        return Response(status=status.HTTP_204_NO_CONTENT)