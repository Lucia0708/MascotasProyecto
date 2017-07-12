package com.example.lucia.mascotasproyecto.InstagramPresenter;

/**
 * Created by Lucia on 06/06/2017.
 */

public interface IPerfilCuentaInstagramActivityPresentador {
    public void  obtenerMascotasCuentaInstagram();

    public void obtenerIdbyUsername(String Username);
    public void obtenerMediosRecientes();

    public void obtenerMediosRecientesbyId(String idUsername);

    public void insertarLikeInstagrambymedia(String idMedia);

    public void enviarTokenLikeRegistro(String token, String id_usuario_instagram, String id_foto_instagram);

    public void  mostrarMascotasRV();
}
