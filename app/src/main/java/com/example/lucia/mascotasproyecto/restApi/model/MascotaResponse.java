package com.example.lucia.mascotasproyecto.restApi.model;

import com.example.lucia.mascotasproyecto.pojo.MascotaInstagram;

import java.util.ArrayList;

/**
 * Created by Lucia on 07/06/2017.
 */

public class MascotaResponse {

    ArrayList<MascotaInstagram> mascotasInstagram;

    public ArrayList<MascotaInstagram> getMascotasInstagram()
    {
        return mascotasInstagram;
    }

    public void setMascotasInstagram(ArrayList<MascotaInstagram> mascotasInstagram) {
        this.mascotasInstagram = mascotasInstagram;
    }
}
