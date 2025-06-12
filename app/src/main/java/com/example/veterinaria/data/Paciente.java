package com.example.veterinaria.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity
public class Paciente {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "especie")
    public String especie;

    @ColumnInfo(name = "raza")
    public String raza;

    @ColumnInfo(name = "edad")
    public int edad;

    @ColumnInfo(name = "encargado")
    public String encargado;

    @ColumnInfo(name = "telefono")
    public String telefono;

    public Paciente(String nombre, String especie, String raza, int edad, String encargado, String telefono) {
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.encargado = encargado;
        this.telefono = telefono;
    }
}