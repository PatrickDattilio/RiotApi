package com.howbig.riot.type.rune;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.google.gson.Gson;
import com.howbig.riot.persistence.DBHelper;
import com.howbig.riot.persistence.RiotContentProvider;
import com.howbig.riot.type.Gold;
import com.howbig.riot.type.item.ItemImage;
import com.howbig.riot.type.item.ItemStats;

import java.util.Map;

/**
 * Created by Alex on 5/29/2014.
 */
public class Rune {

    public static final Uri CONTENT_URI =
            RiotContentProvider.BASE_CONTENT_URI.buildUpon().appendPath("rune").build();

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/rune";

    public String name;
    public int id;
    public RuneInfo rune;
    public ItemImage image;
    public Gold gold;
    public String group;
    public String description;
    public String colloq;
    public String plaintext;
    public boolean consumed;
    public int stacks;
    public int depth;
    public boolean consumeOnFull;
    public String[] from;
    public String[] into;
    public int specialRecipe;
    public boolean inStore;
    public boolean hideFromAll;
    public String requiredChampion;
    public ItemStats stats;
    public String[] tags;
    public Map<String,Boolean> maps;

    public ContentValues getAsContentValues() {
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_ID, id);
        values.put(DBHelper.KEY_NAME, name);
        values.put(DBHelper.KEY_TAGS, tags.toString());
        values.put(DBHelper.KEY_IMAGE, image.full);
        values.put(DBHelper.KEY_TIER, rune.tier);
        values.put(DBHelper.KEY_JSON, new Gson().toJson(this));
        return values;
    }
}
