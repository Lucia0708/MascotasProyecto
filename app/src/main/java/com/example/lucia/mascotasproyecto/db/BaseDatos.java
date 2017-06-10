package com.example.lucia.mascotasproyecto.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.lucia.mascotasproyecto.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by Lucia on 26/05/2017.
 */

public class BaseDatos extends SQLiteOpenHelper{

    private Context context;
    public BaseDatos(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCrearTablaMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_MASCOTAS_ID        + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE    + " TEXT, " +
                ConstantesBaseDatos.TABLE_MASCOTAS_FOTO      + " INTEGER" +
                ")";
        Toast.makeText(context, "queryCrearTablamascota", Toast.LENGTH_SHORT).show();

        String queryCrearTablaLikesMascota = "CREATE TABLE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS + "(" +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + " INTEGER, " +
                ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + " INTEGER, " +
                "FOREIGN KEY (" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + ") " +
                "REFERENCES " + ConstantesBaseDatos.TABLE_MASCOTAS + "(" + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ")" +
                ")";

        String queryCrearTablaUserInstagram = "CREATE TABLE " + ConstantesBaseDatos.TABLE_USER_INSTAGRAM + "(" +
                ConstantesBaseDatos.TABLE_USER_INSTAGRAM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ConstantesBaseDatos.TABLE_USER_INSTAGRAM_NOMBRE + " TEXT " +
                ")";


        db.execSQL(queryCrearTablaMascota);
        db.execSQL(queryCrearTablaLikesMascota);
        db.execSQL(queryCrearTablaUserInstagram);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST" + ConstantesBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXIST" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS);
        db.execSQL("DROP TABLE IF EXIST" + ConstantesBaseDatos.TABLE_USER_INSTAGRAM);
        onCreate(db);
    }
    public ArrayList<Mascota> obtenerTodasLasMascotas(){

        ArrayList<Mascota> mascotas = new ArrayList<>();
        String query ="SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));

            String queryLikes =  "SELECT COUNT(" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + ") as likes " +
                    " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS +
                    " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + "=" + mascotaActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                mascotaActual.setRaiting(registrosLikes.getInt(0));
            }else {
                mascotaActual.setRaiting(0);
            }

            mascotas.add(mascotaActual);
        }
        db.close();
        return mascotas;
    }

    public void insertarMascota(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_MASCOTAS, null, contentValues);
        db.close();
    }

    public void insertarLikeMascota(ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS, null, contentValues);
        db.close();
    }

    public int obtenerLikesMascota(Mascota mascota){
        int likes = 0;
        String query =  "SELECT COUNT(" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + ")" +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + "=" + mascota.getId();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }
        db.close();
        return likes;
    }

    public ArrayList<Mascota> obtenerCincoMascotasFavoritas(){

        ArrayList<Mascota> mascotas = new ArrayList<>();
       // String queryMascotasFavoritas ="SELECT * FROM " + ConstantesBaseDatos.TABLE_MASCOTAS;
        String queryMascotasFavoritas =  "SELECT " + ConstantesBaseDatos.TABLE_MASCOTAS_ID + ", " + ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE + ", " + ConstantesBaseDatos.TABLE_MASCOTAS_FOTO + ", "
                         +  ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA + ", " + "SUM("+ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES+")"  +
                        " FROM " + ConstantesBaseDatos.TABLE_MASCOTAS + " INNER JOIN "
                     + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS
                      + " ON " + ConstantesBaseDatos.TABLE_MASCOTAS_ID + "=" + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA
                       + " GROUP BY " + ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA
                      +   " ORDER BY SUM("+ ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES + ") DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(queryMascotasFavoritas, null);

        int i = 0;
        while (registros.moveToNext()){
            Mascota mascotaActual = new Mascota();
            mascotaActual.setId(registros.getInt(0));
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setFoto(registros.getInt(2));
            mascotaActual.setRaiting(registros.getInt(4));
     //

            mascotas.add(mascotaActual);
            i = i + 1;
            if (i == 5) {
                while (registros.moveToNext()){}
            }
        }
        db.close();
        return mascotas;
    }

    public void insertarUserInstagram(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_USER_INSTAGRAM, null, contentValues);
        db.close();
    }

}
