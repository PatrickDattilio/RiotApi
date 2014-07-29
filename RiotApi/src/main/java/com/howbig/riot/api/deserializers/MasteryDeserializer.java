package com.howbig.riot.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.item.ItemImage;
import com.howbig.riot.type.mastery.Mastery;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Alex on 6/21/2014.
 */
public class MasteryDeserializer implements JsonDeserializer<Mastery> {

    @Override
    public Mastery deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = (JsonObject) json;
        Mastery mastery = new Mastery();
        mastery.id = obj.get("id").getAsInt();
        mastery.name = obj.get("name").getAsString();
        mastery.description = context.deserialize(obj.get("description"), new TypeToken<ArrayList<String>>() {
        }.getType());
        mastery.image = context.deserialize(obj.get("image"), new TypeToken<ItemImage>() {
        }.getType());
        mastery.ranks = obj.get("ranks").getAsInt();
        mastery.prereq = obj.get("prereq").getAsString();
        return mastery;
    }
}
