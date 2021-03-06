# Generated by Django 3.0.5 on 2020-05-01 05:06

from django.conf import settings
from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    initial = True

    dependencies = [
        migrations.swappable_dependency(settings.AUTH_USER_MODEL),
    ]

    operations = [
        migrations.CreateModel(
            name='klass',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('classid', models.CharField(max_length=16, unique=True)),
                ('classname', models.CharField(max_length=250)),
                ('startingdate', models.DateField()),
                ('endingdate', models.DateField()),
            ],
        ),
        migrations.CreateModel(
            name='studenteditprofilerequest',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('clgregisterid', models.CharField(max_length=16)),
                ('roll_no', models.IntegerField()),
                ('Department', models.CharField(blank=True, max_length=20, null=True)),
                ('div', models.CharField(max_length=10)),
                ('phone_no', models.IntegerField()),
                ('email_id', models.EmailField(max_length=254)),
                ('DOB', models.DateField()),
                ('address', models.CharField(max_length=250)),
                ('city', models.CharField(max_length=16)),
                ('pincode', models.IntegerField()),
                ('enrolledclass', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='academic.klass')),
                ('user', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
        migrations.CreateModel(
            name='student',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('Department', models.CharField(blank=True, max_length=20, null=True)),
                ('clgregisterid', models.CharField(max_length=16)),
                ('roll_no', models.IntegerField()),
                ('div', models.CharField(max_length=10)),
                ('phone_no', models.IntegerField()),
                ('email_id', models.EmailField(max_length=254)),
                ('DOB', models.DateField()),
                ('address', models.CharField(max_length=250)),
                ('city', models.CharField(max_length=16)),
                ('pincode', models.IntegerField()),
                ('enrolledclass', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to='academic.klass')),
                ('user', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
        migrations.CreateModel(
            name='staffprofileeditrequest',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('clgregisterid', models.CharField(max_length=16)),
                ('designation', models.CharField(max_length=16)),
                ('department', models.CharField(max_length=16)),
                ('phone_no', models.IntegerField()),
                ('email_id', models.EmailField(max_length=254)),
                ('DOB', models.DateField()),
                ('address', models.CharField(max_length=250)),
                ('city', models.CharField(max_length=16)),
                ('pincode', models.IntegerField()),
                ('user', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
        migrations.CreateModel(
            name='ktclass',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('classname', models.CharField(max_length=250, unique=True)),
                ('classid', models.CharField(max_length=16)),
                ('student', models.ManyToManyField(to='academic.student')),
            ],
        ),
        migrations.CreateModel(
            name='collegestaff',
            fields=[
                ('id', models.AutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('clgregisterid', models.CharField(max_length=16)),
                ('designation', models.CharField(max_length=16)),
                ('department', models.CharField(max_length=16)),
                ('phone_no', models.IntegerField()),
                ('email_id', models.EmailField(max_length=254)),
                ('DOB', models.DateField()),
                ('address', models.CharField(max_length=250)),
                ('city', models.CharField(max_length=16)),
                ('pincode', models.IntegerField()),
                ('user', models.OneToOneField(on_delete=django.db.models.deletion.CASCADE, to=settings.AUTH_USER_MODEL)),
            ],
        ),
    ]
