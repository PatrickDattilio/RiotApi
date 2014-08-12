package com.howbig.riot.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.Vars;
import com.howbig.riot.type.item.ItemImage;
import com.howbig.riot.type.summoner.LevelTip;
import com.howbig.riot.type.summoner.Summoner;

import java.lang.reflect.Type;

/**
 * Created by Alex on 7/11/2014.
 */
public class SummonerDeserializer implements JsonDeserializer<Summoner> {

    @Override
    public Summoner deserialize(JsonElement json, Type typeofT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = (JsonObject) json;
        Summoner summoner = new Summoner();
        summoner.id = obj.get("id").getAsString();
        summoner.name = obj.get("name").getAsString();
        summoner.description = obj.get("description").getAsString();
        summoner.tooltip = obj.get("tooltip").getAsString();
        summoner.leveltip = context.deserialize(obj.get("leveltip"), new TypeToken<LevelTip>() {
        }.getType());
        summoner.maxrank = obj.get("maxrank").getAsInt();
        summoner.cooldown = context.deserialize(obj.get("cooldown"), new TypeToken<int[]>() {
        }.getType());
        summoner.cooldownBurn = obj.get("cooldownBurn").getAsString();
        summoner.cost = context.deserialize(obj.get("cost"), new TypeToken<int[]>() {
        }.getType());
        summoner.costBurn = obj.get("costBurn").getAsString();
        summoner.effect = context.deserialize(obj.get("effect"), new TypeToken<Integer[][]>() {
        }.getType());
        summoner.effectBurn = context.deserialize(obj.get("effectBurn"), new TypeToken<String[]>() {
        }.getType());
        summoner.vars = context.deserialize(obj.get("vars"), new TypeToken<Vars[]>() {
        }.getType());
        summoner.key = obj.get("key").getAsString();
        summoner.summonerLevel = obj.get("summonerLevel").getAsInt();
        summoner.modes = context.deserialize(obj.get("modes"), new TypeToken<String[]>() {
        }.getType());
        summoner.costType = obj.get("costType").getAsString();
        if (obj.get("range").isJsonArray()) {
            summoner.range = context.deserialize(obj.get("range"), new TypeToken<int[]>() {
            }.getType());
        } else {
            summoner.range = new int[]{};
        }
        summoner.rangeBurn = obj.get("rangeBurn").getAsString();
        summoner.image = context.deserialize(obj.get("image"), new TypeToken<ItemImage>() {
        }.getType());
        summoner.resource = obj.get("resource").getAsString();

        return summoner;
    }
}
