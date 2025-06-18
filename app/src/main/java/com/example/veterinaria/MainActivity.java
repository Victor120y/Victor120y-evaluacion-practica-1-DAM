package com.example.veterinaria;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.veterinaria.data.AppDB;
import com.example.veterinaria.data.Usuario;


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
            String usuarioIngresado = txtIngresoUsuario.getText().toString();
            String claveIngresada = txtIngresoPassword.getText().toString();

            new Thread(() -> {
                AppDB db = AppDB.getInstance(getApplicationContext());
                Usuario usuario = db.usuarioDAO().buscarPorNombre(usuarioIngresado);

                runOnUiThread(() -> {
                    if (usuario != null) {
                        if (usuario.password.equals(claveIngresada)) {
                            Toast.makeText(MainActivity.this, "Login exitoso", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(this, HomeActivity.class);
                            startActivity(intent);
                            Toast.makeText(this, "Abriendo pantalla de home...", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "No hay usuarios registrados", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
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
        int itemId = item.getItemId();

        if (itemId == R.id.action_toggle_theme) {
            int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
            if (currentNightMode == Configuration.UI_MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            return true;
        } else if (itemId == R.id.mnLogin) {
            return true;
        } else if (itemId == R.id.mnRegistro) {
            Intent intent = new Intent(this, RegistarActivity.class);
            startActivity(intent);
            Toast.makeText(this, "Abriendo pantalla de registro...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.mnSalir) {
            finishAffinity();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}