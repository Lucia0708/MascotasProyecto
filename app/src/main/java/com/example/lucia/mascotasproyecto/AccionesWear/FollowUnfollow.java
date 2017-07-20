package com.example.lucia.mascotasproyecto.AccionesWear;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.lucia.mascotasproyecto.Instagram.PerfilCuentaInstagramActivity;
import com.example.lucia.mascotasproyecto.MainActivity;
import com.example.lucia.mascotasproyecto.restApi.ConstantesRestApi;
import com.example.lucia.mascotasproyecto.restApi.EndpointsApi;
import com.example.lucia.mascotasproyecto.restApi.adapter.RestApiAdapter;
import com.example.lucia.mascotasproyecto.restApi.model.FollowResponse;
import com.example.lucia.mascotasproyecto.restApi.model.LikeResponse;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lucia.mascotasproyecto.MainActivity.IDUSERNAME;

/**
 * Created by Lucia on 16/07/2017.
 */

public class FollowUnfollow extends BroadcastReceiver{


    private Context classcontext;
    private String searchUserName;
    private String foundUserId;
    private String tempFollow;

    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity.USERNAME = "zach_dog_24";
        String ACCION_KEY = "FOLLOW_UNFOLLOW";
        String accion = intent.getAction();

        classcontext = context;

        if (ACCION_KEY.equals(accion)){
            Toast.makeText(context, "Diste Follow Unfollow a " + MainActivity.USERNAME, Toast.LENGTH_SHORT).show();

            Bundle bundle = intent.getExtras();
            String userName = bundle.getString("username");
            DarFollowUnfollow(userName, "follow", classcontext);

        }
    }
    public void DarFollowUnfollow (String userName, String follow, final Context classcontext) {

        searchUserName = userName;
        tempFollow = follow;

        MainActivity.USERNAME = userName;

        MainActivity.IDUSERNAME = "5032752476";
        String idUser = MainActivity.IDUSERNAME;
        MainActivity.ORIGEN = 3;

        // GET  https://api.instagram.com/v1/users/{user-id}/relationship?access_token=ACCESS-TOKEN
        // PRIMERO SE HACE GET PARA VER CUAL ES EL ESTADO ACTUAL DE LA RELACION Y DE ACUERDO A ELLA SE HARA POST CON
        // FOLLOW O CON UNFOLLOW

        String url;
        url = ConstantesRestApi.KEY1_GET_RELATIONSHIP + idUser + ConstantesRestApi.KEY2_GET_RELATIONSHIP;
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonfollowunfollow = restApiAdapter.construyeGsonDeserializadorFollowUnfollow();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonfollowunfollow);
        Call<FollowResponse> followResponseCall = endpointsApi.getfollowunfollow(url);
        followResponseCall.enqueue(new Callback<FollowResponse>() {
            @Override
            public void onResponse(Call<FollowResponse> call, Response<FollowResponse> response) {
                FollowResponse followResponse = response.body();
                String outgoing_status = followResponse.getOutgoing_status();
                String action = "";
                if (outgoing_status.equals("follows")) {
                    action = "unfollow";
                } else if (outgoing_status.equals("none")) {
                    action = "follow";
                }
                cambiarrelationshipconPOST(action, classcontext);
            }


            @Override
            public void onFailure(Call<FollowResponse> call, Throwable t) {
                Toast.makeText(classcontext, "!Algo pasó en la conexión¡ Intenta de nuevo ", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION ", t.toString());
            }
        });
    }


    public void cambiarrelationshipconPOST(String action, final Context classcontext){

        String idUser = MainActivity.IDUSERNAME;

        // POST  https://api.instagram.com/v1/users/{user-id}/relationship?access_token=ACCESS-TOKEN&action=follow

        String url;
        url = String.format(ConstantesRestApi.KEY2_POST_RELATIONSHIP, idUser);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonfollowunfollow = restApiAdapter.construyeGsonDeserializadorFollowUnfollow();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonfollowunfollow);

        Call<FollowResponse> followResponseCall = endpointsApi.postfollowunfollow(url, action);

        followResponseCall.enqueue(new Callback<FollowResponse>() {
            @Override
            public void onResponse(Call<FollowResponse> call, Response<FollowResponse> response) {
                FollowResponse followResponse = response.body();
                String outgoing_status = followResponse.getOutgoing_status();
            }

            @Override
            public void onFailure(Call<FollowResponse> call, Throwable t) {

                Toast.makeText(classcontext, "!Algo pasó en la conexión¡ Intenta de nuevo ", Toast.LENGTH_LONG).show();
                Log.e("FALLO LA CONEXION ", t.toString());
            }
        });
    }
}
