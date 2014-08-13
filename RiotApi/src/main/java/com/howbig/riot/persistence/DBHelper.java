package com.howbig.riot.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */

public class DBHelper extends SQLiteOpenHelper {

    //Table Names
    public static final String TABLE_CHAMPION = "champion";
    public static final String TABLE_ITEM = "item";
    public static final String TABLE_MASTERY = "mastery";
    public static final String TABLE_RUNE = "rune";
    public static final String TABLE_SUMMONER = "summoner";
    // Common Keys
    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_JSON = "json";
    public static final String KEY_IMAGE = "image";
    private static final String DATABASE_CREATE_SUMMONER = "create table "
            + TABLE_SUMMONER + "(" +
            KEY_ID + " text not null primary key, " +
            KEY_NAME + " text not null, " +
            KEY_IMAGE + " text not null, " +
            KEY_JSON + " text not null );";
    public static final String KEY_TAGS = "tags";
    private static final String DATABASE_CREATE_CHAMPIONS = "create table "
            + TABLE_CHAMPION + "(" +
            KEY_ID + " text not null primary key, " +
            KEY_NAME + " text not null, " +
            KEY_IMAGE + " text not null, " +
            KEY_TAGS + " text not null, " +
            KEY_JSON + " text not null );";
    public static final String KEY_COLLOQ = "colloq";
    private static final String DATABASE_CREATE_ITEMS = "create table "
            + TABLE_ITEM + "(" +
            KEY_ID + " text not null primary key, " +
            KEY_NAME + " text not null, " +
            KEY_IMAGE + " text not null, " +
            KEY_TAGS + " text not null, " +
            KEY_COLLOQ + " text not null, " +
            KEY_JSON + " text not null );";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_RANKS = "ranks";
    public static final String KEY_PREREQ = "prereq";
    private static final String DATABASE_CREATE_MASTERIES = "create table "
            + TABLE_MASTERY + "(" +
            KEY_ID + " text not null primary key, " +
            KEY_NAME + " text not null, " +
            KEY_IMAGE + " text not null, " +
            KEY_DESCRIPTION + " text not null, " +
            KEY_RANKS + " text not null, " +
            KEY_PREREQ + " text not null );";
    public static final String KEY_TIER = "tier";
    public static final String KEY_TYPE = "type";
    private static final String DATABASE_CREATE_RUNES = "create table "
            + TABLE_RUNE + "(" +
            KEY_ID + " text not null primary key, " +
            KEY_NAME + " text not null, " +
            KEY_IMAGE + " text not null, " +
            KEY_TAGS + " text not null, " +
            KEY_COLLOQ + " text not null, " +
            KEY_TIER + " text not null, " +
            KEY_TYPE + " text not null, " +
            KEY_JSON + " text not null );";
    private static final int DATBASE_VERSION = 1;
    private static final String DATABASE_NAME = "lol.db";

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_CHAMPIONS);
        db.execSQL(DATABASE_CREATE_ITEMS);
        db.execSQL(DATABASE_CREATE_MASTERIES);
        db.execSQL(DATABASE_CREATE_RUNES);
        db.execSQL(DATABASE_CREATE_SUMMONER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
