package com.example.lucia.mascotasproyecto.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lucia.mascotasproyecto.MascotasFavoritas.IMascotasFavoritasView;
import com.example.lucia.mascotasproyecto.R;
import com.example.lucia.mascotasproyecto.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Lucia on 26/05/2017.
 */

public class MascotaAdaptadorFavoritas extends RecyclerView.Adapter <MascotaAdaptadorFavoritas.MascotaViewHolder> {
    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptadorFavoritas(ArrayList<Mascota> mascotas, Activity activity){
        this.mascotas = mascotas;
        this.activity = activity;
    }
    //Inflar el layout y lo pasará al viewholder para el obtenga los views

    @Override
    public MascotaAdaptadorFavoritas.MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaAdaptadorFavoritas.MascotaViewHolder(v);
    }

     // asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(final MascotaAdaptadorFavoritas.MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvNombrecv.setText(mascota.getNombre());
        mascotaViewHolder.tvraitingcv.setText(String.valueOf(mascota.getRaiting()));
    }

    @Override
    public int getItemCount() {//cantidad de elementos que contiene mi lista de contactos
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        private TextView tvNombrecv;
        private TextView  tvraitingcv;
        private ImageButton btnLike;
        private ImageButton btnMuestraRaiting;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto     = (ImageView) itemView.findViewById(R.id.imgMascotacv);
            tvNombrecv  = (TextView) itemView.findViewById(R.id.tvNombreMascotacv);
            tvraitingcv = (TextView) itemView.findViewById(R.id.tvTotRatingcv);
            btnLike     = (ImageButton) itemView.findViewById(R.id.hueso_blancocv);
            btnMuestraRaiting =(ImageButton) itemView.findViewById(R.id.hueso_amarillocv);
        }
    }
}
