package com.example.lucia.mascotasproyecto.pojo;

/**
 * Created by Lucia on 06/06/2017.
 */

public class MascotaInstagram {
    private String id;
    private String urlfotoperfil;
    private String urlfoto;
    private String username;
    private int likes = 0;

    public MascotaInstagram(String urlfotoperfil, String urlfoto, String username, int likes) {
        this.urlfotoperfil = urlfotoperfil;
        this.urlfoto = urlfoto;
        this.username = username;
        this.likes = likes;
    }

    public MascotaInstagram() {
    }

    public String getUrlfotoperfil() {
        return urlfotoperfil;
    }

    public void setUrlfotoperfil(String urlfotoperfil) {
        this.urlfotoperfil = urlfotoperfil;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlfoto() {
        return urlfoto;
    }

    public void setUrlfoto(String urlfoto) {
        this.urlfoto = urlfoto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
