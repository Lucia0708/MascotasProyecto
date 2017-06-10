package com.example.lucia.mascotasproyecto;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.lucia.mascotasproyecto.Instagram.ConfigurarCueInstagramActivity;
import com.example.lucia.mascotasproyecto.Instagram.PerfilCuentaInstagramActivity;
import com.example.lucia.mascotasproyecto.MascotasFavoritas.MascotasFavoritas;
import com.example.lucia.mascotasproyecto.adapter.PageAdapter;
import com.example.lucia.mascotasproyecto.fragments.PerfilFragment;
import com.example.lucia.mascotasproyecto.fragments.lista_mascotas_fragment;
import com.example.lucia.mascotasproyecto.menuactivity.AcercadeActivity;
import com.example.lucia.mascotasproyecto.menuactivity.ContactoActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Clase principal del Proyecto

    public static String USERNAME = "";
    public static String IDUSERNAME = "";
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
            case R.id.mConfigurarCuentaInstagram:
                Intent intentConfigurarCuentaInstagram = new Intent(this, ConfigurarCueInstagramActivity.class);
                startActivity(intentConfigurarCuentaInstagram);
                break;
            case R.id.mbelliccat:
                Intent i1 = new Intent(this, PerfilCuentaInstagramActivity.class);
                i1.putExtra(getResources().getString(R.string.pUsername), getResources().getString(R.string.belliccat));
                startActivity(i1);
                break;
            case R.id.moreothemightycat:
                Intent i2 = new Intent(this, PerfilCuentaInstagramActivity.class);
                i2.putExtra(getResources().getString(R.string.pUsername), getResources().getString(R.string.oreothemightycat));
                startActivity(i2);
                break;
            case R.id.mperritoconnor:
                Intent i3 = new Intent(this, PerfilCuentaInstagramActivity.class);
                i3.putExtra(getResources().getString(R.string.pUsername), getResources().getString(R.string.perritoconnor));
                startActivity(i3);
                break;
            case R.id.mzach_dog_24:
                Intent i4 = new Intent(this, PerfilCuentaInstagramActivity.class);
                i4.putExtra(getResources().getString(R.string.pUsername), getResources().getString(R.string.zach_dog_24));
                startActivity(i4);
                break;
            case R.id.mrufitomurciagonzalez:
                Intent i5 = new Intent(this, PerfilCuentaInstagramActivity.class);
                i5.putExtra(getResources().getString(R.string.pUsername), getResources().getString(R.string.rufitomurciagonzalez));
                startActivity(i5);
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
}
