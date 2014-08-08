package com.howbig.riot.type.champion;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 5/18/2014.
 */
public class Stats implements Parcelable {
    public static final Parcelable.Creator<Stats> CREATOR = new Parcelable.Creator<Stats>() {
        public Stats createFromParcel(Parcel source) {
            return new Stats(source);
        }

        public Stats[] newArray(int size) {
            return new Stats[size];
        }
    };
    public double hp;
    public double hpperlevel;
    public double mp;
    public double mpperlevel;
    public double movespeed;
    public double armor;
    public double armorperlevel;
    public double spellblock;
    public double spellblockperlevel;
    public double attackrange;
    public double hpregen;
    public double hpregenperlevel;
    public double mpregen;
    public double mpregenperlevel;
    public double crit;
    public double critperlevel;
    public double attackdamage;
    public double attackdamageperlevel;
    public double attackspeedoffset;
    public double attackspeedperlevel;

    public Stats() {
    }

    private Stats(Parcel in) {
        this.hp = in.readDouble();
        this.hpperlevel = in.readDouble();
        this.mp = in.readDouble();
        this.mpperlevel = in.readDouble();
        this.movespeed = in.readDouble();
        this.armor = in.readDouble();
        this.armorperlevel = in.readDouble();
        this.spellblock = in.readDouble();
        this.spellblockperlevel = in.readDouble();
        this.attackrange = in.readDouble();
        this.hpregen = in.readDouble();
        this.hpregenperlevel = in.readDouble();
        this.mpregen = in.readDouble();
        this.mpregenperlevel = in.readDouble();
        this.crit = in.readDouble();
        this.critperlevel = in.readDouble();
        this.attackdamage = in.readDouble();
        this.attackdamageperlevel = in.readDouble();
        this.attackspeedoffset = in.readDouble();
        this.attackspeedperlevel = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.hp);
        dest.writeDouble(this.hpperlevel);
        dest.writeDouble(this.mp);
        dest.writeDouble(this.mpperlevel);
        dest.writeDouble(this.movespeed);
        dest.writeDouble(this.armor);
        dest.writeDouble(this.armorperlevel);
        dest.writeDouble(this.spellblock);
        dest.writeDouble(this.spellblockperlevel);
        dest.writeDouble(this.attackrange);
        dest.writeDouble(this.hpregen);
        dest.writeDouble(this.hpregenperlevel);
        dest.writeDouble(this.mpregen);
        dest.writeDouble(this.mpregenperlevel);
        dest.writeDouble(this.crit);
        dest.writeDouble(this.critperlevel);
        dest.writeDouble(this.attackdamage);
        dest.writeDouble(this.attackdamageperlevel);
        dest.writeDouble(this.attackspeedoffset);
        dest.writeDouble(this.attackspeedperlevel);
    }
}
