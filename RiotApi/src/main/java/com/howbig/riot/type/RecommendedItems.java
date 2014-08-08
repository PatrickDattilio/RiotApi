package com.howbig.riot.type;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public class RecommendedItems implements Serializable, Parcelable {
    public static final Parcelable.Creator<RecommendedItems> CREATOR = new Parcelable.Creator<RecommendedItems>() {
        public RecommendedItems createFromParcel(Parcel source) {
            return new RecommendedItems(source);
        }

        public RecommendedItems[] newArray(int size) {
            return new RecommendedItems[size];
        }
    };
    public String id;
    public int count;

    public RecommendedItems() {
    }

    private RecommendedItems(Parcel in) {
        this.id = in.readString();
        this.count = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeInt(this.count);
    }
}
