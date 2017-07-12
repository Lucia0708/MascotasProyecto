package com.example.lucia.mascotasproyecto.restApiFirebase;

import com.example.lucia.mascotasproyecto.restApiFirebase.model.UsuarioLikeResponse;
import com.example.lucia.mascotasproyecto.restApiFirebase.model.UsuarioResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Lucia on 22/06/2017.
 */

public interface EndpointsFirebase {
    @FormUrlEncoded
    @POST(ConstantesRestAPIFirebase.KEY_POST_ID_TOKEN)
    Call<UsuarioResponse> registrarusuario(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram);


    @FormUrlEncoded
    @POST(ConstantesRestAPIFirebase.KEY_POST_ID_LIKE_INSTAGRAM)
    Call<UsuarioLikeResponse> registrarlikeusuario(@Field("id_dispositivo") String id_dispositivo, @Field("id_usuario_instagram") String id_usuario_instagram, @Field("id_foto_instagram") String id_foto_instagram);

    @GET (ConstantesRestAPIFirebase.KEY_GET_TOQUE_LIKE_ANIMAL)
    Call<UsuarioResponse> toqueLikeAnimal(@Path("id") String id, @Path("id_usuario_instagram") String id_usuario_instagram);




}
