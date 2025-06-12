package com.example.veterinaria.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Update;

import com.example.veterinaria.data.Tarea;

import java.util.List;

@Dao
public interface TareaDAO {

    @Insert
    void crear(Tarea tarea);

    @Update
    void actualizar(Tarea tarea);

    @Delete
    void eliminar(Tarea tarea);

    @Query("SELECT * FROM Tarea ")
    List<Tarea> obtenerTodasLasTareas();

   @Query("SELECT * FROM Tarea WHERE descripcion LIKE '%' || :desc || '%'")
    List<Tarea> buscarTareasPorDescripcion(String desc);

    @Query("SELECT * FROM Tarea WHERE nombre = :nombre AND descripcion = :descripcion")
    Tarea buscarTareaPorNombreYDescripcion(String nombre, String descripcion);
}
