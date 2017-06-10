package com.example.lucia.mascotasproyecto.restApi.model;

import com.example.lucia.mascotasproyecto.pojo.UserInstagram;

import java.util.ArrayList;

/**
 * Created by Lucia on 09/06/2017.
 */

public class UserInstagramResponse {

    ArrayList<UserInstagram> userInstagrams;

    public ArrayList<UserInstagram> getUserInstagrams() {
        return userInstagrams;
    }

    public void setUserInstagrams(ArrayList<UserInstagram> userInstagrams) {
        this.userInstagrams = userInstagrams;
    }
}
