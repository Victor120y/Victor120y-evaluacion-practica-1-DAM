package com.example.veterinaria.data;

    import android.content.Context;
    import androidx.annotation.NonNull;
    import androidx.room.Database;
    import androidx.room.InvalidationTracker;
    import androidx.room.Room;
    import androidx.room.RoomDatabase;

    import com.example.veterinaria.dao.TareaDAO;

@Database(entities = {Tarea.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
       private static AppDB instancia;
       public abstract TareaDAO tareaDAO();

         public static synchronized AppDB getInstance(Context context) {
             if (instancia == null) {
                 instancia = Room.databaseBuilder(context.getApplicationContext(),
                            AppDB.class, "veterinaria.db")
                         .build();
             }
                return instancia;
         }
   }