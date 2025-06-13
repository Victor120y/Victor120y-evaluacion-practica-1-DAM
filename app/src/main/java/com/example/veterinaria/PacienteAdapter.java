package com.example.veterinaria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton; // Importa ImageButton

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterinaria.R;
import com.example.veterinaria.data.Paciente;

import java.util.List;

public class PacienteAdapter extends RecyclerView.Adapter<PacienteAdapter.PacienteViewHolder> {
    private List<Paciente> pacientes;
    private OnPacienteActionListener actionListener;

    public interface OnPacienteActionListener {
        void onEditar(Paciente paciente);
        void onEliminar(Paciente paciente);
    }

    // Constructor para solo mostrar
    public PacienteAdapter(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    // Constructor para acciones
    public PacienteAdapter(List<Paciente> pacientes, OnPacienteActionListener listener) {
        this.pacientes = pacientes;
        this.actionListener = listener;
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

        // Zebra striping
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(0xFFFFFFFF);
        } else {
            holder.itemView.setBackgroundColor(0xFFF5F5F5);
        }

        // Listeners de editar y eliminar
        if (actionListener != null) {
            holder.btnEditar.setOnClickListener(v -> actionListener.onEditar(paciente));
            holder.btnEliminar.setOnClickListener(v -> actionListener.onEliminar(paciente));
        }
    }

    @Override
    public int getItemCount() {
        return pacientes.size();
    }

    static class PacienteViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtEspecie, txtDueno;
        ImageButton btnEditar, btnEliminar;

        PacienteViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombrePaciente);
            txtEspecie = itemView.findViewById(R.id.txtEspeciePaciente);
            txtDueno = itemView.findViewById(R.id.txtDueno);
            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}