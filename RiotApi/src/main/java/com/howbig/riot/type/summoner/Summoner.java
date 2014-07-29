package com.howbig.riot.type.summoner;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.google.gson.Gson;
import com.howbig.riot.persistence.DBHelper;
import com.howbig.riot.persistence.RiotContentProvider;
import com.howbig.riot.type.Vars;
import com.howbig.riot.type.item.ItemImage;

/**
 * Created by Alex on 7/11/2014.
 */
public class Summoner {

    public static final Uri CONTENT_URI =
            RiotContentProvider.BASE_CONTENT_URI.buildUpon().appendPath("summoner").build();

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/summoner";

    public String id;
    public String name;
    public String description;
    public String tooltip;
    public int maxrank;
    public LevelTip leveltip;
    public int[] cooldown;
    public String cooldownBurn;
    public int [] cost;
    public String costBurn;
    public Integer[][] effect;
    public String [] effectBurn;
    public Vars [] vars;
    public String key;
    public int summonerLevel;
    public String [] modes;
    public String costType;
    public Object range;
    public String rangeBurn;
    public ItemImage image;
    public String resource;

    public ContentValues getAsContentValues() {
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_ID, id);
        values.put(DBHelper.KEY_NAME, name);
        values.put(DBHelper.KEY_IMAGE, image.full);
        values.put(DBHelper.KEY_JSON, new Gson().toJson(this));
        return values;
    }

}
