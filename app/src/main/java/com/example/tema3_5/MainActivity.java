package com.example.tema3_5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu; //Realizamos el import del paquete android.view.Menu para poder instanciar la clase del tipo Menu.
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnLogin = findViewById(R.id.btnLogin); // Botón "Ingresar"
        EditText txtIngresoUsuario = findViewById(R.id.txtIngresoUsuario); // Campo de usuario
        EditText txtIngresoPassword = findViewById(R.id.txtIngresoPassword); // Campo de contraseña
        Button btnSalir = findViewById(R.id.btnSalir); // Botón "Salir"

        btnLogin.setOnClickListener(v -> {
            // Recuperar datos de SharedPreferences
            SharedPreferences preferencias = getSharedPreferences("preferences", Context.MODE_PRIVATE);
            String usuarioGuardado = preferencias.getString("Usuario", null);
            String claveGuardada = preferencias.getString("Clave", null);

            // Obtener datos ingresados por el usuario
            String usuarioIngresado = txtIngresoUsuario.getText().toString();
            String claveIngresada = txtIngresoPassword.getText().toString();

            // Comparar datos
            if (usuarioGuardado != null && claveGuardada != null) {
                if (usuarioGuardado.equals(usuarioIngresado) && claveGuardada.equals(claveIngresada)) {
                    Toast.makeText(MainActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();

                    // Crea un Intent para iniciar RegistrarActivity
                    Intent intent = new Intent(this, HomeActivity.class); // aqui pasamos al home
                    startActivity(intent); // Inicia la actividad
                    Toast.makeText(this, "Abriendo pantalla de home...", Toast.LENGTH_SHORT).show(); // Opcional
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "No hay usuarios registrados", Toast.LENGTH_SHORT).show();
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);

        //Snackbar.make(layoud, "barrar activada", Snackbar.LENGTH_LONG).show();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId(); // Get the item ID once

        if (itemId == R.id.mnLogin) {
            //Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
            return true; // Indicate that the event was handled
        } else if (itemId == R.id.mnRegistro) {

            // Crea un Intent para iniciar RegistrarActivity
            Intent intent = new Intent(this, RegistarActivity.class); // Asegúrate de que RegistrarActivity exista
            startActivity(intent); // Inicia la actividad
            Toast.makeText(this, "Abriendo pantalla de registro...", Toast.LENGTH_SHORT).show(); // Opcional
            return true; // Indica que el evento fue manejado

        }else if (itemId == R.id.mnSalir) {

            finishAffinity();
            return true; // Indica que el evento fue manejado

        } else {
            return super.onOptionsItemSelected(item); // Default handling
        }
    }
}