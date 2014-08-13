package com.howbig.riot.type.summoner;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 7/11/2014.
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
    public String[] label;
    public String[] effect;

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
