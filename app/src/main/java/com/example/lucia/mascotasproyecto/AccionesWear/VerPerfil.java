package com.example.lucia.mascotasproyecto.AccionesWear;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.lucia.mascotasproyecto.Instagram.PerfilCuentaInstagramActivity;
import com.example.lucia.mascotasproyecto.MainActivity;

/**
 * Created by Lucia on 16/07/2017.
 */

public class VerPerfil extends BroadcastReceiver{

    Activity activity;


    @Override
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY = "VER_PERFIL";
        String accion = intent.getAction();
        MainActivity.ACCION_PULSADA = accion;

        if (ACCION_KEY.equals(accion)){
            que_hago();
            Toast.makeText(context, "Veras tu perfil ", Toast.LENGTH_SHORT).show();
        }
    }

    public void que_hago (){
        MainActivity.USERNAME = "zach_dog_24";
        MainActivity.ORIGEN = 3;
        Intent intent = new Intent(activity, PerfilCuentaInstagramActivity.class );
        activity.startActivity(intent);
   }

}
