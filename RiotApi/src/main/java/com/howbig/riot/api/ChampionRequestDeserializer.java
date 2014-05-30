package com.howbig.riot.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.champion.Champion;
import com.howbig.riot.type.champion.ChampionJsonResponse;

import java.lang.reflect.Type;
import java.util.Map;


/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public class ChampionRequestDeserializer implements JsonDeserializer<ChampionJsonResponse> {

    @Override
    public ChampionJsonResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        ChampionJsonResponse response = new ChampionJsonResponse();
        JsonObject obj = jsonElement.getAsJsonObject();
        response.type = obj.get("type").getAsString();
        response.format = obj.get("format").getAsString();
        response.version = obj.get("version").getAsString();

        obj = obj.get("data").getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
            response.data = context.deserialize(entry.getValue(), new TypeToken<Champion>() {
            }.getType());
        }

        return response;
    }
}
