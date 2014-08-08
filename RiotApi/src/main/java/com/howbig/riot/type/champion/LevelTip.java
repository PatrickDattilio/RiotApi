package com.howbig.riot.type.champion;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 5/18/2014.
 */
public class LevelTip implements Parcelable {
    public static final Parcelable.Creator<LevelTip> CREATOR = new Parcelable.Creator<LevelTip>() {
        public LevelTip createFromParcel(Parcel source) {
            return new LevelTip(source);
        }

        public LevelTip[] newArray(int size) {
            return new LevelTip[size];
        }
    };
    String[] label;
    String[] effect;

    public LevelTip() {
    }

    private LevelTip(Parcel in) {
        this.label = in.createStringArray();
        this.effect = in.createStringArray();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(this.label);
        dest.writeStringArray(this.effect);
    }
}
