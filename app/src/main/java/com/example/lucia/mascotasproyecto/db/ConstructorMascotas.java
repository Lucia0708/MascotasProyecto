package com.example.lucia.mascotasproyecto.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.lucia.mascotasproyecto.R;
import com.example.lucia.mascotasproyecto.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Lucia on 25/05/2017.
 */

public class ConstructorMascotas {

    private static final int LIKE = 1;
    private Context context;

    public ConstructorMascotas(Context context){
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){

        ArrayList<Mascota> mascotas = new ArrayList<>();
        BaseDatos db = new BaseDatos(context);
       insertarOchoMascotas(db);
        return db.obtenerTodasLasMascotas();

  }

        public void insertarOchoMascotas(BaseDatos db) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Bolita");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.bolita);
            db.insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Connor");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.connor);
            db.insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Bunny");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.bunny);
            db.insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Paloma");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.paloma);
            db.insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Shuly");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.shuly);
            db.insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Puerquito");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.puerquito);
            db.insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Dorado");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.dorado);
            db.insertarMascota(contentValues);

            contentValues = new ContentValues();
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Arrugas");
            contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.arrugas);
            db.insertarMascota(contentValues);
        }

    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();

        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES, LIKE);
        db.insertarLikeMascota(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascota(mascota);
    }

    public ArrayList<Mascota> obtenerDatosFavoritas (){

        /*ArrayList<Mascota> mascotas = new ArrayList<>();
        mascotas.add(new Mascota(R.drawable.shuly, "Shuly", 1));
        mascotas.add(new Mascota(R.drawable.paloma, "Paloma", 1));
        mascotas.add(new Mascota(R.drawable.bolita, "Bolita", 1));
        mascotas.add(new Mascota(R.drawable.bunny, "Bunny", 1));
        mascotas.add(new Mascota(R.drawable.connor, "Connor", 1));
        return mascotas;*/

        ArrayList<Mascota> mascotas = new ArrayList<>();
        BaseDatos db = new BaseDatos(context);
        return db.obtenerCincoMascotasFavoritas();

    }
}
