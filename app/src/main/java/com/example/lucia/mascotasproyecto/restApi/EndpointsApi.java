package com.example.lucia.mascotasproyecto.restApi;

import com.example.lucia.mascotasproyecto.R;
import com.example.lucia.mascotasproyecto.restApi.model.MascotaResponse;
import com.example.lucia.mascotasproyecto.restApi.model.UserInstagramResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Lucia on 07/06/2017.
 */

public interface EndpointsApi {

    // Para obtener Recent_media_de_Self
    // https://api.instagram.com/v1/users/self/media/recent/?access_token=ACCESS-TOKEN
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia();

    // Para obtener Recent_media_de_ID
    // https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    @GET
    public Call<MascotaResponse> getRecentMediaid(@Url String url);

// Para obtener el id buscando con el USERNAME
// https://api.instagram.com/v1/users/search?q=jack&access_token=ACCESS-TOKEN
    @GET
    public Call<UserInstagramResponse> getIdbyUsername(@Url String url);

    // Para obtener los Folows de este usuaior
    //https://api.instagram.com/v1/users/self/follows?access_token=ACCESS-TOKEN

    @GET (ConstantesRestApi.KEY2_GET_FOLLOWS_SELF)
    Call<MascotaResponse> getfollowsself();

}
