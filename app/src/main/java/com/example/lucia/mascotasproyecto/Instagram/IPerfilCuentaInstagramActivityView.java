package com.example.lucia.mascotasproyecto.Instagram;

import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptadorPerfil;
import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptadorPerfilInstagram;
import com.example.lucia.mascotasproyecto.pojo.Mascota;
import com.example.lucia.mascotasproyecto.pojo.MascotaInstagram;

import java.util.ArrayList;

/**
 * Created by Lucia on 06/06/2017.
 */

public interface IPerfilCuentaInstagramActivityView {

    public final String idUsername = "";

    public void generalGridLayout();

    public MascotaAdaptadorPerfilInstagram crearAdaptador(ArrayList<MascotaInstagram> mascotasInstagrams);

    public void inicializarAdaptadorRV(MascotaAdaptadorPerfilInstagram adaptadorPerfil);

    public void llenaEncabezado(ArrayList<MascotaInstagram> mascotasInstagrams);
}
