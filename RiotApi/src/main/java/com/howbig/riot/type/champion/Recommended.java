package com.howbig.riot.type.champion;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public class Recommended implements Parcelable {
    public static final Parcelable.Creator<Recommended> CREATOR = new Parcelable.Creator<Recommended>() {
        public Recommended createFromParcel(Parcel source) {
            return new Recommended(source);
        }

        public Recommended[] newArray(int size) {
            return new Recommended[size];
        }
    };
    public String champion;
    public String title;
    public String type;
    public String map;
    public boolean priority;
    public Blocks[] blocks;

    public Recommended() {
    }

    private Recommended(Parcel in) {
        this.champion = in.readString();
        this.title = in.readString();
        this.type = in.readString();
        this.map = in.readString();
        this.priority = in.readByte() != 0;
        this.blocks = (Blocks[]) in.readParcelableArray(Blocks[].class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.champion);
        dest.writeString(this.title);
        dest.writeString(this.type);
        dest.writeString(this.map);
        dest.writeByte(priority ? (byte) 1 : (byte) 0);
        dest.writeParcelableArray(this.blocks, flags);
    }
}
