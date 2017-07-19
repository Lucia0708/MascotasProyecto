package com.example.lucia.mascotasproyecto.Notificaciones;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.example.lucia.mascotasproyecto.Instagram.PerfilCuentaInstagramActivity;
import com.example.lucia.mascotasproyecto.MainActivity;
import com.example.lucia.mascotasproyecto.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;

/**
 * Created by Lucia on 21/06/2017.
 */

public class NotificationService extends FirebaseMessagingService {
    public static final String TAG = "FIREBASE";
    public static final int NOTIFICATION_ID = 001;
    public static final String ACCION_ENVIADA = "";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());


        int tam = remoteMessage.getNotification().getBody().length();
        tam = tam - 15;
    //    MainActivity.USERNAME = remoteMessage.getNotification().getBody().substring(0, tam);
        MainActivity.USERNAME = MainActivity.CUENTA_INSTAGRAM;
        MainActivity.ORIGEN = 3;

        Intent i1 = new Intent(this, PerfilCuentaInstagramActivity.class);

        i1.putExtra(ACCION_ENVIADA, "VER_PERFIL");
//        Intent i1 = new Intent();
 //       i1.setAction("VER_PERFIL");
//        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(this, 0, i1, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent1 = PendingIntent.getActivity(this, 0, i1, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action action_ver_perfil =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_mi_perfil,
                        getString(R.string.texto_accion_ver_mi_perfil), pendingIntent1)
                .build();

//        Intent i2 = new Intent(this, PerfilCuentaInstagramActivity.class);
//        PendingIntent pendingIntent2 = PendingIntent.getActivity(this, 0, i2, PendingIntent.FLAG_ONE_SHOT);

        Intent i2 = new Intent();
        i2.setAction("FOLLOW_UNFOLLOW");
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action_follow_unfollow =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_data_transfer,
                        getString(R.string.texto_accion_follow_unfollow), pendingIntent2)
                        .build();

        MainActivity.USERNAME = MainActivity.CUENTA_EMISORA;

        Intent i3 = new Intent(this, PerfilCuentaInstagramActivity.class);
  //      i3.setAction("VER_USUARIO_DIO_LIKE");
        i3.putExtra(ACCION_ENVIADA, "VER_USUARIO_DIO_LIKE");

        PendingIntent pendingIntent3 = PendingIntent.getActivity(this, 0, i3, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Action action_ver_usuario =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_usuario_dio_like,
                        getString(R.string.texto_accion_ver_usuario), pendingIntent3)
                        .build();

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.paisaaje2))
                .setGravity(Gravity.CENTER_VERTICAL)
                ;


        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icons8_user_48)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntent1)
                .setAutoCancel(true)
                .extend(wearableExtender.addAction(action_ver_perfil))
                .extend(wearableExtender.addAction(action_follow_unfollow))
                .extend(wearableExtender.addAction(action_ver_usuario))
              //  .addAction(R.drawable.ic_full_mi_perfil, getString(R.string.texto_accion_ver_mi_perfil), pendingIntent)
                ;

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());


    }


}
