from django.urls import path
from servidorapi import views
from rest_framework.urlpatterns import format_suffix_patterns

urlpatterns = [
    path('alumno/', views.alumno_list),
    path('alumno/<int:pk>/', views.alumno_detail),
    path('coordinador/', views.coordinador_list),
    path('coordinador/<int:pk>', views.coordinador_detail),
	path('escuela/', views.escuela_list),
    path('escuela/<int:pk>', views.escuela_detail),
	path('docente/', views.docente_list),
    path('docente/<int:pk>', views.docente_detail),
	path('boleta/', views.boleta_list),
    path('boleta/<int:pk>', views.boleta_detail),
	path('clase/', views.clase_list),
    path('clase/<int:pk>', views.clase_detail),
	path('parcial/', views.parcial_list),
    path('parcial/<int:pk>', views.parcial_detail),

    #Prueba
    path('coordinadores/', views.user_view),
    path('register/', views.UserCreateAPI.as_view(), name='register')
]

urlpatterns = format_suffix_patterns(urlpatterns)
