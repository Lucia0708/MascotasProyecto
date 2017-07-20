package com.example.lucia.mascotasproyecto.Notificaciones;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;


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

        // Chek if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        }

        //  // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }
        enviarNotificacion(remoteMessage);
    }

    public void enviarNotificacion(RemoteMessage remoteMessage) {

        List<NotificationCompat.Action> ListActions = new ArrayList<NotificationCompat.Action>();

        MainActivity.USERNAME = MainActivity.CUENTA_INSTAGRAM;
        MainActivity.ORIGEN = 3;

        Intent i = new Intent(this, PerfilCuentaInstagramActivity.class);
        i.setAction("VER_PERFIL");
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        // Intent para ver mi perfil

        Intent i1 = new Intent(this, PerfilCuentaInstagramActivity.class);
        i1.setAction("VER_PERFIL");

        // Intent para dar follow al usuario que ha dado like a mi foto
        Intent i2 = new Intent();
        i2.setAction("FOLLOW_UNFOLLOW");

        // Extraer el nombre del usuario de la notificacion

        int tam = remoteMessage.getNotification().getBody().length();
        tam = tam - 15;
        String userName = remoteMessage.getNotification().getBody().substring(0, tam);

        i2.putExtra("username", userName);

        // Intent para ver el perfil del usuario que ha dado like a mi foto

 //       SharedPreferences misPreferencias = getSharedPreferences("Instagram", Context.MODE_PRIVATE);
 //       SharedPreferences.Editor editor = misPreferencias.edit();

//        editor.putString("FollowUser", userName);
//        editor.commit();

        Intent i3 = new Intent(this, PerfilCuentaInstagramActivity.class);
        i3.setAction("VER_USUARIO_DIO_LIKE");

    // Configurando los PendingIntent

        PendingIntent pendingIntenti1 = PendingIntent.getActivity(this, 0, i1, PendingIntent.FLAG_ONE_SHOT);
        PendingIntent pendingIntenti2 = PendingIntent.getBroadcast(this, 0, i2, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntenti3 = PendingIntent.getActivity(this, 0, i3, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Action action_ver_perfil =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_mi_perfil,
                        getString(R.string.texto_accion_ver_mi_perfil), pendingIntenti1)
                        .build();

        NotificationCompat.Action action_follow_unfollow =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_data_transfer,
                        getString(R.string.texto_accion_follow_unfollow), pendingIntenti2)
                        .build();

        NotificationCompat.Action action_ver_usuario =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_usuario_dio_like,
                        getString(R.string.texto_accion_ver_usuario), pendingIntenti3)
                        .build();

        ListActions.add(action_ver_perfil);
        ListActions.add(action_follow_unfollow);
        ListActions.add(action_ver_usuario);

        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                        .setHintHideIcon(true)
                        .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.paisaaje2))
                        .setGravity(Gravity.CENTER_VERTICAL);


        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.icons8_user_48)
                .setContentTitle("Notificacion")
                .setContentText(remoteMessage.getNotification().getBody())
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .extend(wearableExtender.addActions(ListActions))
                 //  .addAction(R.drawable.ic_full_mi_perfil, getString(R.string.texto_accion_ver_mi_perfil), pendingIntent)
                ;

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());


    }

}

