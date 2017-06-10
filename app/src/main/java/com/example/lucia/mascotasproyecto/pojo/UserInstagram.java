package com.example.lucia.mascotasproyecto.pojo;

/**
 * Created by Lucia on 05/06/2017.
 */

public class UserInstagram {
    private String id;
    private String username;

    public UserInstagram() {
    }

    public UserInstagram(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
