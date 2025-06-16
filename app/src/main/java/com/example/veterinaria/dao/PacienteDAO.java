package com.example.veterinaria.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Delete;
import androidx.room.Update;

import com.example.veterinaria.data.Paciente;

import java.util.List;
@Dao
public interface PacienteDAO {
    @Insert
    void insertar(Paciente paciente);

    @Query("SELECT * FROM Paciente WHERE nombre = :nombre")
    Paciente buscarPorNombre(String nombre);

    @Query("SELECT * FROM Paciente")
    List<Paciente> obtenerTodos();

    @Query("SELECT * FROM Paciente WHERE nombre LIKE '%' || :query || '%' OR encargado LIKE '%' || :query || '%'")
    List<Paciente> buscarPorNombreODueno(String query);

    @Update
    void actualizar(Paciente paciente);

    @Delete
    void eliminar(Paciente paciente);


}

