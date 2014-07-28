package com.howbig.riot.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */


//TODO Test speed of saving champion as json string. Probably only need name/id/tags, rest could be pure json.
public class DBHelper extends SQLiteOpenHelper {

    //Table Names
    public static final String TABLE_CHAMPION = "champion";
    public static final String TABLE_ITEMS = "items";
    // Common Keys
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_JSON = "json";
    public static final String KEY_TITLE = "title";
    // Champion image
    public static final String KEY_IMAGE = "image";
    public static final String KEY_IMAGE_SPRITE = "sprite";
    public static final String KEY_X = "x";
    public static final String KEY_Y = "y";
    // Champion Table
    // KEY_ID
    // KEY_NAME
    public static final String KEY_W = "w";
    public static final String KEY_H = "h";
    public static final String KEY_SKINS = "skins";
    public static final String KEY_ALLYTIPS = "allytips";
    public static final String KEY_ENEMYTIPS = "enemytips";
    public static final String KEY_TAGS = "tags";
    private static final String DATABASE_CREATE_CHAMPIONS = "create table "
            + TABLE_CHAMPION + "(" +
            KEY_ID + " text not null primary key, " +
            KEY_NAME + " text not null, " +
//            KEY_TITLE + " text not null, " +
            KEY_IMAGE + " text not null, " +
//            KEY_IMAGE_SPRITE + " text not null, " +
//            KEY_X + " text not null, " +
//            KEY_Y + " text not null, " +
//            KEY_W + " text not null, " +
//            KEY_H + " text not null, " +
//            KEY_SKINS + " text not null, " +
//            KEY_ALLYTIPS + " text not null, " +
//            KEY_ENEMYTIPS + " text not null, " +
            KEY_TAGS + " text not null, " +
//            KEY_PARTYPE + " text not null, " +
//            KEY_ATTACK + " integer, " +
//            KEY_DEFENSE + " integer, " +
//            KEY_MAGIC + " integer, " +
//            KEY_DIFFICULTY + " real, " +
//            KEY_HP + " real, " +
//            KEY_HPPERLEVEL + " real, " +
//            KEY_MP + " real, " +
//            KEY_MPPERLEVEL + " real, " +
//            KEY_MOVESPEED + " real, " +
//            KEY_ARMOR + " real, " +
//            KEY_ARMORPERLEVEL + " real, " +
//            KEY_SPELLBLOCK + " real, " +
//            KEY_SPELLBLOCKPERLEVEL + " real, " +
//            KEY_ATTACKRANGE + " real, " +
//            KEY_HPREGEN + " real, " +
//            KEY_HPREGENPERLEVEL + " real, " +
//            KEY_MPREGEN + " real, " +
//            KEY_MPREGENPERLEVEL + " real, " +
//            KEY_CRIT + " real, " +
//            KEY_CRITPERLEVEL + " real, " +
//            KEY_ATTACKDAMAGE + " real, " +
//            KEY_ATTACKDAMAGEPERLEVEL + " real, " +
//            KEY_ATTACKSPEEDOFFSET + " real, " +
//            KEY_ATTACKSPEEDPERLEVEL + " real, " +
//            KEY_SPELLS + " text not null, " +
//            KEY_PASSIVE_NAME + " text not null, " +
//            KEY_PASSIVE_DESCRIPTION + " text not null, " +
//            KEY_PASSIVE_IMAGE_FULL + " text not null, " +
//            KEY_PASSIVE_IMAGE_SPRITE + " text not null, " +
//            KEY_PASSIVE_X + " integer, " +
//            KEY_PASSIVE_Y + " integer, " +
//            KEY_PASSIVE_W + " integer, " +
//            KEY_PASSIVE_H + " integer, " +
            KEY_JSON + " text not null );";
    private static final String DATABASE_CREATE_ITEMS = "create table "
            + TABLE_ITEMS + "(" +
            KEY_ID + " text not null primary key, " +
            KEY_NAME + " text not null, " +
//            KEY_TITLE + " text not null, " +
            KEY_IMAGE + " text not null, " +
//            KEY_IMAGE_SPRITE + " text not null, " +
//            KEY_X + " text not null, " +
//            KEY_Y + " text not null, " +
//            KEY_W + " text not null, " +
//            KEY_H + " text not null, " +
//            KEY_SKINS + " text not null, " +
//            KEY_ALLYTIPS + " text not null, " +
//            KEY_ENEMYTIPS + " text not null, " +
            KEY_TAGS + " text not null, " +
            KEY_JSON + " text not null );";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_RANKS = "ranks";
    public static final String KEY_PREREQ = "prereq";
    private static final String DATABASE_CREATE_MASTERIES = "create table "
            + TABLE_ITEMS + "(" +
            KEY_ID + " text not null primary key, " +
            KEY_NAME + " text not null, " +
            KEY_DESCRIPTION + " text not null, " +
            KEY_IMAGE + " text not null, " +
            KEY_RANKS + " text not null, " +
            KEY_PREREQ + " text not null);";
    private static final int DATBASE_VERSION = 1;
    private static final String DATABASE_NAME = "lol.db";


    //    public static final String KEY_PARTYPE = "partype";
//    // Champion Info
//    public static final String KEY_ATTACK = "attack";
//    public static final String KEY_DEFENSE = "defense";
//    public static final String KEY_MAGIC = "magic";
//    public static final String KEY_DIFFICULTY = "difficulty";
//    // Champion Stats
//    public static final String KEY_HP = "hp";
//    public static final String KEY_HPPERLEVEL = "hpperlevel";
//    public static final String KEY_MP = "mp";
//    public static final String KEY_MPPERLEVEL = "mpperlevel";
//    public static final String KEY_MOVESPEED = "movespeed";
//    public static final String KEY_ARMOR = "armor";
//    public static final String KEY_ARMORPERLEVEL = "armorperlevel";
//    public static final String KEY_SPELLBLOCK = "spellblock";
//    public static final String KEY_SPELLBLOCKPERLEVEL = "spellblockperlevel";
//    public static final String KEY_ATTACKRANGE = "attackrange";
//    public static final String KEY_HPREGEN = "hpregen";
//    public static final String KEY_HPREGENPERLEVEL = "hpregenperlevel";
//    public static final String KEY_MPREGEN = "mpregen";
//    public static final String KEY_MPREGENPERLEVEL = "mpregenperlevel";
//    public static final String KEY_CRIT = "crit";
//    public static final String KEY_CRITPERLEVEL = "critperlevel";
//    public static final String KEY_ATTACKDAMAGE = "attackdamage";
//    public static final String KEY_ATTACKDAMAGEPERLEVEL = "attackdamageperlevel";
//    public static final String KEY_ATTACKSPEEDOFFSET = "attackspeedoffset";
//    public static final String KEY_ATTACKSPEEDPERLEVEL = "attackspeedperlevel";
//    public static final String KEY_SPELLS = "spells";
//    public static final String KEY_PASSIVE_NAME = "passive_spells";
//    public static final String KEY_PASSIVE_DESCRIPTION = "passive_spells";
//    // Passive Image
//    public static final String KEY_PASSIVE_IMAGE_FULL = "passive_full";
//    public static final String KEY_PASSIVE_IMAGE_SPRITE = "passive_sprite";
//    public static final String KEY_PASSIVE_X = "passive_x";
//    public static final String KEY_PASSIVE_Y = "passive_y";
//    public static final String KEY_PASSIVE_W = "passive_w";
//    public static final String KEY_PASSIVE_H = "passive_h";
//    public static final String KEY_RECCOMMENDED = "recommended";
//    // Skin Table columns
//    // KEY_ID
//    // KEY_NAME
//    public static final String KEY_NUM = "num";
//    // Spell Table columns
//    // ID, NAME
//    public static final String KEY_DESCRIPTION = "description";
//    public static final String KEY_TOOLTIP = "tooltip";
//    // Spell LevelTip
//    public static final String KEY_LABEL = "label";
//    public static final String KEY_TOOLTIP_EFFECT = "tooltipEffect";
//    public static final String KEY_MAXRANK = "maxrank";
//    public static final String KEY_COOLDOWN = "cooldown";
//    public static final String KEY_COOLDOWNBURN = "cooldownBurn";
//    public static final String KEY_COST = "cost";
//    public static final String KEY_COSTBURN = "costBurn";
//    public static final String KEY_EFFECT = "effect";
//    public static final String KEY_EFFECTBURN = "effectBurn";
//    public static final String KEY_VARS = "vars";
//    public static final String KEY_COSTTYPE = "costType";
//    public static final String KEY_RANGE = "range";
//    public static final String KEY_RANGEBURN = "rangeBurn";
//    // Spell Image
//    // KEY_IMAGE_FULL
//    // KEY_IMAGE_SPRITE
//    // KEY_X
//    // KEY_Y
//    // KEY_W
//    // KEY_H
//    public static final String KEY_RESOURCE = "resource";
//    // Recommended Table
//    public static final String KEY_CHAMPION = "champion";
//    //public static final String KEY_TITLE= "title";
//    public static final String KEY_TYPE = "champion";
//    public static final String KEY_MAP = "map";
//    public static final String KEY_PRIORITY = "priority";
    //Vars Table
    //ID
//    public static final String KEY_VARS_LINK = "link";
//    public static final String KEY_VARS_COEFF = "coeff";
//    public static final String KEY_VARS_KEY = "key";
//    public static final String KEY_BLOCKS = "blocks";
//    private static final String TABLE_SKIN = "skin";
//    private static final String DATABASE_CREATE_SKINS = "create table "
//            + TABLE_SKIN + "(" +
//            KEY_ID + " integer, " +
//            KEY_NUM + " integer, " +
//            KEY_NAME + " text not null " +
//            ");";
//
    //Blocks Table
//    public static final String KEY_BLOCKS_TYPE = "type";
//    public static final String KEY_BLOCKS_RECMATH = "recMath";
//    public static final String KEY_BLOCKS_RECOMMENDEDITEMS = "recommendeditems";
//
//
    //RecommendedItems Table
//    public static final String KEY_RECC_ITEMS_ID = "id";
//    public static final String KEY_RECC_ITEMS_COUNT = "count";
//    private static final String TABLE_SPELL = "spell";
//    private static final String DATABASE_CREATE_SPELLS = "create table "
//            + TABLE_SPELL + "(" +
//            KEY_ID + " text primary key, " +
//            KEY_NAME + " text not null, " +
//            KEY_DESCRIPTION + " text not null, " +
//            KEY_TOOLTIP + " text not null, " +
//            KEY_LABEL + " text not null, " +
//            KEY_TOOLTIP_EFFECT + " text not null, " +
//            KEY_MAXRANK + " integer, " +
//            KEY_COOLDOWN + "1 integer, " +
//            KEY_COOLDOWN + "2 integer, " +
//            KEY_COOLDOWN + "3 integer, " +
//            KEY_COOLDOWN + "4 integer, " +
//            KEY_COOLDOWN + "5 integer, " +
//            KEY_COOLDOWNBURN + " text not null, " +
//            KEY_COST + "1 integer, " +
//            KEY_COST + "2 integer, " +
//            KEY_COST + "3 integer, " +
//            KEY_COST + "4 integer, " +
//            KEY_COST + "5 integer, " +
//            KEY_COSTBURN + " text not null, " +
//            KEY_EFFECT + " text not null, " +
//            KEY_EFFECTBURN + "1 text not null, " +
//            KEY_EFFECTBURN + "2 text not null, " +
//            KEY_EFFECTBURN + "3 text not null, " +
//            KEY_EFFECTBURN + "4 text not null, " +
//            KEY_EFFECTBURN + "5 text not null, " +
//            KEY_VARS + " text not null, " +
//            KEY_COSTTYPE + " text not null, " +
//            KEY_RANGE + "1 text not null, " +
//            KEY_RANGE + "2 text not null, " +
//            KEY_RANGE + "3 text not null, " +
//            KEY_RANGE + "4 text not null, " +
//            KEY_RANGE + "5 text not null, " +
//            KEY_RANGEBURN + " text not null, " +
//            KEY_IMAGE + " text not null, " +
//            KEY_IMAGE_SPRITE + " text not null, " +
//            KEY_X + " integer, " +
//            KEY_Y + " integer, " +
//            KEY_W + " integer, " +
//            KEY_H + " integer, " +
//            KEY_RESOURCE + " text not null " +
//            ");";
//    private static final String TABLE_RECOMMENDED = "recommended";
//    private static final String DATABASE_CREATE_RECOMMENDED = "create table "
//            + TABLE_RECOMMENDED + "(" +
//            KEY_TITLE + " text primary key, " +
//            KEY_TYPE + " text not null, " +
//            KEY_MAP + " text not null, " +
//            KEY_PRIORITY + " integer, " +
//            KEY_BLOCKS + " blob" +
//            ");";
    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_CHAMPIONS);
        db.execSQL(DATABASE_CREATE_ITEMS);
        db.execSQL(DATABASE_CREATE_MASTERIES);
        //db.execSQL(DATABASE_CREATE_SKINS);
        //db.execSQL(DATABASE_CREATE_SPELLS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
