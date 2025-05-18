package com.example.tema3_5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registar);

        // Configurar el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.register_activity_title); // Título desde strings.xml
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnGuardar = findViewById(R.id.btnGuardar);
        Button btnRegresar = findViewById(R.id.btnRegresar);
        EditText txtIngresoUsuario = findViewById(R.id.txtIngresoUsuario);
        EditText txtIngresoEmail = findViewById(R.id.txtIngresoEmail);
        EditText txtIngresoPassword = findViewById(R.id.txtIngresoPassword);
        EditText txtIngresoPassword2 = findViewById(R.id.txtIngresoPassword2);
       /* btnGuardar.setOnClickListener(new View.OnClickListener() {
            // @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                SharedPreferences usuarios = getSharedPreferences("preferences", Context.MODE_PRIVATE);

                String Usuario = txtIngresoUsuario.getText().toString();
                String Email = txtIngresoEmail.getText().toString();
                String Clave = txtIngresoPassword.getText().toString();
                String Clave1 = txtIngresoPassword2.getText().toString();

                SharedPreferences.Editor editorpref = usuarios.edit();
                editorpref.putString("Usuario", Usuario); // Clave única
                editorpref.putString("Email", Email);     // Clave única
                editorpref.putString("Clave", Clave);     // Clave única
                editorpref.putString("Clave1", Clave1);   // Clave única
                editorpref.apply();

                Toast.makeText(RegistarActivity.this, "Usuario guardado con éxito", Toast.LENGTH_LONG).show();
            }
        });*/

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Usuario = txtIngresoUsuario.getText().toString().trim();
                String Email = txtIngresoEmail.getText().toString().trim();
                String Clave = txtIngresoPassword.getText().toString();
                String Clave1 = txtIngresoPassword2.getText().toString();

                // Validación del nombre de usuario
                if (Usuario.length() < 3) {
                    Toast.makeText(RegistarActivity.this, "El nombre de usuario debe tener al menos 3 caracteres", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validación del formato del email
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
                    Toast.makeText(RegistarActivity.this, "Ingrese un correo electrónico válido", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validación de la longitud y formato del password
                if (Clave.length() < 5 || !Clave.matches("^(?=.*[a-zA-Z])(?=.*\\d).+$")) {
                    Toast.makeText(RegistarActivity.this, "La contraseña debe tener al menos 5 caracteres y ser alfanumérica", Toast.LENGTH_LONG).show();
                    return;
                }

                // Validación de confirmación del password
                if (!Clave.equals(Clave1)) {
                    Toast.makeText(RegistarActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
                    return;
                }

                // Guardar los datos en SharedPreferences
                SharedPreferences usuarios = getSharedPreferences("preferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editorpref = usuarios.edit();
                editorpref.putString("Usuario", Usuario); // Clave única
                editorpref.putString("Email", Email);     // Clave única
                editorpref.putString("Clave", Clave);     // Clave única
                editorpref.apply();

                Toast.makeText(RegistarActivity.this, "Usuario guardado con éxito", Toast.LENGTH_LONG).show();

                // --- INICIO: Limpiar los EditText ---
                txtIngresoUsuario.setText("");
                txtIngresoEmail.setText("");
                txtIngresoPassword.setText("");
                txtIngresoPassword2.setText("");
                // --- FIN: Limpiar los EditText ---
            }
        });

        btnRegresar.setOnClickListener(v -> {
                    // Crea un Intent para iniciar RegistrarActivity
                    Intent intent = new Intent(this, MainActivity.class); // aqui pasamos al home
                    startActivity(intent); // Inicia la actividad
                    Toast.makeText(this, "Abriendo pantalla de home...", Toast.LENGTH_SHORT).show(); // Opcional
                    finish();
        });
    }

}