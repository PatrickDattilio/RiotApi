package com.howbig.riot.type.champion;

import android.os.Parcel;
import android.os.Parcelable;

import com.howbig.riot.type.RecommendedItems;

import java.io.Serializable;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public class Blocks implements Serializable, Parcelable {
    public static final Parcelable.Creator<Blocks> CREATOR = new Parcelable.Creator<Blocks>() {
        public Blocks createFromParcel(Parcel source) {
            return new Blocks(source);
        }

        public Blocks[] newArray(int size) {
            return new Blocks[size];
        }
    };
    public String type;
    public Boolean recMath;
    public RecommendedItems[] recommendeditems;

    public Blocks() {
    }

    private Blocks(Parcel in) {
        this.type = in.readString();
        this.recMath = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.recommendeditems = (RecommendedItems[]) in.readParcelableArray(RecommendedItems[].class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.type);
        dest.writeValue(this.recMath);
        dest.writeParcelableArray(this.recommendeditems, flags);
    }
}
