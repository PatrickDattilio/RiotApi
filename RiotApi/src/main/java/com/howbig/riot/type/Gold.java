package com.howbig.riot.type;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 5/21/2014.
 */
public class Gold implements Parcelable {
    public static final Parcelable.Creator<Gold> CREATOR = new Parcelable.Creator<Gold>() {
        public Gold createFromParcel(Parcel source) {
            return new Gold(source);
        }

        public Gold[] newArray(int size) {
            return new Gold[size];
        }
    };
    public int base;
    public boolean purchasable;
    public int total;
    public int sell;

    public Gold() {
    }

    private Gold(Parcel in) {
        this.base = in.readInt();
        this.purchasable = in.readByte() != 0;
        this.total = in.readInt();
        this.sell = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.base);
        dest.writeByte(purchasable ? (byte) 1 : (byte) 0);
        dest.writeInt(this.total);
        dest.writeInt(this.sell);
    }
}
