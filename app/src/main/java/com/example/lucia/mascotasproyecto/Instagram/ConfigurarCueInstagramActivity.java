package com.example.lucia.mascotasproyecto.Instagram;

import android.content.ContentValues;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lucia.mascotasproyecto.MainActivity;
import com.example.lucia.mascotasproyecto.MascotasFavoritas.MascotasFavoritas;
import com.example.lucia.mascotasproyecto.R;
import com.example.lucia.mascotasproyecto.db.BaseDatos;
import com.example.lucia.mascotasproyecto.db.ConstantesBaseDatos;
import com.example.lucia.mascotasproyecto.db.ConstructorMascotas;
import com.example.lucia.mascotasproyecto.db.ConstructorUserInstagram;
import com.example.lucia.mascotasproyecto.pojo.UserInstagram;

public class ConfigurarCueInstagramActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText tiUsuario;
    private Button btnGuardarCuenta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar_cue_instagram);

         // obtener el action bar
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        //habiliatar la navegacion de regreso al padre
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tiUsuario = (TextInputEditText) findViewById(R.id.et_Usuario);
        btnGuardarCuenta = (Button) findViewById(R.id.btn_GuardarCuenta);
        btnGuardarCuenta.setOnClickListener(this);
    }

        @Override
        public void onClick (View v){
            int viewId = v.getId();
            switch (viewId) {
                case R.id.btn_GuardarCuenta:
//                    GuardarCuenta();

                    Intent intentPerfilInstagram = new Intent(ConfigurarCueInstagramActivity.this,PerfilCuentaInstagramActivity.class);
                    intentPerfilInstagram.putExtra(getResources().getString(R.string.pUsername), tiUsuario.getText().toString());
                    MainActivity.USERNAME=tiUsuario.getText().toString();
                    startActivity(intentPerfilInstagram);
                    break;
            }
        }

    public void GuardarCuenta () {

         Toast.makeText(this, "Guardar cuenta: " + tiUsuario.getText() , Toast.LENGTH_SHORT).show();

        UserInstagram userInstagram = new UserInstagram();
        userInstagram.setUsername(tiUsuario.getText().toString());
        ConstructorUserInstagram constructorUserInstagram = new ConstructorUserInstagram(this);
        constructorUserInstagram.guardarCuentaUser(userInstagram);

        //nombre_usuario = constructorUserInstagram.obtenerLikesMascota(UserInstagram);

       // Toast.makeText(this, "Guardar cuenta: " + tiUsuario.getText() , Toast.LENGTH_SHORT).show();
    }
}





