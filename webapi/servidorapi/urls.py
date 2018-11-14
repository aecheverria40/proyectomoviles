from django.urls import path
from servidorapi import views
from rest_framework.urlpatterns import format_suffix_patterns

urlpatterns = [
    path('alumno/', views.alumno_list),
    path('alumno/<int:pk>/', views.alumno_detail),
    path('coordinador/', views.coordinador_list),
    path('coordinador/<int:pk>', views.coordinador_detail),
]

urlpatterns = format_suffix_patterns(urlpatterns)
