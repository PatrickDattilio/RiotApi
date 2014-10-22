package com.howbig.riot.type.mastery;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.howbig.riot.persistence.DBHelper;
import com.howbig.riot.persistence.RiotContentProvider;
import com.howbig.riot.service.DownloadService;
import com.howbig.riot.type.item.ItemImage;

import java.util.ArrayList;

/**
 * Created by Alex on 6/21/2014.
 */
public class Mastery implements Parcelable {

    public static final Uri CONTENT_URI =
            RiotContentProvider.BASE_CONTENT_URI.buildUpon().appendPath("mastery").build();

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/mastery";
    public static final Parcelable.Creator<Mastery> CREATOR = new Parcelable.Creator<Mastery>() {
        public Mastery createFromParcel(Parcel source) {
            return new Mastery(source);
        }

        public Mastery[] newArray(int size) {
            return new Mastery[size];
        }
    };
    public int id;
    public String name;
    public ArrayList<String> description;
    public ItemImage image;
    public int ranks;
    public String prereq;
    //This field is used in a build to indicate how many ranks of this mastery the build uses.
    public int ranksUsed;

    public Mastery() {
    }

    private Mastery(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.description = (ArrayList<String>) in.readSerializable();
        this.image = in.readParcelable(ItemImage.class.getClassLoader());
        this.ranks = in.readInt();
        this.ranksUsed = in.readInt();
        this.prereq = in.readString();
    }

    public static Mastery getMasteryById(String id, Activity activity) {
        Cursor cursor = activity.getContentResolver().query(Mastery.CONTENT_URI, new String[]{DBHelper.KEY_JSON}, DBHelper.KEY_ID + "=?", new String[]{id}, null);
        cursor.moveToFirst();
        Mastery mastery = DownloadService.gson.fromJson(cursor.getString(0), Mastery.class);
        cursor.close();
        return mastery;
    }

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
        dest.writeSerializable(this.description);
        dest.writeParcelable(this.image, 0);
        dest.writeInt(this.ranks);
        dest.writeInt(this.ranksUsed);
        dest.writeString(this.prereq);
    }
}
