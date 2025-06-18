package com.example.veterinaria.ui.slideshow;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.example.veterinaria.R;
import com.example.veterinaria.data.AppDB;
import com.example.veterinaria.data.Cita;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import java.util.Calendar;

public class agendarCitaFragment extends Fragment {

    private TextInputEditText edtNombreDueno, edtNombrePerro, edtTelefono, edtFecha, edtHora, edtMotivo;
    private ImageButton imgButtonFecha, imgButtonHora;
    private MaterialButton btnGuardar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agendar_cita, container, false);

        edtNombreDueno = view.findViewById(R.id.edtNombreDueno);
        edtNombrePerro = view.findViewById(R.id.edtNombrePerro);
        edtTelefono = view.findViewById(R.id.edtTelefono);
        edtFecha = view.findViewById(R.id.edtFecha);
        edtHora = view.findViewById(R.id.edtHora);
        edtMotivo = view.findViewById(R.id.edtMotivo);
        imgButtonFecha = view.findViewById(R.id.imgButtonFecha);
        imgButtonHora = view.findViewById(R.id.imgButtonHora);
        btnGuardar = view.findViewById(R.id.btnGuardar);

        imgButtonFecha.setOnClickListener(v -> mostrarDatePicker());
        imgButtonHora.setOnClickListener(v -> mostrarTimePicker());

        btnGuardar.setOnClickListener(v -> {
            String nombreDueno = edtNombreDueno.getText() != null ? edtNombreDueno.getText().toString() : "";
            String nombrePerro = edtNombrePerro.getText() != null ? edtNombrePerro.getText().toString() : "";
            String telefono = edtTelefono.getText() != null ? edtTelefono.getText().toString() : "";
            String fecha = edtFecha.getText() != null ? edtFecha.getText().toString() : "";
            String hora = edtHora.getText() != null ? edtHora.getText().toString() : "";
            String motivo = edtMotivo.getText() != null ? edtMotivo.getText().toString() : "";

            Cita cita = new Cita();
            cita.nombreDueno = nombreDueno;
            cita.nombrePerro = nombrePerro;
            cita.telefono = telefono;
            cita.fecha = fecha;
            cita.hora = hora;
            cita.motivo = motivo;

            new Thread(() -> {
                AppDB db = Room.databaseBuilder(requireContext(),
                        AppDB.class, "veterinaria-db").build();
                db.citaDAO().insertarCita(cita);
            }).start();

            Toast.makeText(getContext(),
                    "Cita agendada para " + nombrePerro + " (" + nombreDueno + ") el " + fecha + " a las " + hora,
                    Toast.LENGTH_LONG).show();

            edtNombreDueno.setText("");
            edtNombrePerro.setText("");
            edtTelefono.setText("");
            edtFecha.setText("");
            edtHora.setText("");
            edtMotivo.setText("");
        });

        return view;
    }

    private void mostrarDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, year1, month1, dayOfMonth) -> {
                    String fechaSeleccionada = String.format("%02d/%02d/%04d", dayOfMonth, month1 + 1, year1);
                    edtFecha.setText(fechaSeleccionada);
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void mostrarTimePicker() {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(
                requireContext(),
                (view, hourOfDay, minute1) -> {
                    String horaSeleccionada = String.format("%02d:%02d", hourOfDay, minute1);
                    edtHora.setText(horaSeleccionada);
                },
                hour, minute, true
        );
        timePickerDialog.show();
    }
}