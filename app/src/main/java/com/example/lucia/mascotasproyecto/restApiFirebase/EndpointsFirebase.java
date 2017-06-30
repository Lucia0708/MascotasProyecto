package com.example.lucia.mascotasproyecto.restApiFirebase;

import com.example.lucia.mascotasproyecto.restApiFirebase.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Lucia on 22/06/2017.
 */

public interface EndpointsFirebase {
    @FormUrlEncoded
    @POST(ConstantesRestAPIFirebase.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarusuario(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram);
}
