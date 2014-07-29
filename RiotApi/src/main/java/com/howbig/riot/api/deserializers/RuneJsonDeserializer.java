package com.howbig.riot.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.rune.Rune;
import com.howbig.riot.type.rune.RuneJsonResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 5/29/2014.
 */
public class RuneJsonDeserializer implements JsonDeserializer<RuneJsonResponse> {
    public RuneJsonResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = (JsonObject) jsonElement;
        RuneJsonResponse response = new RuneJsonResponse();
        response.type = obj.get("type").getAsString();
        response.version = obj.get("version").getAsString();
        response.basic = jsonDeserializationContext.deserialize(obj.get("basic"), new TypeToken<Rune>() {
        }.getType());
        response.data = new ArrayList<Rune>();
        JsonObject dataObj = obj.get("data").getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> set = dataObj.entrySet();
        for (Map.Entry<String, JsonElement> entry : set) {
            Rune rune = jsonDeserializationContext.deserialize(entry.getValue(), new TypeToken<Rune>() {
            }.getType());
            rune.id = Integer.parseInt(entry.getKey());
            response.data.add(rune);
        }
        return response;
    }
}
