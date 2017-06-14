package com.example.lucia.mascotasproyecto.Instagram;

import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptadorFavoritas;
import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptadorPerfilInstagram;
import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptadorTimeline;
import com.example.lucia.mascotasproyecto.pojo.Mascota;
import com.example.lucia.mascotasproyecto.pojo.MascotaInstagram;

import java.util.ArrayList;

/**
 * Created by Lucia on 13/06/2017.
 */

public interface ITimeLineView {
    public void generalLinearLayout();

    public MascotaAdaptadorTimeline crearAdaptador(ArrayList<MascotaInstagram> mascotasInstagrams);

    public void inicializarAdaptadorRV(MascotaAdaptadorTimeline adaptador);

}
