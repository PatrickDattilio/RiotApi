package com.howbig.riot.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.Gold;
import com.howbig.riot.type.Item.Item;
import com.howbig.riot.type.Item.ItemRune;
import com.howbig.riot.type.Item.ItemStats;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 5/22/2014.
 */
public class ItemDeserializer implements JsonDeserializer<Item> {

    @Override
    public Item deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = (JsonObject) json;
        Item item = new Item();
        item.name = obj.get("name").getAsString();
        item.itemRune = context.deserialize(obj.get("itemRune"), new TypeToken<ItemRune>() {
        }.getType());
        item.gold = context.deserialize(obj.get("gold"), new TypeToken<Gold>() {
        }.getType());
        if (obj.has("group"))
            item.group = obj.get("group").getAsString();
        item.description = obj.get("description").getAsString();
        if (obj.has("colloq") && !obj.get("colloq").isJsonNull())
            item.colloq = obj.get("colloq").getAsString();
        if (!obj.get("plaintext").isJsonNull()) {
            item.plaintext = obj.get("plaintext").getAsString();
        }
        if (obj.has("consumed"))
            item.consumed = obj.get("consumed").getAsBoolean();
        if (obj.has("stacks"))
            item.stacks = obj.get("stacks").getAsInt();
        if (obj.has("depth"))
            item.depth = obj.get("depth").getAsInt();
        if (obj.has("consumeOnFull"))
            item.consumeOnFull = obj.get("consumeOnFull").getAsBoolean();
        item.from = context.deserialize(obj.get("from"), new TypeToken<String[]>() {
        }.getType());
        item.into = context.deserialize(obj.get("into"), new TypeToken<String[]>() {
        }.getType());
        if (obj.has("specialRecipe"))
            item.specialRecipe = obj.get("specialRecipe").getAsInt();
        if (obj.has("inStore"))
            item.inStore = obj.get("inStore").getAsBoolean();
        if (obj.has("requiredChampion"))
            item.requiredChampion = obj.get("requiredChampion").getAsString();
        item.stats = context.deserialize(obj.get("stats"), new TypeToken<ItemStats>() {
        }.getType());
        item.tags = context.deserialize(obj.get("tags"), new TypeToken<String[]>() {
        }.getType());
        if (obj.has("maps")) {
            item.maps = new HashMap<String, Boolean>();
            JsonObject mapObj = obj.get("maps").getAsJsonObject();
            Set<Map.Entry<String, JsonElement>> set = mapObj.entrySet();
            for (Map.Entry<String, JsonElement> entry : set) {
                item.maps.put(entry.getKey(), entry.getValue().getAsBoolean());
            }
        }
        return item;
    }
}