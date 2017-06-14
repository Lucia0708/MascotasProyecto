package com.example.lucia.mascotasproyecto.restApi.Deserializador;

import com.example.lucia.mascotasproyecto.pojo.Mascota;
import com.example.lucia.mascotasproyecto.pojo.MascotaInstagram;
import com.example.lucia.mascotasproyecto.pojo.UserInstagram;
import com.example.lucia.mascotasproyecto.restApi.JsonKeys;
import com.example.lucia.mascotasproyecto.restApi.model.MascotaResponse;
import com.example.lucia.mascotasproyecto.restApi.model.UserInstagramResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Lucia on 13/06/2017.
 */

public class followsSelfDeserializador implements JsonDeserializer<MascotaResponse> {
    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.ID_RESPONSE_ARRAY);

        mascotaResponse.setMascotasInstagram(deserializarUserInstagramDeJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<MascotaInstagram> deserializarUserInstagramDeJson(JsonArray mascotaResponseData){
        ArrayList<MascotaInstagram> mascotaInstagrams = new ArrayList<>();

        for (int i=0; i < mascotaResponseData.size(); i++){
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            String id                  = mascotaResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String username            = mascotaResponseDataObject.get(JsonKeys.USER_USERNAME).getAsString();
            String profile_picture     = mascotaResponseDataObject.get(JsonKeys.MEDIA_URL_PERFIL).getAsString();

            MascotaInstagram mascotaInstagramActual = new MascotaInstagram();
            mascotaInstagramActual.setId(id);
            mascotaInstagramActual.setUsername(username);
            mascotaInstagramActual.setUrlfotoperfil(profile_picture);
            mascotaInstagramActual.setLikes(0);


            mascotaInstagrams.add(mascotaInstagramActual);
        }
        return mascotaInstagrams;

    }
}
