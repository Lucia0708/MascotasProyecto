package com.example.lucia.mascotasproyecto.restApi.adapter;

import com.example.lucia.mascotasproyecto.restApi.ConstantesRestApi;
import com.example.lucia.mascotasproyecto.restApi.Deserializador.IdbyUserDeserializador;
import com.example.lucia.mascotasproyecto.restApi.Deserializador.MascotaDeserializador;
import com.example.lucia.mascotasproyecto.restApi.Deserializador.followsSelfDeserializador;
import com.example.lucia.mascotasproyecto.restApi.EndpointsApi;
import com.example.lucia.mascotasproyecto.restApi.model.MascotaResponse;
import com.example.lucia.mascotasproyecto.restApi.model.UserInstagramResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucia on 07/06/2017.
 */

public class RestApiAdapter {

    public EndpointsApi establecerConexionRestApiInstagram (Gson gson){
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndpointsApi.class);
    }


public Gson construyeGsonDeserializadorMediaRecent(){
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());

    return gsonBuilder.create();
}

    public Gson construyeGsonDeserializadorIdbyUsername() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(UserInstagramResponse.class, new IdbyUserDeserializador());

        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorfollwsself(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new followsSelfDeserializador());

        return gsonBuilder.create();

    }


}


