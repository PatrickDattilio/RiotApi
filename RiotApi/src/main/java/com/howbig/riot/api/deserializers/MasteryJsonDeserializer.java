package com.howbig.riot.api.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.mastery.Mastery;
import com.howbig.riot.type.mastery.MasteryJsonResponse;
import com.howbig.riot.type.mastery.MasteryTree;
import com.howbig.riot.type.mastery.MasteryTreeElement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 6/21/2014.
 */
public class MasteryJsonDeserializer implements JsonDeserializer<MasteryJsonResponse> {

    @Override
    public MasteryJsonResponse deserialize(JsonElement jsonElement, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = (JsonObject) jsonElement;
        MasteryJsonResponse response = new MasteryJsonResponse();
        response.tree = new MasteryTree();
        response.type = obj.get("type").getAsString();
        response.version = obj.get("version").getAsString();
        JsonObject tree = (JsonObject) obj.get("tree");

        Set<Map.Entry<String, JsonElement>> treeSet = tree.entrySet();
        for (Map.Entry<String, JsonElement> entry : treeSet) {
            ArrayList<ArrayList<MasteryTreeElement>> lists = context.deserialize(entry.getValue(), new TypeToken<ArrayList<ArrayList<MasteryTreeElement>>>() {
            }.getType());

            if (entry.getKey().equals("Offense"))
                response.tree.Offense = lists;
            else if (entry.getKey().equals("Defense"))
                response.tree.Defense = lists;
            else if (entry.getKey().equals("Utility"))
                response.tree.Utility = lists;
        }

        JsonObject dataObj = obj.get("data").getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> set = dataObj.entrySet();
        response.data = new ArrayList<Mastery>(set.size());
        for (Map.Entry<String, JsonElement> entry : set) {
            Mastery nextMastery = context.deserialize(entry.getValue(), new TypeToken<Mastery>() {
            }.getType());
            response.data.add(nextMastery);
        }

        return response;

    }

}
