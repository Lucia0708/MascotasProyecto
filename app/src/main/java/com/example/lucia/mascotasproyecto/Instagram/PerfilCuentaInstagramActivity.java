package com.example.lucia.mascotasproyecto.Instagram;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucia.mascotasproyecto.InstagramPresenter.IPerfilCuentaInstagramActivityPresentador;
import com.example.lucia.mascotasproyecto.InstagramPresenter.PerfilCuentaInstagramActivityPresenter;
import com.example.lucia.mascotasproyecto.MainActivity;
import com.example.lucia.mascotasproyecto.R;
import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptadorPerfil;
import com.example.lucia.mascotasproyecto.adapter.MascotaAdaptadorPerfilInstagram;
import com.example.lucia.mascotasproyecto.pojo.Mascota;
import com.example.lucia.mascotasproyecto.pojo.MascotaInstagram;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PerfilCuentaInstagramActivity extends AppCompatActivity implements IPerfilCuentaInstagramActivityView {

    private ImageView ivImagenPerfilInst;
    private TextView tvNombreMascotaInst;

    ArrayList<MascotaInstagram> mascotasInstagrams;
    private RecyclerView listaMascotas;
    private IPerfilCuentaInstagramActivityPresentador presenter;

    public PerfilCuentaInstagramActivity() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_cuenta_instagram);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        //habiliatar la navegacion de regreso al padre
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (MainActivity.ORIGEN == 3) {
            if (getIntent().getAction().equals("VER_PERFIL")) {
                MainActivity.ACCION_PULSADA = "VER_PERFIL";
            }
            if (getIntent().getAction().equals("VER_USUARIO_DIO_LIKE")) {
                MainActivity.ACCION_PULSADA = "VER_USUARIO_DIO_LIKE";
            }
        }

        ivImagenPerfilInst = (ImageView) findViewById(R.id.ivImagenPerfilInst);
        tvNombreMascotaInst = (TextView) findViewById(R.id.tvNombreMascotaInst);

        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        presenter = new PerfilCuentaInstagramActivityPresenter(this);
    }
     @Override
    public void generalGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(this,3);
        listaMascotas.setLayoutManager(glm);
    }
    @Override
    public MascotaAdaptadorPerfilInstagram crearAdaptador(ArrayList<MascotaInstagram> mascotasInstagrams) {

        MascotaAdaptadorPerfilInstagram adaptador = new MascotaAdaptadorPerfilInstagram(mascotasInstagrams, this);
        return adaptador;
    }
    @Override
    public void inicializarAdaptadorRV(MascotaAdaptadorPerfilInstagram adaptadorPerfil) {
        listaMascotas.setAdapter(adaptadorPerfil);
    }

    @Override
    public void llenaEncabezado(ArrayList<MascotaInstagram> mascotasInstagrams) {
        tvNombreMascotaInst.setText(mascotasInstagrams.get(0).getUsername());

        Picasso.with(this)
                .load(mascotasInstagrams.get(0).getUrlfotoperfil())
                .placeholder(R.drawable.bolita)
                .into(ivImagenPerfilInst);


    }
}
