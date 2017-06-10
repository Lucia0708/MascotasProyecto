package com.example.lucia.mascotasproyecto.menuactivity;

import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lucia.mascotasproyecto.MainActivity;
import com.example.lucia.mascotasproyecto.R;
import com.example.lucia.mascotasproyecto.Utilerias.Mail;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

public class ContactoActivity extends AppCompatActivity  implements View.OnClickListener{

    private TextInputEditText tiNombre;
    private TextInputEditText tiEmail;
    private TextInputEditText tiComentario;
    private Button btnEnviarComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        // obtener el action bar
        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(miActionBar);

        //habiliatar la navegacion de regreso al padre
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tiNombre = (TextInputEditText)findViewById(R.id.et_Nombre);
        tiEmail = (TextInputEditText)findViewById(R.id.et_Email);
        tiComentario = (TextInputEditText)findViewById(R.id.et_Comentario);

        btnEnviarComentario = (Button) findViewById(R.id.btn_EnviarComentario);
        btnEnviarComentario.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.btn_EnviarComentario:
                EnviarComentario();
                break;
    }
    }

    public void EnviarComentario(){

            String[] recipients = { tiEmail.getText().toString() };
            SendEmailAsyncTask email = new SendEmailAsyncTask();
            email.activity = this;
            email.m = new Mail(null);
            email.m.set_from("no-reply@nodomain.com");
            email.m.setBody(tiComentario.getText().toString());
            email.m.set_to(recipients);
            email.m.set_subject(tiNombre.getText().toString());
            email.execute();

        }

    public void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
        Mail m;
        ContactoActivity activity;

        public SendEmailAsyncTask() {
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                m.send();
                return true;
            } catch (AuthenticationFailedException e) {
                activity.displayMessage(e.getMessage());
                return false;
            } catch (MessagingException e) {
                activity.displayMessage(e.getMessage());
                return false;
            } catch (Exception e) {
                activity.displayMessage(e.getMessage());
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean){
                activity.displayMessage("Mensaje enviado!");
                //Limpiar los campos dado que el mensaje se envio satisfactoriamente
                tiNombre.setText("");
                tiEmail.setText("");
                tiComentario.setText("");

            }else{
                activity.displayMessage("Error de envio :(");
            }
        }
    }


}
