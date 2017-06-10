package com.example.lucia.mascotasproyecto.InstagramPresenter;

/**
 * Created by Lucia on 06/06/2017.
 */

public interface IPerfilCuentaInstagramActivityPresentador {
    public void  obtenerMascotasCuentaInstagram();

    public void obtenerIdbyUsername(String Username);
    public void obtenerMediosRecientes();

    public void obtenerMediosRecientesbyId(String idUsername);

    public void  mostrarMascotasRV();
}
