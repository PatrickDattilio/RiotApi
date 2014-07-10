package com.howbig.riot.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.howbig.riot.type.mastery.Mastery;
import com.howbig.riot.type.mastery.MasteryJsonResponse;
import com.howbig.riot.type.mastery.MasteryTreeElement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Alex on 6/21/2014.
 */
public class MasteryJsonDeserializer implements JsonDeserializer<MasteryJsonResponse>{

    @Override
    public MasteryJsonResponse deserialize(JsonElement jsonElement, Type typeOfT,JsonDeserializationContext context) throws JsonParseException {
        JsonObject obj = (JsonObject) jsonElement;
        MasteryJsonResponse response = new MasteryJsonResponse();
        MasteryTreeElement [] arrTemp;
        ArrayList<ArrayList<MasteryTreeElement>> listTemp;

        response.type = obj.get("type").getAsString();
        response.version = obj.get("version").getAsString();
        JsonObject tree =(JsonObject) obj.get("tree");

        int count = 0;
        Set<Map.Entry<String,JsonElement>> treeSet =tree.entrySet();
        for(Map.Entry<String,JsonElement> entry :treeSet) {
            //Arraylist of arrays of MasteryTreeElements
            JsonArray jsonArray= tree.getAsJsonArray(entry.getKey());
       /*     ArrayList<MasteryTreeElement[]> treeElements = new ArrayList<MasteryTreeElement[]>();
            ArrayList<MasteryTreeElement> arrayList = new ArrayList<MasteryTreeElement>();
            for(JsonElement ell : jsonArray){
               for(JsonElement e : ell.getAsJsonArray()){
                   MasteryTreeElement l =context.deserialize(e,new TypeToken<MasteryTreeElement>(){}.getClass());
                   arrayList.add(l);
               }

             MasteryTreeElement[] lists = new MasteryTreeElement[arrayList.size()];
                  lists=arrayList.toArray(lists);
              treeElements.add(lists);
            }*/
            ArrayList<ArrayList<MasteryTreeElement>> lists =context.deserialize(entry.getValue(),new TypeToken<ArrayList<ArrayList<MasteryTreeElement>>>(){}.getClass());
            listTemp= new ArrayList<ArrayList<MasteryTreeElement>>();
          /*  String key =entry.getKey();
            for (MasteryTreeElement[] currArr : treeElements) {
                arrTemp = new MasteryTreeElement[6];
                count=0;
                for (MasteryTreeElement currElement : currArr) {
                   arrTemp[count]=currElement;
                    count+=1;
                }
                listTemp.add(arrTemp);
            }*/
            if(entry.getKey().equals("Offense"))
                response.tree.Offense= lists;
            else if(entry.getKey().equals("Defense"))
                response.tree.Defense = lists;
            else if(entry.getValue().equals("Utility"))
                response.tree.Utility = lists;
        }

        JsonObject dataObj=obj.get("data").getAsJsonObject();
        Set<Map.Entry<String,JsonElement>> set= dataObj.entrySet();
        for(Map.Entry<String,JsonElement> entry: set){
            Mastery nextMastery= context.deserialize(entry.getValue(),new TypeToken<Mastery>(){}.getClass());
            nextMastery.id=Integer.parseInt(entry.getKey());
            response.data.add(nextMastery);
        }

        return response;

    }

}
