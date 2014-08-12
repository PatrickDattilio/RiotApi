package com.howbig.riot.type.rune;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.howbig.riot.persistence.DBHelper;
import com.howbig.riot.persistence.RiotContentProvider;
import com.howbig.riot.type.Gold;
import com.howbig.riot.type.item.ItemImage;
import com.howbig.riot.type.item.ItemStats;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 5/29/2014.
 */
public class Rune implements Parcelable {

    public static final Uri CONTENT_URI =
            RiotContentProvider.BASE_CONTENT_URI.buildUpon().appendPath("rune").build();

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/rune";
    public static final Parcelable.Creator<Rune> CREATOR = new Parcelable.Creator<Rune>() {
        public Rune createFromParcel(Parcel source) {
            return new Rune(source);
        }

        public Rune[] newArray(int size) {
            return new Rune[size];
        }
    };
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
    public Map<String, Boolean> maps;

    public Rune() {
    }

    private Rune(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
        this.rune = in.readParcelable(RuneInfo.class.getClassLoader());
        this.image = in.readParcelable(ItemImage.class.getClassLoader());
        this.gold = in.readParcelable(Gold.class.getClassLoader());
        this.group = in.readString();
        this.description = in.readString();
        this.colloq = in.readString();
        this.plaintext = in.readString();
        this.consumed = in.readByte() != 0;
        this.stacks = in.readInt();
        this.depth = in.readInt();
        this.consumeOnFull = in.readByte() != 0;
        this.from = in.createStringArray();
        this.into = in.createStringArray();
        this.specialRecipe = in.readInt();
        this.inStore = in.readByte() != 0;
        this.hideFromAll = in.readByte() != 0;
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
        values.put(DBHelper.KEY_TAGS, tags.toString());
        values.put(DBHelper.KEY_IMAGE, image.full);
        values.put(DBHelper.KEY_TIER, rune.tier);
        values.put(DBHelper.KEY_JSON, new Gson().toJson(this));
        return values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeParcelable(this.rune, flags);
        dest.writeParcelable(this.image, 0);
        dest.writeParcelable(this.gold, 0);
        dest.writeString(this.group);
        dest.writeString(this.description);
        dest.writeString(this.colloq);
        dest.writeString(this.plaintext);
        dest.writeByte(consumed ? (byte) 1 : (byte) 0);
        dest.writeInt(this.stacks);
        dest.writeInt(this.depth);
        dest.writeByte(consumeOnFull ? (byte) 1 : (byte) 0);
        dest.writeStringArray(this.from);
        dest.writeStringArray(this.into);
        dest.writeInt(this.specialRecipe);
        dest.writeByte(inStore ? (byte) 1 : (byte) 0);
        dest.writeByte(hideFromAll ? (byte) 1 : (byte) 0);
        dest.writeString(this.requiredChampion);
        dest.writeParcelable(this.stats, 0);
        dest.writeStringArray(this.tags);
        Bundle bundle = new Bundle();
        for (String key : this.maps.keySet())
            bundle.putBoolean(key, this.maps.get(key));
        dest.writeBundle(bundle);
    }
}
