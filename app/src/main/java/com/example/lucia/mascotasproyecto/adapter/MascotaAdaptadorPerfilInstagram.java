package com.example.lucia.mascotasproyecto.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucia.mascotasproyecto.Instagram.IPerfilCuentaInstagramActivityView;
import com.example.lucia.mascotasproyecto.Instagram.PerfilCuentaInstagramActivity;
import com.example.lucia.mascotasproyecto.InstagramPresenter.IPerfilCuentaInstagramActivityPresentador;
import com.example.lucia.mascotasproyecto.InstagramPresenter.PerfilCuentaInstagramActivityPresenter;
import com.example.lucia.mascotasproyecto.MainActivity;
import com.example.lucia.mascotasproyecto.R;
import com.example.lucia.mascotasproyecto.db.ConstructorMascotas;
import com.example.lucia.mascotasproyecto.pojo.MascotaInstagram;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Lucia on 06/06/2017.
 */

public class MascotaAdaptadorPerfilInstagram extends RecyclerView.Adapter<MascotaAdaptadorPerfilInstagram.MascotaViewHolder>  {

    ArrayList<MascotaInstagram> mascotasInstagram;
    Activity activity;
    private IPerfilCuentaInstagramActivityPresentador presenter;

    public MascotaAdaptadorPerfilInstagram(ArrayList<MascotaInstagram> mascotasInstagram, Activity activity){
        this.mascotasInstagram = mascotasInstagram;
        this.activity = activity;
    }

    //Inflar el layout y lo pasará al viewholder para el obtenga los views

    @Override
    public MascotaAdaptadorPerfilInstagram.MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota_perfil, parent, false);
        return new MascotaAdaptadorPerfilInstagram.MascotaViewHolder(v);
    }


    // asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(final MascotaAdaptadorPerfilInstagram.MascotaViewHolder mascotaViewHolder, int position) {
        final MascotaInstagram mascotaInstagram = mascotasInstagram.get(position);
   //     mascotaViewHolder.imgFoto.setImageResource(mascotaInstagram.getUrlfoto());
        Picasso.with(activity)
                .load(mascotaInstagram.getUrlfoto())
                .placeholder(R.drawable.bolita)
                .into(mascotaViewHolder.imgFoto);

        mascotaViewHolder.tvraitingcv.setText(String.valueOf(mascotaInstagram.getLikes()));

        mascotaViewHolder.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i;
                //   Toast.makeText(activity, "Diste like a " + mascota.getNombre() + mascota.getRaiting(), Toast.LENGTH_SHORT).show();


                MainActivity.IDMEDIA = mascotaInstagram.getId_foto();
                MainActivity.ORIGEN = 2;
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
        private TextView tvraitingcv;
        private ImageButton btnLike;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto     = (ImageView) itemView.findViewById(R.id.imgMascotacv);
            tvraitingcv = (TextView) itemView.findViewById(R.id.tvTotRatingcv);
            btnLike     = (ImageButton) itemView.findViewById(R.id.hueso_amarillocv);
        }
    }


}
