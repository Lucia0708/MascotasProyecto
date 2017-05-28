package com.example.lucia.mascotasproyecto.presentador;

import android.content.Context;

import com.example.lucia.mascotasproyecto.MascotasFavoritas.IMascotasFavoritasView;
import com.example.lucia.mascotasproyecto.MascotasFavoritas.MascotasFavoritas;
import com.example.lucia.mascotasproyecto.db.ConstructorMascotas;
import com.example.lucia.mascotasproyecto.pojo.Mascota;

import java.security.AccessControlContext;
import java.util.ArrayList;

/**
 * Created by Lucia on 26/05/2017.
 */

public class MascotasFavoritasPresenter implements IMascotasFavoritasPresenter {

    private IMascotasFavoritasView iMascotasFavoritasView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

     public MascotasFavoritasPresenter(IMascotasFavoritasView iMascotasFavoritasView, Context context){
        this.iMascotasFavoritasView = iMascotasFavoritasView;
        this.context = context;
         obtenerMascotasFavoritasBaseDatos();
     }

    public MascotasFavoritasPresenter(IMascotasFavoritasView iMascotasFavoritasView){
        this.iMascotasFavoritasView = iMascotasFavoritasView;
        obtenerMascotasFavoritasBaseDatos();
    }

    @Override
    public void obtenerMascotasFavoritasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatosFavoritas();
        mostrarMascotasRV();
    }

    @Override
    public void mostrarMascotasRV() {
        iMascotasFavoritasView.inicializarAdaptadorRV(iMascotasFavoritasView.crearAdaptador(mascotas));
        iMascotasFavoritasView.generarLinearLayoutVertical();

    }
}
