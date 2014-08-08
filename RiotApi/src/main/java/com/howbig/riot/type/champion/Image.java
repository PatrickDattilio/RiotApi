package com.howbig.riot.type.champion;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 5/18/2014.
 */
public class Image implements Parcelable {
    public static final Parcelable.Creator<Image> CREATOR = new Parcelable.Creator<Image>() {
        public Image createFromParcel(Parcel source) {
            return new Image(source);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
    public String full;
    public String sprite;
    public String champion;
    public int x;
    public int y;
    public int w;
    public int h;

    public Image() {
    }

    private Image(Parcel in) {
        this.full = in.readString();
        this.sprite = in.readString();
        this.champion = in.readString();
        this.x = in.readInt();
        this.y = in.readInt();
        this.w = in.readInt();
        this.h = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.full);
        dest.writeString(this.sprite);
        dest.writeString(this.champion);
        dest.writeInt(this.x);
        dest.writeInt(this.y);
        dest.writeInt(this.w);
        dest.writeInt(this.h);
    }
}
