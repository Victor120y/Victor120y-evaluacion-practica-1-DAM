package com.example.veterinaria.data;

    import android.content.Context;

    import androidx.room.Database;
    import androidx.room.Room;
    import androidx.room.RoomDatabase;

    import com.example.veterinaria.dao.PacienteDAO;
    import com.example.veterinaria.dao.TareaDAO;
    import com.example.veterinaria.dao.UsuarioDAO;
    import com.example.veterinaria.fragment_paciente;

@Database(entities = {Tarea.class, Usuario.class, Paciente.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    private static AppDB instancia;
    public abstract TareaDAO tareaDAO();
    public abstract UsuarioDAO usuarioDAO();

    public abstract PacienteDAO pacienteDAO();

    public static synchronized AppDB getInstance(Context context) {
        if (instancia == null) {
            instancia = Room.databaseBuilder(context.getApplicationContext(),
                            AppDB.class, "veterinaria.db")
                    .build();
        }
        return instancia;
    }
}