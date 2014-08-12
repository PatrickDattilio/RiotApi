package com.howbig.riot.type.item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 5/21/2014.
 */
public class ItemRune implements Parcelable {
    public static final Parcelable.Creator<ItemRune> CREATOR = new Parcelable.Creator<ItemRune>() {
        public ItemRune createFromParcel(Parcel source) {
            return new ItemRune(source);
        }

        public ItemRune[] newArray(int size) {
            return new ItemRune[size];
        }
    };
    public boolean isRune;
    public int tier;
    public String type;

    public ItemRune() {
    }

    private ItemRune(Parcel in) {
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
