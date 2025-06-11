package com.example.veterinaria.dao;

import androidx.room.PrimaryKey;

public interface TareaDAO {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @columnInfo(name = "nombre")
    public String nombre;
    @columnInfo(name = "descripcion")
    public String descripcion;
    @columnInfo(name = "fecha")
    public String fecha;
    @columnInfo(name = "hora")
    public String hora;

    public Tarea(String nombre, String descripcion, String fecha, String hora) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
    }
}
