package com.example.lucia.mascotasproyecto.Notificaciones;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Lucia on 21/06/2017.
 */

public class NotificacionIDTokenService extends FirebaseInstanceIdService{

    private static final String TAG = "FIREBASE_TOKEN";
    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        Log.d(TAG, "Solicitando Token");
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);

    }
    private void enviarTokenRegistro (String token){
        Log.d(TAG, token);


    }
}
