package com.howbig.riot.api;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.Gold;
import com.howbig.riot.type.item.ItemImage;
import com.howbig.riot.type.item.ItemStats;
import com.howbig.riot.type.rune.Rune;
import com.howbig.riot.type.rune.RuneInfo;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 5/29/2014.
 */
public class RuneDeserializer implements JsonDeserializer<Rune> {

    @Override
    public Rune deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = (JsonObject) json;
        Rune rune = new Rune();
        rune.name = obj.get("name").getAsString();
        rune.rune = context.deserialize(obj.get("rune"), new TypeToken<RuneInfo>() {
        }.getType());
        rune.image = context.deserialize(obj.get("image"), new TypeToken<ItemImage>() {
        }.getType());
        rune.gold = context.deserialize(obj.get("gold"), new TypeToken<Gold>() {
        }.getType());
        if (obj.has("group"))
            rune.group = obj.get("group").getAsString();
        rune.description = obj.get("description").getAsString();
        if (!obj.get("colloq").isJsonNull()) {
            rune.colloq = obj.get("colloq").getAsString();
        }
        if (!obj.get("plaintext").isJsonNull()) {
            rune.plaintext = obj.get("plaintext").getAsString();
        }
        if (obj.has("consumed"))
            rune.consumed = obj.get("consumed").getAsBoolean();
        if (obj.has("stacks"))
            rune.stacks = obj.get("stacks").getAsInt();
        if (obj.has("depth"))
            rune.depth = obj.get("depth").getAsInt();
        if (obj.has("consumeOnFull"))
            rune.consumeOnFull = obj.get("consumeOnFull").getAsBoolean();
        rune.from = context.deserialize(obj.get("from"), new TypeToken<String[]>() {
        }.getType());
        rune.into = context.deserialize(obj.get("into"), new TypeToken<String[]>() {
        }.getType());
        if (obj.has("specialRecipe"))
            rune.specialRecipe = obj.get("specialRecipe").getAsInt();
        if (obj.has("inStore"))
            rune.inStore = obj.get("inStore").getAsBoolean();
        if (obj.has("hideFromAll"))
            rune.hideFromAll = obj.get("hideFromAll").getAsBoolean();
        if (obj.has("requiredChampion"))
            rune.requiredChampion = obj.get("requiredChampion").getAsString();
        rune.stats = context.deserialize(obj.get("stats"), new TypeToken<ItemStats>() {
        }.getType());
        rune.tags = context.deserialize(obj.get("tags"), new TypeToken<String[]>() {
        }.getType());
        if (obj.has("maps")) {
            rune.maps = new HashMap<String, Boolean>();
            JsonObject mapObj = obj.get("maps").getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> set = mapObj.entrySet();
            for (Map.Entry<String, JsonElement> entry : set) {
                rune.maps.put(entry.getKey(), entry.getValue().getAsBoolean());
            }
        }
        return rune;
    }
}
