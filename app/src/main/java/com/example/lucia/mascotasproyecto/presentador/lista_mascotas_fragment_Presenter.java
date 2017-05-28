package com.example.lucia.mascotasproyecto.presentador;

import android.content.Context;

import com.example.lucia.mascotasproyecto.db.ConstructorMascotas;
import com.example.lucia.mascotasproyecto.fragments.Ilista_mascotas_fragment_View;
import com.example.lucia.mascotasproyecto.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Lucia on 25/05/2017.
 */

public class lista_mascotas_fragment_Presenter implements Ilista_mascotas_fragment_Presenter{


   private Ilista_mascotas_fragment_View ilista_mascotas_fragment_view;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public lista_mascotas_fragment_Presenter(Ilista_mascotas_fragment_View ilista_mascotas_fragment_view, Context context){
        this.ilista_mascotas_fragment_view = ilista_mascotas_fragment_view;
        this.context = context;
        obtenerMascotasBaseDatos();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        ilista_mascotas_fragment_view.inicializarAdaptadorRV(ilista_mascotas_fragment_view.crearAdaptador(mascotas));
        ilista_mascotas_fragment_view.generarLinearLayoutVertical();
    }
}
