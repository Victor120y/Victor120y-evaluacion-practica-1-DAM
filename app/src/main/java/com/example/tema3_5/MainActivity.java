package com.example.tema3_5;

import android.content.Intent;
import android.os.Bundle;

import android.view.Menu; //Realizamos el import del paquete android.view.Menu para poder instanciar la clase del tipo Menu.
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        //getMenuInflater().inflate(R.menu.menu_main, menu);

        //Snackbar.make(layoud, "barrar activada", Snackbar.LENGTH_LONG).show();
        return super.onCreateOptionsMenu(menu);
    }

    /*
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnLogin:
                //Snackbar.make(layoud, "Configuracion activada", Snackbar.LENGTH_LONG).show();
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnRegistro:
                //Snackbar.make(layoud, "Acerca de activada", Snackbar.LENGTH_LONG).show();
                Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnInicio:
                //Snackbar.make(layoud, "Salir activada", Snackbar.LENGTH_LONG).show();
                finish();
                Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    */

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


            //Toast.makeText(this, "Registro", Toast.LENGTH_SHORT).show();
            //return true; // Indicate that the event was handled
        } else if (itemId == R.id.mnInicio) {
            Toast.makeText(this, "Inicio", Toast.LENGTH_SHORT).show();
            return true; // Indicate that the event was handled
        } else {
            return super.onOptionsItemSelected(item); // Default handling
        }
    }
}