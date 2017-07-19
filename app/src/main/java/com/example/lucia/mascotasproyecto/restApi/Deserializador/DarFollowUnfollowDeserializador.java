package com.example.lucia.mascotasproyecto.restApi.Deserializador;

import com.example.lucia.mascotasproyecto.restApi.JsonKeys;
import com.example.lucia.mascotasproyecto.restApi.model.FollowResponse;
import com.example.lucia.mascotasproyecto.restApi.model.LikeResponse;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by Lucia on 17/07/2017.
 */

public class DarFollowUnfollowDeserializador implements JsonDeserializer<FollowResponse> {
    @Override
    public FollowResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        FollowResponse followResponse = gson.fromJson(json, FollowResponse.class);
        JsonObject followResponseDataObject = json.getAsJsonObject().getAsJsonObject(JsonKeys.POST_FOLLOW_UNFOLLOW_DATA);
        String outgoing_status                 = followResponseDataObject.get(JsonKeys.POST_FOLLOW_UNFOLLOW).getAsString();

        followResponse.setOutgoing_status(outgoing_status);
        return followResponse;
    }
}
