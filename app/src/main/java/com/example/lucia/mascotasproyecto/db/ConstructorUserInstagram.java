package com.example.lucia.mascotasproyecto.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.lucia.mascotasproyecto.R;
import com.example.lucia.mascotasproyecto.pojo.Mascota;
import com.example.lucia.mascotasproyecto.pojo.MascotaInstagram;
import com.example.lucia.mascotasproyecto.pojo.UserInstagram;

import java.util.ArrayList;

/**
 * Created by Lucia on 06/06/2017.
 */

public class ConstructorUserInstagram {
    private Context context;

    public ConstructorUserInstagram(Context context)
    {
        this.context = context;
    }

    public ArrayList<MascotaInstagram> obtenerDatos(){

        ArrayList<MascotaInstagram> mascotasInstagram = new ArrayList<MascotaInstagram>();

        mascotasInstagram.add(new MascotaInstagram("R.drawable.connor1", " ", "Connor", 1));
        mascotasInstagram.add(new MascotaInstagram("R.drawable.connor2", " ", "Bolita", 2));
        mascotasInstagram.add(new MascotaInstagram("R.drawable.connor3", " ", "Bunny", 3));
        mascotasInstagram.add(new MascotaInstagram("R.drawable.connor4"," ", "Paloma", 4));
        mascotasInstagram.add(new MascotaInstagram("R.drawable.connor5"," ", "Shuly", 5));
        mascotasInstagram.add(new MascotaInstagram("R.drawable.connor6"," ", "Shuly", 4));
        mascotasInstagram.add(new MascotaInstagram("R.drawable.connor7"," ", "Shuly", 3));
        mascotasInstagram.add(new MascotaInstagram("R.drawable.connor8"," ", "Shuly", 2));
        mascotasInstagram.add(new MascotaInstagram("R.drawable.connor9"," ", "Shuly", 1));

        return mascotasInstagram;
    }

    public void guardarCuentaUser(UserInstagram userInstagram){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_USER_INSTAGRAM_NOMBRE, userInstagram.getUsername());
        db.insertarUserInstagram(contentValues);
    }
}
