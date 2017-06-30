package com.example.lucia.mascotasproyecto.restApiFirebase.adapter;

import com.example.lucia.mascotasproyecto.restApiFirebase.ConstantesRestAPIFirebase;
import com.example.lucia.mascotasproyecto.restApiFirebase.EndpointsFirebase;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lucia on 22/06/2017.
 */

public class RestApiAdapterFirebase {
    public EndpointsFirebase establecerConexionRestApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPIFirebase.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;
        return retrofit.create(EndpointsFirebase.class);


    }
}
