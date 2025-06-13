package com.example.veterinaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.veterinaria.data.AppDB;
import com.example.veterinaria.data.Paciente;

import java.util.ArrayList;
import java.util.List;

public class fragment_lista_pacientes extends Fragment {
    private PacienteAdapter adapter;
    private List<Paciente> listaPacientes = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_pacientes, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rcvLista);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new PacienteAdapter(listaPacientes, new PacienteAdapter.OnPacienteActionListener() {
            @Override
            public void onEditar(Paciente paciente) {
                // Abre un diálogo o nueva pantalla para editar y luego actualiza la base de datos
            }

            @Override
            public void onEliminar(Paciente paciente) {
                new Thread(() -> {
                    AppDB.getInstance(requireContext()).pacienteDAO().eliminar(paciente);
                    buscarPacientes(""); // Refresca la lista
                }).start();
            }
        });

        //adapter = new PacienteAdapter(listaPacientes);
        recyclerView.setAdapter(adapter);

       androidx.appcompat.widget.SearchView searchView = view.findViewById(R.id.srcBusqueda);
       searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               buscarPacientes(query);
               return true;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
               buscarPacientes(newText);
               return true;
           }
       });

        // Cargar todos al inicio
        buscarPacientes("");
        return view;
    }

    private void buscarPacientes(String query) {
        new Thread(() -> {
            List<Paciente> resultados = AppDB.getInstance(requireContext())
                    .pacienteDAO()
                    .buscarPorNombreODueno(query);
            requireActivity().runOnUiThread(() -> {
                listaPacientes.clear();
                listaPacientes.addAll(resultados);
                adapter.notifyDataSetChanged();
            });
        }).start();
    }
}