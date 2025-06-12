package com.example.veterinaria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterinaria.R;
import com.example.veterinaria.data.Paciente;

import java.util.List;

// PacienteAdapter.java
public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder> {
    private List<Paciente> pacientes;

    public PacienteAdapter(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @NonNull
    @Override
    public PacienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_paciente, parent, false);
        return new PacienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PacienteViewHolder holder, int position) {
        Paciente paciente = pacientes.get(position);
        holder.txtNombre.setText(paciente.nombre);
        holder.txtEspecie.setText(paciente.especie);
        holder.txtDueno.setText(paciente.encargado);
    }

    @Override
    public int getItemCount() {
        return pacientes.size();
    }

    static class PacienteViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtEspecie, txtDueno;

        PacienteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombrePaciente);
            txtEspecie = itemView.findViewById(R.id.txtEspeciePaciente);
            txtDueno = itemView.findViewById(R.id.txtDueno);
        }
    }
}