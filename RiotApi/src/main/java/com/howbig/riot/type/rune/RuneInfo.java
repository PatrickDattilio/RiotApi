package com.howbig.riot.type.rune;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 5/29/2014.
 */
public class RuneInfo implements Parcelable {
    public static final Parcelable.Creator<RuneInfo> CREATOR = new Parcelable.Creator<RuneInfo>() {
        public RuneInfo createFromParcel(Parcel source) {
            return new RuneInfo(source);
        }

        public RuneInfo[] newArray(int size) {
            return new RuneInfo[size];
        }
    };
    public boolean isRune;
    public int tier;
    public String type;

    public RuneInfo() {
    }

    private RuneInfo(Parcel in) {
        this.isRune = in.readByte() != 0;
        this.tier = in.readInt();
        this.type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(isRune ? (byte) 1 : (byte) 0);
        dest.writeInt(this.tier);
        dest.writeString(this.type);
    }
}
