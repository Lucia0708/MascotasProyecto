package com.example.lucia.mascotasproyecto.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucia.mascotasproyecto.Instagram.PerfilCuentaInstagramActivity;
import com.example.lucia.mascotasproyecto.MainActivity;
import com.example.lucia.mascotasproyecto.R;
import com.example.lucia.mascotasproyecto.pojo.Mascota;
import com.example.lucia.mascotasproyecto.pojo.MascotaInstagram;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Lucia on 13/06/2017.
 */

public class MascotaAdaptadorTimeline extends RecyclerView.Adapter <MascotaAdaptadorTimeline.MascotaViewHolder> {
    ArrayList<MascotaInstagram> mascotasInstagram;
    Activity activity;

    public MascotaAdaptadorTimeline(ArrayList<MascotaInstagram> mascotasInstagram, Activity activity){
        this.mascotasInstagram = mascotasInstagram;
        this.activity = activity;
    }
    //Inflar el layout y lo pasará al viewholder para el obtenga los views

    @Override
    public MascotaAdaptadorTimeline.MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_timeline, parent, false);
        return new MascotaAdaptadorTimeline.MascotaViewHolder(v);
    }


    // asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(final MascotaAdaptadorTimeline.MascotaViewHolder mascotaViewHolder, int position) {
        final MascotaInstagram mascotaInstagram = mascotasInstagram.get(position);
//        mascotaViewHolder.imgFoto.setImageResource(mascotaInstagram.getUrlfoto());
        Picasso.with(activity)
                .load(mascotaInstagram.getUrlfotoperfil())
                .placeholder(R.drawable.bolita)
                .into(mascotaViewHolder.imgFoto);

        mascotaViewHolder.tvNombrecv.setText(mascotaInstagram.getUsername());

           mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.USERNAME = mascotaInstagram.getUsername();
                Intent intent = new Intent(activity, PerfilCuentaInstagramActivity.class );
                activity.startActivity(intent);
                    }
              });


    }

    @Override
    public int getItemCount() {//cantidad de elementos que contiene mi lista de contactos
        return mascotasInstagram.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvNombrecv;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto     = (ImageView) itemView.findViewById(R.id.imgMascotacv);
            tvNombrecv  = (TextView) itemView.findViewById(R.id.tvNombreMascotacv);
          }
    }



}
