from django.contrib import admin
from .models import (Alumno, Boleta, Clase, Coordinador, Docente, Escuela, Parcial)
# Register your models here.
admin.site.register(Alumno)
admin.site.register(Boleta)
admin.site.register(Clase)
admin.site.register(Coordinador)
admin.site.register(Docente)
admin.site.register(Escuela)
admin.site.register(Parcial)
