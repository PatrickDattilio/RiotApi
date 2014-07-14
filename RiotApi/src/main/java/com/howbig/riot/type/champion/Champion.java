package com.howbig.riot.type.champion;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.google.gson.Gson;
import com.howbig.riot.persistence.DBHelper;
import com.howbig.riot.persistence.RiotContentProvider;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public class Champion {

    public static final Uri CONTENT_URI =
            RiotContentProvider.BASE_CONTENT_URI.buildUpon().appendPath("champion").build();

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/champion";

    public String id;
    public String name;
    public String title;
    public Image image;
    public Skin[] skins;
    public String lore;
    public String blurb;
    public String[] allytips;
    public String[] enemytips;
    public String[] tags;
    public String partype;
    public Info info;
    public Stats stats;
    public Spell[] spells;
    public Passive passive;
    public Recommended[] recommended;


    public ContentValues getAsContentValues() {
        Gson gson = new Gson();
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_ID, id);
        values.put(DBHelper.KEY_NAME, name);
        values.put(DBHelper.KEY_TITLE, title);

        values.put(DBHelper.KEY_IMAGE_FULL, image.full);
        values.put(DBHelper.KEY_IMAGE_SPRITE, image.sprite);
        values.put(DBHelper.KEY_X, image.x);
        values.put(DBHelper.KEY_Y, image.y);
        values.put(DBHelper.KEY_W, image.w);
        values.put(DBHelper.KEY_H, image.h);

        values.put(DBHelper.KEY_SKINS, gson.toJson(skins));
        values.put(DBHelper.KEY_H, image.h);


        return values;
    }
}
