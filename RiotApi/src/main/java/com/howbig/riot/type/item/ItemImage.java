package com.howbig.riot.type.item;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 5/29/2014.
 */
public class ItemImage implements Parcelable {
    public static final Parcelable.Creator<ItemImage> CREATOR = new Parcelable.Creator<ItemImage>() {
        public ItemImage createFromParcel(Parcel source) {
            return new ItemImage(source);
        }

        public ItemImage[] newArray(int size) {
            return new ItemImage[size];
        }
    };
    public String full;
    public String sprite;
    public String group;
    public int x;
    public int y;
    public int w;
    public int h;

    public ItemImage() {
    }

    private ItemImage(Parcel in) {
        this.full = in.readString();
        this.sprite = in.readString();
        this.group = in.readString();
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
        dest.writeString(this.group);
        dest.writeInt(this.x);
        dest.writeInt(this.y);
        dest.writeInt(this.w);
        dest.writeInt(this.h);
    }
}
