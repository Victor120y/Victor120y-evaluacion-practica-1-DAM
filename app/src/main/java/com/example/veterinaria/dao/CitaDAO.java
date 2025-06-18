package com.example.veterinaria.dao;

import androidx.room.Dao;
import androidx.room.Insert;

import com.example.veterinaria.data.Cita;

@Dao
public interface CitaDAO {
    @Insert
    void insertarCita(Cita cita);
}

