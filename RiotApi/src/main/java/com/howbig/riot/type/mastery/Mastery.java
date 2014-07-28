package com.howbig.riot.type.mastery;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import com.howbig.riot.persistence.DBHelper;
import com.howbig.riot.persistence.RiotContentProvider;
import com.howbig.riot.type.item.ItemImage;

import java.util.ArrayList;

/**
 * Created by Alex on 6/21/2014.
 */
public class Mastery {

    public static final Uri CONTENT_URI =
            RiotContentProvider.BASE_CONTENT_URI.buildUpon().appendPath("mastery").build();

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/mastery";

    public int id;
    public String name;
    public ArrayList<String> description;
    public ItemImage image;
    public int ranks;
    public String prereq;


    public ContentValues getAsContentValues() {
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_ID, id);
        values.put(DBHelper.KEY_NAME, name);
        String description = "";
        for (String desc : this.description)
            description += desc + ",";
        description = description.substring(0, description.length() - 1);
        values.put(DBHelper.KEY_DESCRIPTION, description);
        values.put(DBHelper.KEY_IMAGE, image.full);
        values.put(DBHelper.KEY_RANKS, ranks);
        values.put(DBHelper.KEY_PREREQ, prereq);
        return values;
    }
}
