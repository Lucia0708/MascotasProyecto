package com.example.lucia.mascotasproyecto.menuactivity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucia.mascotasproyecto.MainActivity;
import com.example.lucia.mascotasproyecto.R;

public class ContactoActivity extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvEmail;
    private TextView tvComentario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void EnviarComentario(View view){

           Toast.makeText(ContactoActivity.this, "Enviar Comentario", Toast.LENGTH_SHORT).show();

/*            String email = tvEmail.getText().toString();
            Intent emailIntent = new Intent((Intent.ACTION_SEND));
            emailIntent.setData(Uri.parse("mailto:"));
            emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
            emailIntent.setType("message/rfc822");
            startActivity(Intent.createChooser(emailIntent, "Email "));
*/


/*        String dat_nombre;
        String dat_email;
        String dat_comentario;


        //https://github.com/steveholt55/JavaMail-API-Android.git

        Intent intent = new Intent(ContactoActivity.this, email.class);

        TextInputEditText textInputEditText1 = (TextInputEditText) findViewById(R.id.et_Nombre);
        TextInputEditText textInputEditText3 = (TextInputEditText) findViewById(R.id.et_Email);
        TextInputEditText textInputEditText4 = (TextInputEditText) findViewById(R.id.et_Comentario);

        dat_nombre = textInputEditText1.getText().toString();
        dat_email = textInputEditText3.getText().toString();
        dat_comentario = textInputEditText4.getText().toString();

        intent.putExtra(getResources().getString(R.string.pnombre), dat_nombre);
        intent.putExtra(getResources().getString(R.string.pemail), dat_email);
        intent.putExtra(getResources().getString(R.string.pcomentario), dat_comentario);

// utilize esta llamada de startActivity porque asegura un canal y nos manda una respuesta de resultado.


        startActivityForResult(intent);
        */
    }

}
