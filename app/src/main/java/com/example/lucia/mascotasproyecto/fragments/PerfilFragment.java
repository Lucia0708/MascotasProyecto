package com.example.lucia.mascotasproyecto.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lucia.mascotasproyecto.R;
import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptador;
import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptadorPerfil;
import com.example.lucia.mascotasproyecto.pojo.Mascota;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
    ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    public MascotaAdaptadorPerfil adaptador;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_perfil, container, false);

        //  return super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);


        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);
        glm.setOrientation(GridLayoutManager.VERTICAL);

        listaMascotas.setLayoutManager(glm);
        inicializarListaMascotas();
        inicializarAdaptador();

        return v;
    }

    public void inicializarAdaptador() {
        adaptador = new MascotaAdaptadorPerfil(mascotas, getActivity());
        listaMascotas.setAdapter(adaptador);
    }

    public void inicializarListaMascotas() {
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.img_4850, "Conor", 9));
        mascotas.add(new Mascota(R.drawable.img_4816, "Bolita", 7));
        mascotas.add(new Mascota(R.drawable.img_4815, "Bunny", 7));

    }

}
