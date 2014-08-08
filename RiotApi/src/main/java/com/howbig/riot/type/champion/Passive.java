package com.howbig.riot.type.champion;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 5/18/2014.
 */
public class Passive implements Parcelable {
    public static final Parcelable.Creator<Passive> CREATOR = new Parcelable.Creator<Passive>() {
        public Passive createFromParcel(Parcel source) {
            return new Passive(source);
        }

        public Passive[] newArray(int size) {
            return new Passive[size];
        }
    };
    String name;
    String description;
    Image image;

    public Passive() {
    }

    private Passive(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.image = in.readParcelable(Image.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeParcelable(this.image, 0);
    }
}
