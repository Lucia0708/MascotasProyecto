package com.example.lucia.mascotasproyecto;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lucia.mascotasproyecto.Instagram.ConfigurarCueInstagramActivity;
import com.example.lucia.mascotasproyecto.Instagram.PerfilCuentaInstagramActivity;
import com.example.lucia.mascotasproyecto.Instagram.TimeLineActivity;
import com.example.lucia.mascotasproyecto.MascotasFavoritas.MascotasFavoritas;
import com.example.lucia.mascotasproyecto.adapter.PageAdapter;
import com.example.lucia.mascotasproyecto.fragments.PerfilFragment;
import com.example.lucia.mascotasproyecto.fragments.lista_mascotas_fragment;
import com.example.lucia.mascotasproyecto.menuactivity.AcercadeActivity;
import com.example.lucia.mascotasproyecto.menuactivity.ContactoActivity;
import com.example.lucia.mascotasproyecto.restApiFirebase.EndpointsFirebase;
import com.example.lucia.mascotasproyecto.restApiFirebase.adapter.RestApiAdapterFirebase;
import com.example.lucia.mascotasproyecto.restApiFirebase.model.UsuarioResponse;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    // Clase principal del Proyecto

    public static String USERNAME = "perritoconnor";
    public static String IDUSERNAME = "";
    public static String IDMEDIA = "";
    public static int ORIGEN = 1;
    public static String CUENTA_INSTAGRAM = "perritoconnor";

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar  = (Toolbar) findViewById(R.id.toolbar);
        tabLayout  = (TabLayout) findViewById(R.id.tabLayout);
        viewPager  = (ViewPager) findViewById(R.id.viewPager);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        setUpViewPager();
        agregarFAB();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.mContacto:
                Intent intent = new Intent(this, ContactoActivity.class);
                startActivity(intent);
                break;
            case R.id.mAcercade:
                Intent i = new Intent(this, AcercadeActivity.class);
                startActivity(i);
                break;
            case R.id.mTimelineInstagram:
                Intent intentTimeLine = new Intent(this, TimeLineActivity.class);
                startActivity(intentTimeLine);
                break;
            case R.id.mConfigurarCuentaInstagram:
                Intent intentConfigurarCuentaInstagram = new Intent(this, ConfigurarCueInstagramActivity.class);
                startActivity(intentConfigurarCuentaInstagram);
                break;
            case R.id.mperritoconnor:
                Intent i3 = new Intent(this, PerfilCuentaInstagramActivity.class);
                i3.putExtra(getResources().getString(R.string.pUsername), getResources().getString(R.string.perritoconnor));
                MainActivity.USERNAME=getResources().getString(R.string.perritoconnor);
                MainActivity.ORIGEN = 1;
                startActivity(i3);
                break;
            case R.id.mNotificacion:
                String token =  FirebaseInstanceId.getInstance().getToken();
                String id_usuario_instagram = IDUSERNAME;
                enviarTokenRegistro(token, id_usuario_instagram);
            break;

            case R.id.ibfavoritos:
                Intent fav = new Intent(MainActivity.this,MascotasFavoritas.class);
                startActivity(fav);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragment(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new lista_mascotas_fragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }
    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragment()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dog_perfil);
    }

    public void agregarFAB (){
        FloatingActionButton miFAB = (FloatingActionButton) findViewById(R.id.fab);
        miFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Intent intent = new Intent(MainActivityListaMascotas.this, MascotasFavoritas.class );
                //  startActivity(intent);
            }
        });
    }
    private void enviarTokenRegistro(String token, String id_usuario_instagram){

        Log.d("TOKEN", token);
        Log.d("ID_USUARIO_INSTAGRAM", id_usuario_instagram);

        RestApiAdapterFirebase restApiAdapterFirebase = new RestApiAdapterFirebase();
        EndpointsFirebase endpointsFirebase = restApiAdapterFirebase.establecerConexionRestApi();
        Call<UsuarioResponse> usuarioResponseCall = endpointsFirebase.registrarusuario(token, id_usuario_instagram);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE", usuarioResponse.getId());
                Log.d("TOKEN_FIREBASE", usuarioResponse.getId_dispositivo());
                Log.d("ID_USUARIO_INSTAGRAM", usuarioResponse.getId_usuario_instagram());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });

    }



}
