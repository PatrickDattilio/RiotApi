package com.howbig.riot.type.champion;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 5/18/2014.
 */
public class Skin implements Parcelable {
    public static final Parcelable.Creator<Skin> CREATOR = new Parcelable.Creator<Skin>() {
        public Skin createFromParcel(Parcel source) {
            return new Skin(source);
        }

        public Skin[] newArray(int size) {
            return new Skin[size];
        }
    };
    public String id;
    public int num;
    public String name;

    public Skin() {
    }

    private Skin(Parcel in) {
        this.id = in.readString();
        this.num = in.readInt();
        this.name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.num);
        dest.writeString(this.name);
    }
}
