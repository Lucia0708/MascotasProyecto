package com.example.lucia.mascotasproyecto.pojo;

/**
 * Created by Lucia on 18/05/2017.
 */

public class Mascota {
    private int id;
    private int foto;
    private String nombre;
    private int raiting;

    // clase POJO del proyecto

    public Mascota(int foto, String nombre, int raiting){
        this.foto = foto;
        this.nombre = nombre;
        this.raiting = raiting;

    }

    public Mascota() {

    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

