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
import com.example.lucia.mascotasproyecto.presentador.Ilista_mascotas_fragment_Presenter;
import com.example.lucia.mascotasproyecto.presentador.lista_mascotas_fragment_Presenter;

import java.util.ArrayList;

/**
 * Created by Lucia on 19/05/2017.
 */

public class lista_mascotas_fragment extends Fragment implements Ilista_mascotas_fragment_View {

    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    private Ilista_mascotas_fragment_Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

       //  return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_lista_mascotas, container, false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter = new lista_mascotas_fragment_Presenter(this, getContext());
        return v;
    }

   @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}
