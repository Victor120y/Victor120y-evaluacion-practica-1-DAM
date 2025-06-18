package com.example.veterinaria.data;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Cita {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String nombreDueno;
    public String nombrePerro;
    public String telefono;
    public String fecha;
    public String hora;
    public String motivo;
}