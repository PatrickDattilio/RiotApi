package com.howbig.riot.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.champion.Image;
import com.howbig.riot.type.champion.LevelTip;
import com.howbig.riot.type.champion.Spell;
import com.howbig.riot.type.Vars;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public class SpellDeserializer implements JsonDeserializer<Spell> {

    @Override
    public Spell deserialize(JsonElement json, java.lang.reflect.Type type,
            JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = (JsonObject) json;
        Spell spell = new Spell();

        spell.id = obj.get("id").getAsString();
        spell.name = obj.get("name").getAsString();
        spell.description = obj.get("description").getAsString();
        spell.tooltip = obj.get("tooltip").getAsString();
        spell.leveltip = context.deserialize(obj.get("leveltip"), new TypeToken<LevelTip>() {
        }.getType());
        spell.maxrank = obj.get("maxrank").getAsInt();
        spell.cooldown = context.deserialize(obj.get("cooldown"), new TypeToken<int[]>() {
        }.getType());
        spell.cooldownBurn = obj.get("cooldownBurn").getAsString();
        spell.cost = context.deserialize(obj.get("cost"), new TypeToken<int[]>() {
        }.getType());
        spell.costBurn = obj.get("costBurn").getAsString();
        spell.effect = context.deserialize(obj.get("effect"), new TypeToken<int[][]>() {
        }.getType());
        spell.effectBurn = context.deserialize(obj.get("effectBurn"), new TypeToken<String[]>() {
        }.getType());
        spell.vars = context.deserialize(obj.get("vars"), new TypeToken<Vars[]>() {
        }.getType());
        spell.costType = obj.get("costType").getAsString();
        if (obj.get("range").isJsonArray()) {
            spell.range = (int[]) context.deserialize(obj.get("range"), new TypeToken<int[]>() {
            }.getType());
        } else {
            spell.range = null;
        }
        spell.rangeBurn = obj.get("rangeBurn").getAsString();
        spell.image = context.deserialize(obj.get("image"), new TypeToken<Image>() {
        }.getType());
        spell.resource = obj.get("resource").getAsString();

        return spell;

    }
}
