# Generated by Django 3.0.5 on 2020-05-01 05:06

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        ('academic', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Transcript',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('added_at', models.DateField(auto_now_add=True)),
                ('reason', models.TextField()),
                ('marksheet12', models.ImageField(upload_to='static/Trancript/')),
                ('marksheet34', models.ImageField(upload_to='static/Trancript/')),
                ('marksheet56', models.ImageField(upload_to='static/Trancript/')),
                ('marksheet78', models.ImageField(blank=True, null=True, upload_to='static/Trancript/')),
                ('feerecipt', models.ImageField(upload_to='static/Trancript/')),
                ('application', models.ImageField(upload_to='static/Trancript/')),
                ('status', models.BooleanField(default=False)),
                ('student', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='academic.student')),
            ],
        ),
    ]
