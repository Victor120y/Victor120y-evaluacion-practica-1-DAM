package com.example.veterinaria.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.veterinaria.data.Usuario;
import java.util.List;

@Dao
public interface UsuarioDAO {
    @Insert
    void insertar(Usuario usuario);

    @Query("SELECT * FROM Usuario WHERE nombre = :nombre")
    Usuario buscarPorNombre(String nombre);

    @Query("SELECT * FROM Usuario")
    List<Usuario> obtenerTodos();
}