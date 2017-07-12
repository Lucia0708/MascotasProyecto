package com.example.lucia.mascotasproyecto.restApi.Deserializador;

import com.example.lucia.mascotasproyecto.pojo.MascotaInstagram;
import com.example.lucia.mascotasproyecto.restApi.JsonKeys;
import com.example.lucia.mascotasproyecto.restApi.model.MascotaResponse;
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
 * Created by Lucia on 07/06/2017.
 */

public class MascotaDeserializador implements JsonDeserializer<MascotaResponse>{


    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaResponse.setMascotasInstagram(deserializarMascotaDeJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<MascotaInstagram> deserializarMascotaDeJson(JsonArray mascotaResponseData){
        ArrayList<MascotaInstagram> mascotaInstagrams = new ArrayList<>();
        for (int i=0; i < mascotaResponseData.size(); i++){
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();
            JsonObject userJson        = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id                  = userJson.get(JsonKeys.USER_ID).getAsString();
            String nombreCompleto      = userJson.get(JsonKeys.USER_FULLNAME).getAsString();
            String url_perfil          = userJson.get(JsonKeys.MEDIA_URL_PERFIL).getAsString();

            String id_image            = mascotaResponseDataObject.get(JsonKeys.ID_IMAGE).getAsString();
            JsonObject imageJson       = mascotaResponseDataObject.getAsJsonObject(JsonKeys.IMAGES);
            JsonObject stdResolutionJson = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto              = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson        =mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes                   = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            MascotaInstagram mascotaInstagramActual = new MascotaInstagram();
            mascotaInstagramActual.setId(id);
            mascotaInstagramActual.setUsername(nombreCompleto);
            mascotaInstagramActual.setUrlfotoperfil(url_perfil);
            mascotaInstagramActual.setId_foto(id_image);
            mascotaInstagramActual.setUrlfoto(urlFoto);
            mascotaInstagramActual.setLikes(likes);

            mascotaInstagrams.add(mascotaInstagramActual);
        }
        return mascotaInstagrams;

    }
}
