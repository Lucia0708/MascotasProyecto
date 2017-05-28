package com.example.lucia.mascotasproyecto.fragments;

import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptador;
import com.example.lucia.mascotasproyecto.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Lucia on 25/05/2017.
 */

public interface Ilista_mascotas_fragment_View {

    public void generarLinearLayoutVertical ();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

}
