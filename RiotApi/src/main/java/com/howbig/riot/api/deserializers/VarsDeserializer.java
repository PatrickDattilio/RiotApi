package com.howbig.riot.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.Vars;

import java.lang.reflect.Type;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public class VarsDeserializer implements JsonDeserializer<Vars> {

    @Override
    public Vars deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = (JsonObject) json;
        Vars vars = new Vars();
        vars.link = obj.get("link").getAsString();
        if (obj.get("coeff").isJsonArray()) {
            vars.coeff = (int[]) context.deserialize(obj.get("coeff"), new TypeToken<int[]>() {
            }.getType());
        } else {
            vars.coeff = new int[]{obj.get("coeff").getAsInt()};
        }
        vars.key = obj.get("key").getAsString();
        return vars;
    }
}
