package com.howbig.riot.type.champion;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.howbig.riot.persistence.DBHelper;
import com.howbig.riot.persistence.RiotContentProvider;
import com.howbig.riot.service.DownloadService;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public class Champion implements Parcelable {

    public static final Uri CONTENT_URI =
            RiotContentProvider.BASE_CONTENT_URI.buildUpon().appendPath("champion").build();

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/champion";
    public static final Parcelable.Creator<Champion> CREATOR = new Parcelable.Creator<Champion>() {
        public Champion createFromParcel(Parcel source) {
            return new Champion(source);
        }

        public Champion[] newArray(int size) {
            return new Champion[size];
        }
    };
    public String id;
    public String key;
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

    public Champion() {
    }

    private Champion(Parcel in) {
        this.id = in.readString();
        this.key = in.readString();
        this.name = in.readString();
        this.title = in.readString();
        this.image = in.readParcelable(Image.class.getClassLoader());
        this.skins = (Skin[]) in.readParcelableArray(Skin[].class.getClassLoader());
        this.lore = in.readString();
        this.blurb = in.readString();
        this.allytips = in.createStringArray();
        this.enemytips = in.createStringArray();
        this.tags = in.createStringArray();
        this.partype = in.readString();
        this.info = in.readParcelable(Info.class.getClassLoader());
        this.stats = in.readParcelable(Stats.class.getClassLoader());
        this.spells = (Spell[]) in.readParcelableArray(Spell[].class.getClassLoader());
        this.passive = in.readParcelable(Passive.class.getClassLoader());
        this.recommended = (Recommended[]) in.readParcelableArray(Recommended[].class.getClassLoader());
    }

    public static Champion fromCursor(Cursor c) {
        Champion champ = new Champion();
        champ.key = c.getString(c.getColumnIndex(DBHelper.KEY_ID));
        champ.name = c.getString(c.getColumnIndex(DBHelper.KEY_NAME));
        Image image = new Image();
        image.full = c.getString(c.getColumnIndex(DBHelper.KEY_IMAGE));
        champ.image = image;

        return champ;
    }

    public static Champion getChampionByName(String name, Context context) {
        return getChampion(context, DBHelper.KEY_NAME, name);
    }

    public static Champion getChampionById(String id, Context context) {
        return getChampion(context, DBHelper.KEY_ID, id);
    }

    public static Champion getChampion(Context context, String column, String value) {
        Cursor cursor = context.getContentResolver().query(Champion.CONTENT_URI, new String[]{DBHelper.KEY_JSON}, column + "=?", new String[]{value}, null);
        Champion champion = null;

        if (cursor.moveToFirst()) {
            champion = DownloadService.gson.fromJson(cursor.getString(0), Champion.class);
        }
        cursor.close();

        return champion;
    }

    public ContentValues getAsContentValues() {
        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_ID, key);
        values.put(DBHelper.KEY_NAME, name);
        values.put(DBHelper.KEY_TAGS, tags[0]);
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
        dest.writeString(this.title);
        dest.writeParcelable(this.image, flags);
        dest.writeParcelableArray(this.skins, flags);
        dest.writeString(this.lore);
        dest.writeString(this.blurb);
        dest.writeStringArray(this.allytips);
        dest.writeStringArray(this.enemytips);
        dest.writeStringArray(this.tags);
        dest.writeString(this.partype);
        dest.writeParcelable(this.info, flags);
        dest.writeParcelable(this.stats, flags);
        dest.writeParcelableArray(this.spells, flags);
        dest.writeParcelable(this.passive, flags);
        dest.writeParcelableArray(this.recommended, flags);
    }
}
