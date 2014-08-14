package com.howbig.riot.type.item;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.howbig.riot.persistence.DBHelper;
import com.howbig.riot.persistence.RiotContentProvider;
import com.howbig.riot.type.Gold;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 5/21/2014.
 */
public class Item implements Parcelable {

    public static final Uri CONTENT_URI =
            RiotContentProvider.BASE_CONTENT_URI.buildUpon().appendPath("item").build();

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/item";

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public int id;
    public String name;
    public ItemRune itemRune;
    public Gold gold;
    public String group;
    public String description;
    public String colloq;
    public String plaintext;
    public ItemImage image;
    public boolean consumed;
    public int stacks;
    public int depth;
    public boolean consumeOnFull;
    public String[] from;
    public String[] into;
    public int specialRecipe;
    public boolean inStore;
    public String requiredChampion;
    public ItemStats stats;
    public String[] tags;
    public Map<String, Boolean> maps;

    public Item() {
    }

    private Item(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.itemRune = in.readParcelable(ItemRune.class.getClassLoader());
        this.gold = in.readParcelable(Gold.class.getClassLoader());
        this.group = in.readString();
        this.description = in.readString();
        this.colloq = in.readString();
        this.plaintext = in.readString();
        this.image = in.readParcelable(ItemImage.class.getClassLoader());
        this.consumed = in.readByte() != 0;
        this.stacks = in.readInt();
        this.depth = in.readInt();
        this.consumeOnFull = in.readByte() != 0;
        this.from = in.createStringArray();
        this.into = in.createStringArray();
        this.specialRecipe = in.readInt();
        this.inStore = in.readByte() != 0;
        this.requiredChampion = in.readString();
        this.stats = in.readParcelable(ItemStats.class.getClassLoader());
        this.tags = in.createStringArray();
        this.maps = new HashMap<String, Boolean>();
        Bundle mapBundle = in.readBundle();
        for (String key : mapBundle.keySet())
            this.maps.put(key, mapBundle.getBoolean(key));
    }

    public ContentValues getAsContentValues() {
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_ID, id);
        values.put(DBHelper.KEY_NAME, name);
        values.put(DBHelper.KEY_IMAGE, image.full);
        values.put(DBHelper.KEY_TAGS, Arrays.deepToString(tags));
        values.put(DBHelper.KEY_COLLOQ, colloq);
        if (maps != null) {
            ArrayList<String> list = new ArrayList<String>();
            boolean summonersRift = !maps.containsKey("1") && !maps.containsKey("2");
            if (summonersRift)
                list.add("SR");
            boolean twistedTreeline = !maps.containsKey("4") && !maps.containsKey("10");
            if (twistedTreeline)
                list.add("TT");
            boolean crystalScar = !maps.containsKey("8");
            if (crystalScar)
                list.add("CS");
            boolean howlingAbyss = !maps.containsKey("12");
            if (howlingAbyss)
                list.add("HA");
            String mapsList = TextUtils.join(",", list);
            values.put(DBHelper.KEY_MAPS, mapsList);
        } else {
            values.put(DBHelper.KEY_MAPS, "SR,TT,CS,HA");
        }

        values.put(DBHelper.KEY_JSON, new Gson().toJson(this));
        return values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeParcelable(this.itemRune, flags);
        dest.writeParcelable(this.gold, flags);
        dest.writeString(this.group);
        dest.writeString(this.description);
        dest.writeString(this.colloq);
        dest.writeString(this.plaintext);
        dest.writeParcelable(this.image, flags);
        dest.writeByte(consumed ? (byte) 1 : (byte) 0);
        dest.writeInt(this.stacks);
        dest.writeInt(this.depth);
        dest.writeByte(consumeOnFull ? (byte) 1 : (byte) 0);
        dest.writeStringArray(this.from);
        dest.writeStringArray(this.into);
        dest.writeInt(this.specialRecipe);
        dest.writeByte(inStore ? (byte) 1 : (byte) 0);
        dest.writeString(this.requiredChampion);
        dest.writeParcelable(this.stats, flags);
        dest.writeStringArray(this.tags);
        Bundle bundle = new Bundle();
        for (String key : this.maps.keySet())
            bundle.putBoolean(key, this.maps.get(key));
        dest.writeBundle(bundle);
    }
}
