package com.howbig.riot.api;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.ItemGroup;
import com.howbig.riot.type.Item;
import com.howbig.riot.type.ItemTree;
import com.howbig.riot.type.ItemsJsonResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 5/24/2014.
 */
public class ItemJsonDeserializer implements JsonDeserializer<ItemsJsonResponse> {


    @Override
    public ItemsJsonResponse deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject obj = (JsonObject) jsonElement;
        ItemsJsonResponse  response= new ItemsJsonResponse();
        response.type=obj.get("type").getAsString();
        response.version=obj.get("version").getAsString();
        response.basic=jsonDeserializationContext.deserialize(obj.get("basic"), new TypeToken<Item>() {
        }.getType());
        response.data =  new ArrayList<Item>();

        JsonObject dataObj= obj.get("data").getAsJsonObject();
        Set<Map.Entry<String,JsonElement>> set=dataObj.entrySet();
        for(Map.Entry<String,JsonElement> entry :set){

            Item nextItem= jsonDeserializationContext.deserialize(entry.getValue(), new TypeToken<Item>(){}.getType());
            nextItem.id=Integer.parseInt(entry.getKey());
            response.data.add(nextItem);
        }
        response.groups= new ArrayList<ItemGroup>();
        ArrayList<ItemGroup> groupsObj=jsonDeserializationContext.deserialize(obj.get("groups"), new TypeToken<ArrayList<ItemGroup>>(){}.getType());
        for(ItemGroup currGroup:groupsObj){
            response.groups.add(currGroup);
        }
        response.tree= new ArrayList<ItemTree>();
        ArrayList<ItemTree> treeObj= jsonDeserializationContext.deserialize(obj.get("tree"), new TypeToken<ArrayList<ItemTree>>(){}.getType());
        for(ItemTree currTree:treeObj){
            response.tree.add(currTree);
        }





        return response;
    }
}
