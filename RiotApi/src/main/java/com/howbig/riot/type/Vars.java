package com.howbig.riot.type;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Alex on 5/18/2014.
 */
public class Vars implements Serializable, Parcelable {
    public static final Parcelable.Creator<Vars> CREATOR = new Parcelable.Creator<Vars>() {
        public Vars createFromParcel(Parcel source) {
            return new Vars(source);
        }

        public Vars[] newArray(int size) {
            return new Vars[size];
        }
    };
    public String link;
    public int[] coeff;
    public String key;

    public Vars() {
    }

    private Vars(Parcel in) {
        this.link = in.readString();
        this.coeff = in.createIntArray();
        this.key = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int size) {
        dest.writeString(this.link);
        dest.writeIntArray(this.coeff);
        dest.writeString(this.key);
    }
}
