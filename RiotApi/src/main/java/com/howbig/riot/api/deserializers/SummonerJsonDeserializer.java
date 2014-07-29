package com.howbig.riot.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.summoner.Summoner;
import com.howbig.riot.type.summoner.SummonerJsonResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 7/11/2014.
 */
public class SummonerJsonDeserializer implements JsonDeserializer<SummonerJsonResponse> {

    @Override
    public SummonerJsonResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = (JsonObject) json;
        SummonerJsonResponse response = new SummonerJsonResponse();
        response.type = obj.get("type").getAsString();
        response.version = obj.get("version").getAsString();
        response.data = new ArrayList<Summoner>();
        JsonObject dataObject = obj.get("data").getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> set = dataObject.entrySet();
        for (Map.Entry<String, JsonElement> entry : set) {
            Summoner summoner = context.deserialize(entry.getValue(), new TypeToken<Summoner>() {
            }.getType());
            response.data.add(summoner);
        }
        return response;
    }
}
