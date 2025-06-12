package com.example.veterinaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.veterinaria.data.AppDB;
import com.example.veterinaria.data.Paciente;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class fragment_paciente extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_paciente, container, false);

        TextInputEditText edtNombre = view.findViewById(R.id.edtNombrePaciente);
        TextInputEditText edtEspecie = view.findViewById(R.id.edtEspecie);
        TextInputEditText edtRaza = view.findViewById(R.id.edtRaza);
        TextInputEditText edtEdad = view.findViewById(R.id.edtEdad);
        TextInputEditText edtEncargado = view.findViewById(R.id.edtNombreDueno);
        TextInputEditText edtTelefono = view.findViewById(R.id.edtTelefono);

        MaterialButton btnGuardar = view.findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(v -> {
            String nombre = edtNombre.getText().toString();
            String especie = edtEspecie.getText().toString();
            String raza = edtRaza.getText().toString();
            int edad = edtEdad.getText().toString().isEmpty() ? 0 : Integer.parseInt(edtEdad.getText().toString());
            String encargado = edtEncargado.getText().toString();
            String telefono = edtTelefono.getText().toString();

            Paciente paciente = new Paciente(nombre, especie, raza, edad, encargado, telefono);

            new Thread(() -> {
                AppDB.getInstance(requireContext()).pascienteDAO().insertar(paciente);
                requireActivity().runOnUiThread(() -> {
                    Toast.makeText(getContext(), "Paciente agregado con éxito", Toast.LENGTH_SHORT).show();
                    edtNombre.setText("");
                    edtEspecie.setText("");
                    edtRaza.setText("");
                    edtEdad.setText("");
                    edtEncargado.setText("");
                    edtTelefono.setText("");
                });
            }).start();
        });

        return view;
    }
}