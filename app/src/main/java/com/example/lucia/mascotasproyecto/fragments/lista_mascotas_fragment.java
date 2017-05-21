package com.example.lucia.mascotasproyecto.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucia.mascotasproyecto.R;
import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptador;
import com.example.lucia.mascotasproyecto.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Lucia on 19/05/2017.
 */

public class lista_mascotas_fragment extends Fragment {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    public MascotaAdaptador adaptador;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       //  return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_lista_mascotas, container, false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(llm);
        inicializarListaMascotas();
        inicializarAdaptador();

        return v;
    }

    public void inicializarAdaptador() {
        adaptador = new MascotaAdaptador(mascotas, getActivity());
        listaMascotas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas() {
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.connor, "Connor", "0"));
        mascotas.add(new Mascota(R.drawable.bolita, "Bolita", "0"));
        mascotas.add(new Mascota(R.drawable.bunny, "Bunny", "0"));
        mascotas.add(new Mascota(R.drawable.paloma, "Paloma", "0"));
        mascotas.add(new Mascota(R.drawable.shuly, "Shuly", "0"));
    }
}
