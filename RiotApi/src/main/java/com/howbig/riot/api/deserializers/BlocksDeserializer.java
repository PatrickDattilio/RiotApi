package com.howbig.riot.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.RecommendedItems;
import com.howbig.riot.type.champion.Blocks;

import java.lang.reflect.Type;

/**
 * Created by Alex on 5/19/2014.
 */
public class BlocksDeserializer implements JsonDeserializer<Blocks> {

    @Override
    public Blocks deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = (JsonObject) json;
        Blocks blocks = new Blocks();
        blocks.type = obj.get("type").getAsString();
        if (obj.has("recMath") && obj.get("recMath").isJsonPrimitive()) {
            blocks.recMath = obj.get("recMath").getAsBoolean();
        } else {
            blocks.recMath = null;
        }
        blocks.recommendeditems = context.deserialize(obj.get("recommendeditems"), new TypeToken<RecommendedItems[]>() {
        }.getType());
        return blocks;
    }
}
