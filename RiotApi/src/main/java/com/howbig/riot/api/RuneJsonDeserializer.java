package com.howbig.riot.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.rune.Rune;
import com.howbig.riot.type.rune.RuneJsonResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Alex on 5/29/2014.
 */
public class RuneJsonDeserializer {
    public RuneJsonResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = (JsonObject) jsonElement;
        RuneJsonResponse response = new RuneJsonResponse();
        response.type= obj.get("type").getAsString();
        response.version= obj.get("version").getAsString();
        response.basic= jsonDeserializationContext.deserialize(obj.get("basic"), new TypeToken<Rune>() {}.getType());
        response.data= new ArrayList<Rune>();
        ArrayList<Rune> dataObj= jsonDeserializationContext.deserialize(obj.get("data"), new TypeToken<ArrayList<Rune>>(){}.getType());
        for(Rune rune: dataObj){
            response.data.add(rune);
        }
        return response;
    }
}
