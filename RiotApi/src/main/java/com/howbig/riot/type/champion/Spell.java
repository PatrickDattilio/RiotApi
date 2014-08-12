package com.howbig.riot.type.champion;

import android.os.Parcel;
import android.os.Parcelable;

import com.howbig.riot.type.Vars;

/**
 * Created by Alex on 5/18/2014.
 */
public class Spell implements Parcelable {
    public static final Parcelable.Creator<Spell> CREATOR = new Parcelable.Creator<Spell>() {
        public Spell createFromParcel(Parcel source) {
            return new Spell(source);
        }

        public Spell[] newArray(int size) {
            return new Spell[size];
        }
    };
    public String id;
    public String name;
    public String description;
    public String tooltip;
    public LevelTip leveltip;
    public int maxrank;
    public int[] cooldown;
    public String cooldownBurn;
    public int[] cost;
    public String costBurn;
    public int[][] effect;
    public String[] effectBurn;
    public Vars[] vars;
    public String costType;
    public int[] range;
    public String rangeBurn;
    public Image image;
    public String resource;

    public Spell() {
    }

    private Spell(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.description = in.readString();
        this.tooltip = in.readString();
        this.leveltip = in.readParcelable(LevelTip.class.getClassLoader());
        this.maxrank = in.readInt();
        this.cooldown = in.createIntArray();
        this.cooldownBurn = in.readString();
        this.cost = in.createIntArray();
        this.costBurn = in.readString();

        int size = in.readInt();
        int[][] array = new int[size][];
        for (int i = 0; i < size; i++) {
            array[i] = in.createIntArray();
        }
        this.effect = array;

        this.effectBurn = in.createStringArray();
        this.vars = (Vars[]) in.readParcelableArray(Vars[].class.getClassLoader());
        this.costType = in.readString();
        this.range = in.createIntArray();
        this.rangeBurn = in.readString();
        this.image = in.readParcelable(Image.class.getClassLoader());
        this.resource = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.tooltip);
        dest.writeParcelable(this.leveltip, flags);
        dest.writeInt(this.maxrank);
        dest.writeIntArray(this.cooldown);
        dest.writeString(this.cooldownBurn);
        dest.writeIntArray(this.cost);
        dest.writeString(this.costBurn);
        dest.writeInt(this.effect.length);
        for (int[] array : this.effect)
            dest.writeIntArray(array);
        dest.writeStringArray(this.effectBurn);
        dest.writeParcelableArray(this.vars, flags);
        dest.writeString(this.costType);
        dest.writeIntArray(this.range);
        dest.writeString(this.rangeBurn);
        dest.writeParcelable(this.image, 0);
        dest.writeString(this.resource);
    }
}
