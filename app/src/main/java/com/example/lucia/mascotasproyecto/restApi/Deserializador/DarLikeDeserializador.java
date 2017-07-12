package com.example.lucia.mascotasproyecto.restApi.Deserializador;

import com.example.lucia.mascotasproyecto.pojo.UserInstagram;
import com.example.lucia.mascotasproyecto.restApi.JsonKeys;
import com.example.lucia.mascotasproyecto.restApi.model.LikeResponse;
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
 * Created by Lucia on 09/07/2017.
 */

public class DarLikeDeserializador implements JsonDeserializer<LikeResponse> {
    @Override
    public LikeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        LikeResponse likeResponse = gson.fromJson(json, LikeResponse.class);
        JsonObject likeResponseDataObject = json.getAsJsonObject().getAsJsonObject(JsonKeys.POST_DAR_LIKE);
        int code                   = likeResponseDataObject.get(JsonKeys.POST_DAR_LIKE_CODE).getAsInt();

        likeResponse.setCode(code);
        return likeResponse;
    }

}
