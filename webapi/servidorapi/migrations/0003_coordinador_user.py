# Generated by Django 2.1.3 on 2018-11-19 23:13

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
        ('servidorapi', '0002_remove_coordinador_idcoordinador'),
    ]

    operations = [
        migrations.AddField(
            model_name='coordinador',
            name='user',
            field=models.OneToOneField(null=True, on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL),
        ),
    ]