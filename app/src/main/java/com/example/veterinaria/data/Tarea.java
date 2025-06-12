package com.example.veterinaria.data;

        import androidx.room.Entity;
        import androidx.room.PrimaryKey;
        import androidx.room.ColumnInfo;

        @Entity
        public class Tarea {
            @PrimaryKey(autoGenerate = true)
            public int id;

            @ColumnInfo(name = "nombre")
            public String nombre;

            @ColumnInfo(name = "descripcion")
            public String descripcion;

            @ColumnInfo(name = "fecha")
            public String fecha;

            @ColumnInfo(name = "hora")
            public String hora;

            public Tarea(String nombre, String descripcion, String fecha, String hora) {
                this.nombre = nombre;
                this.descripcion = descripcion;
                this.fecha = fecha;
                this.hora = hora;
            }
        }