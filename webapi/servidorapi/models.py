from django.db import models
from django.contrib.auth.models import User
from django.utils.translation import ugettext_lazy as _
from django.db.models.signals import post_save
from django.dispatch import receiver

# Create your models here.
class Coordinador(models.Model):
    IdCoordinador = models.OneToOneField(User, on_delete=models.CASCADE)
    apellidoPaternoCoordinador = models.CharField(max_length=100)
    apellidoMaternoCoordinador = models.CharField(max_length=100)
    nombreCoordinador = models.CharField(max_length=100)
    direccionCoordinador = models.CharField(max_length=100)
    telefonoCoordinador = models.CharField(max_length=20)
    email_Coordinador = models.CharField(max_length=50)

    class Meta:
        verbose_name = _('Coordinador')
        verbose_name_plural = _('Coordinadores')


    def __str__(self):
        return str(self.IdCoordinador)

    def getNombreCompleto(self):
        return self.nombreCoordinador + ' ' + self.apellidoPaternoCoordinador + ' ' + self.apellidoMaternoCoordinador

    def getDireccion(self):
        return self.direccionCoordinador

    def getTelefono(self):
        return self.telefonoCoordinador

    def getEmail(self):
        return self.email_Coordinador


#Abiel
@receiver(post_save, sender=User)
def update_user_profile(sender, instance, created, **kwargs):
    if created:
        Coordinador.objects.create(user=instance)
    instance.coordinador.save()

#Max Gordon
# def crate_coordinador(sender, **kwargs):
#     if kwargs['created']:
#         user_profile = Coordinador.objects.create(user=kwargs['instance'])

# post_save.connect(crate_coordinador, sender=User)

class Escuela(models.Model):
    clave = models.CharField(max_length=100, primary_key=True)
    direccion = models.CharField(max_length=100)
    telefono = models.CharField(max_length=20)
    director = models.CharField(max_length=100)
    coordinador = models.ForeignKey(Coordinador, on_delete=models.CASCADE)

    def __str__(self):
        return self.clave

class Docente(models.Model):
    IdDocente = models.OneToOneField(User, on_delete=models.CASCADE)
    apellidoPaternoDocente = models.CharField(max_length=100)
    apellidoMaterno = models.CharField(max_length=100)
    nombreDocente = models.CharField(max_length=100)
    direccionDocente = models.CharField(max_length=100)
    telefonoDocente = models.CharField(max_length=20)
    emailDocente = models.CharField(max_length=50)
    escuelas = models.ManyToManyField(Escuela)

    class Meta:
        verbose_name = _('Docente')
        verbose_name_plural = _('Docentes')


    def __str__(self):
        return str(self.IdDocente)

    def getNombreCompleto(self):
        return self.nombreDocente + ' ' + self.apellidoPaternoDocente + ' ' + self.apellidoMaterno

    def getDireccion(self):
        return self.direccionDocente

    def getTelefono(self):
        return self.telefonoDocente

    def getEmail(self):
        return self.emailDocente

class Clase(models.Model):
    IdClase = models.CharField(max_length=100, primary_key=True)
    docenteImpartiendo = models.ForeignKey(Docente, on_delete=models.CASCADE)
    escuelaImparte = models.ForeignKey(Escuela, on_delete=models.CASCADE)
    Materia = models.CharField(max_length=100)
    Horario = models.CharField(max_length=100)

    def __str__(self):
        return self.IdClase + ' ' + self.Materia

class Alumno(models.Model):
    IdAlumno = models.CharField(max_length=100, primary_key=True)
    apellidoPaternoAlumno = models.CharField(max_length=100)
    apellidoMaternoAlumno = models.CharField(max_length=100)
    nombreAlumno = models.CharField(max_length=100)
    direccionAlumno = models.CharField(max_length=100)
    telefonoAlumno = models.CharField(max_length=20)
    emailAlumno = models.CharField(max_length=50)
    clases = models.ManyToManyField(Clase)

    def __str__(self):
        return self.IdAlumno + ' ' + self.nombreAlumno

class Boleta(models.Model):
    perteneceAlumno = models.ForeignKey(Alumno, on_delete=models.CASCADE)
    perteneceClase = models.ForeignKey(Clase, on_delete=models.CASCADE)

    def __str__(self):
        return self.Folio

class Parcial(models.Model):
    boletaPertenece = models.ForeignKey(Boleta, on_delete=models.CASCADE)
    calificacion = models.SmallIntegerField()
    faltas = models.SmallIntegerField()
    comentarios = models.CharField(max_length=100)
