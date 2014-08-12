package com.howbig.riot.type.summoner;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.howbig.riot.persistence.DBHelper;
import com.howbig.riot.persistence.RiotContentProvider;
import com.howbig.riot.type.Vars;
import com.howbig.riot.type.item.ItemImage;

/**
 * Created by Alex on 7/11/2014.
 */
public class Summoner implements Parcelable {

    public static final Uri CONTENT_URI =
            RiotContentProvider.BASE_CONTENT_URI.buildUpon().appendPath("summoner").build();

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/summoner";
    public static final Parcelable.Creator<Summoner> CREATOR = new Parcelable.Creator<Summoner>() {
        public Summoner createFromParcel(Parcel source) {
            return new Summoner(source);
        }

        public Summoner[] newArray(int size) {
            return new Summoner[size];
        }
    };
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
    public int[] range;
    public String rangeBurn;
    public ItemImage image;
    public String resource;

    public Summoner() {
    }

    private Summoner(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.tooltip = in.readString();
        this.maxrank = in.readInt();
        this.leveltip = in.readParcelable(LevelTip.class.getClassLoader());
        this.cooldown = in.createIntArray();
        this.cooldownBurn = in.readString();
        this.cost = in.createIntArray();
        this.costBurn = in.readString();
        int size = in.readInt();
        Integer[][] array = new Integer[size][];
        for (int i = 0; i < size; i++) {
            array[i] = (Integer[]) in.readArray(Integer.class.getClassLoader());
        }
        this.effect = array;
        this.effectBurn = in.createStringArray();
        this.vars = (Vars[]) in.readParcelableArray(Vars[].class.getClassLoader());
        this.key = in.readString();
        this.summonerLevel = in.readInt();
        this.modes = in.createStringArray();
        this.costType = in.readString();
        this.range = in.createIntArray();
        this.rangeBurn = in.readString();
        this.image = in.readParcelable(ItemImage.class.getClassLoader());
        this.resource = in.readString();
    }

    public ContentValues getAsContentValues() {
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_ID, id);
        values.put(DBHelper.KEY_NAME, name);
        values.put(DBHelper.KEY_IMAGE, image.full);
        values.put(DBHelper.KEY_JSON, new Gson().toJson(this));
        return values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.tooltip);
        dest.writeInt(this.maxrank);
        dest.writeParcelable(this.leveltip, flags);
        dest.writeIntArray(this.cooldown);
        dest.writeString(this.cooldownBurn);
        dest.writeIntArray(this.cost);
        dest.writeString(this.costBurn);
        dest.writeInt(this.effect.length);
        for (Integer[] array : this.effect)
            dest.writeArray(array);
        dest.writeStringArray(this.effectBurn);
        dest.writeParcelableArray(this.vars, flags);
        dest.writeString(this.key);
        dest.writeInt(this.summonerLevel);
        dest.writeStringArray(this.modes);
        dest.writeString(this.costType);
        dest.writeIntArray(this.range);
        dest.writeString(this.rangeBurn);
        dest.writeParcelable(this.image, 0);
        dest.writeString(this.resource);
    }
}
