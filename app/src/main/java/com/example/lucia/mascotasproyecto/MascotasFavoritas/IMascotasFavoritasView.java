package com.example.lucia.mascotasproyecto.MascotasFavoritas;

import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptadorFavoritas;
import com.example.lucia.mascotasproyecto.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Lucia on 26/05/2017.
 */

public interface IMascotasFavoritasView {

    public void generarLinearLayoutVertical ();

    public MascotaAdaptadorFavoritas crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptadorFavoritas adaptador);

}
