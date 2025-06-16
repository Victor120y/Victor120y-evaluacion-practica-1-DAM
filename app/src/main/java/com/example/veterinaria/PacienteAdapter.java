package com.example.veterinaria;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageButton; // Importa ImageButton
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterinaria.R;
import com.example.veterinaria.data.AppDB;
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
            holder.btnEditar.setOnClickListener(v -> {
                View dialogView = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.dialog_editar_paciente, null);
                TextView txtNombre = dialogView.findViewById(R.id.txtNombreEditar);
                TextView txtEspecie = dialogView.findViewById(R.id.txtEspecieEditar);
                TextView txtDueno = dialogView.findViewById(R.id.txtDuenoEditar);

                // Prellenar los datos actuales
                txtNombre.setText(paciente.nombre);
                txtEspecie.setText(paciente.especie);
                txtDueno.setText(paciente.encargado);

                new android.app.AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("Editar paciente")
                    .setView(dialogView)
                    .setPositiveButton("Guardar", (dialog, which) -> {
                        // Validar los datos
                        String nombre = txtNombre.getText().toString().trim();
                        String especie = txtEspecie.getText().toString().trim();
                        String dueno = txtDueno.getText().toString().trim();

                        if (nombre.isEmpty() || especie.isEmpty() || dueno.isEmpty()) {
                            Toast.makeText(holder.itemView.getContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        // Actualizar los datos del paciente
                        paciente.nombre = nombre;
                        paciente.especie = especie;
                        paciente.encargado = dueno;

                        // Guardar en la base de datos en un hilo secundario
                        new Thread(() -> {
                            AppDB.getInstance(holder.itemView.getContext()).pacienteDAO().actualizar(paciente);
                            // Refrescar la lista en el hilo principal
                            ((android.app.Activity) holder.itemView.getContext()).runOnUiThread(() -> {
                                notifyItemChanged(holder.getAdapterPosition());
                                Toast.makeText(holder.itemView.getContext(), "Paciente actualizado correctamente", Toast.LENGTH_SHORT).show();
                            });
                        }).start();
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> {
                        // No se realiza ninguna acción, el diálogo simplemente se cierra
                        dialog.dismiss();
                    })
                    .show();
            });

            holder.btnEliminar.setOnClickListener(v -> {
                new android.app.AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("Eliminar paciente")
                    .setMessage("¿Estás seguro de que deseas eliminar este paciente de forma permanente?")
                    .setPositiveButton("SÍ", (dialog, which) -> {
                        try {
                            actionListener.onEliminar(paciente);
                            Toast.makeText(holder.itemView.getContext(), "Paciente eliminado correctamente", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            Toast.makeText(holder.itemView.getContext(), "Error al eliminar el paciente: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .setNegativeButton("NO", null)
                    .show();
            });
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