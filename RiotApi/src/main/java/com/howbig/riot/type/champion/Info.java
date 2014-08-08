package com.howbig.riot.type.champion;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public class Info implements Parcelable {
    public static final Parcelable.Creator<Info> CREATOR = new Parcelable.Creator<Info>() {
        public Info createFromParcel(Parcel source) {
            return new Info(source);
        }

        public Info[] newArray(int size) {
            return new Info[size];
        }
    };
    public int attack;
    public int defense;
    public int magic;
    public int difficulty;

    public Info() {
    }

    private Info(Parcel in) {
        this.attack = in.readInt();
        this.defense = in.readInt();
        this.magic = in.readInt();
        this.difficulty = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.attack);
        dest.writeInt(this.defense);
        dest.writeInt(this.magic);
        dest.writeInt(this.difficulty);
    }
}
