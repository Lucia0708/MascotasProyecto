package com.example.lucia.mascotasproyecto.InstagramPresenter;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lucia.mascotasproyecto.Instagram.ITimeLineView;
import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptadorFavoritas;
import com.example.lucia.mascotasproyecto.db.ConstructorMascotas;
import com.example.lucia.mascotasproyecto.pojo.Mascota;
import com.example.lucia.mascotasproyecto.pojo.MascotaInstagram;
import com.example.lucia.mascotasproyecto.restApi.EndpointsApi;
import com.example.lucia.mascotasproyecto.restApi.adapter.RestApiAdapter;
import com.example.lucia.mascotasproyecto.restApi.model.MascotaResponse;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Lucia on 13/06/2017.
 */

public class TimeLinePresenter implements ITimeLinePresenter {

    private ITimeLineView iTimeLineView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<MascotaInstagram> mascotasInstagrams;

    public TimeLinePresenter(ITimeLineView iTimeLineView, Context context) {
        this.iTimeLineView = iTimeLineView;
        this.context = context;
        obtenerTimeLineInstagram();
    }

    public TimeLinePresenter(ITimeLineView iTimeLineView) {
        this.iTimeLineView = iTimeLineView;
        obtenerTimeLineInstagram();
    }


    @Override
    public void obtenerTimeLineInstagram() {
        // Obtiene los datos Media Recientes de la cuenta SELF
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonfollowself = restApiAdapter.construyeGsonDeserializadorfollwsself();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonfollowself);
        Call<MascotaResponse> mascotaResponseCall = endpointsApi.getfollowsself();
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotasInstagrams = mascotaResponse.getMascotasInstagram();
                mostrarMascotasRV();
            }
            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "!Algo pasó en la conexión¡ Intenta de nuevo ", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION ", t.toString());
            }
        });
    }

    @Override
    public void mostrarMascotasRV() {
        iTimeLineView.inicializarAdaptadorRV(iTimeLineView.crearAdaptador(mascotasInstagrams));
        iTimeLineView.generalLinearLayout();


    }
}
