package com.howbig.riot.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DATBASE_VERSION = 1;
    private static final String DATABSE_NAME = "lol.db";

    //Table Names
    public static final String TABLE_CHAMPION = "champion";
    private static final String TABLE_SKIN = "skin";
    private static final String TABLE_SPELL = "spell";
    private static final String TABLE_RECOMMENDED = "recommended";


    // Common Keys
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";


    // Champion Table
    // KEY_ID
    // KEY_NAME
    private static final String KEY_TITLE = "title";
    // Champion image
    private static final String KEY_IMAGE_FULL = "full";
    private static final String KEY_IMAGE_SPRITE = "sprite";
    private static final String KEY_X = "x";
    private static final String KEY_Y = "y";
    private static final String KEY_W = "w";
    private static final String KEY_H = "h";
    private static final String KEY_SKINS = "skins";
    private static final String KEY_ALLYTIPS = "allytips";
    private static final String KEY_ENEMYTIPS = "enemytips";
    private static final String KEY_TAGS = "tags";
    private static final String KEY_PARTYPE = "partype";
    // Champion Info
    private static final String KEY_ATTACK = "attack";
    private static final String KEY_DEFENSE = "defense";
    private static final String KEY_MAGIC = "magic";
    private static final String KEY_DIFFICULTY = "difficulty";
    // Champion Stats
    private static final String KEY_HP = "hp";
    private static final String KEY_HPPERLEVEL = "hpperlevel";
    private static final String KEY_MP = "mp";
    private static final String KEY_MPPERLEVEL = "mpperlevel";
    private static final String KEY_MOVESPEED = "movespeed";
    private static final String KEY_ARMOR = "armor";
    private static final String KEY_ARMORPERLEVEL = "armorperlevel";
    private static final String KEY_SPELLBLOCK = "spellblock";
    private static final String KEY_SPELLBLOCKPERLEVEL = "spellblockperlevel";
    private static final String KEY_ATTACKRANGE = "attackrange";
    private static final String KEY_HPREGEN = "hpregen";
    private static final String KEY_HPREGENPERLEVEL = "hpregenperlevel";
    private static final String KEY_MPREGEN = "mpregen";
    private static final String KEY_MPREGENPERLEVEL = "mpregenperlevel";
    private static final String KEY_CRIT = "crit";
    private static final String KEY_CRITPERLEVEL = "critperlevel";
    private static final String KEY_ATTACKDAMAGE = "attackdamage";
    private static final String KEY_ATTACKDAMAGEPERLEVEL = "attackdamageperlevel";
    private static final String KEY_ATTACKSPEEDOFFSET = "attackspeedoffset";
    private static final String KEY_ATTACKSPEEDPERLEVEL = "attackspeedperlevel";
    private static final String KEY_SPELLS = "spells";
    private static final String KEY_PASSIVE_NAME = "passive_spells";
    private static final String KEY_PASSIVE_DESCRIPTION = "passive_spells";
    // Passive Image
    private static final String KEY_PASSIVE_IMAGE_FULL = "passive_full";
    private static final String KEY_PASSIVE_IMAGE_SPRITE = "passive_sprite";
    private static final String KEY_PASSIVE_X = "passive_x";
    private static final String KEY_PASSIVE_Y = "passive_y";
    private static final String KEY_PASSIVE_W = "passive_w";
    private static final String KEY_PASSIVE_H = "passive_h";
    private static final String KEY_RECCOMMENDED = "recommended";

    // Skin Table columns
    // KEY_ID
    // KEY_NAME
    private static final String KEY_NUM = "num";


    // Spell Table columns
    // ID, NAME
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TOOLTIP = "tooltip";
    // Spell LevelTip
    private static final String KEY_LABEL = "label";
    private static final String KEY_TOOLTIP_EFFECT = "tooltipEffect";
    private static final String KEY_MAXRANK = "maxrank";
    private static final String KEY_COOLDOWN = "cooldown";
    private static final String KEY_COOLDOWNBURN = "cooldownBurn";
    private static final String KEY_COST = "cost";
    private static final String KEY_COSTBURN = "costBurn";
    private static final String KEY_EFFECT = "effect";
    private static final String KEY_EFFECTBURN = "effectBurn";
    private static final String KEY_VARS = "vars";
    private static final String KEY_COSTTYPE = "costType";
    private static final String KEY_RANGE = "range";
    private static final String KEY_RANGEBURN = "rangeBurn";
    // Spell Image
    // KEY_IMAGE_FULL
    // KEY_IMAGE_SPRITE
    // KEY_X
    // KEY_Y
    // KEY_W
    // KEY_H
    private static final String KEY_RESOURCE = "resource";

    //Vars Table
    //ID
//    private static final String KEY_VARS_LINK = "link";
//    private static final String KEY_VARS_COEFF = "coeff";
//    private static final String KEY_VARS_KEY = "key";

    // Recommended Table
    private static final String KEY_CHAMPION = "champion";
    //private static final String KEY_TITLE= "title";
    private static final String KEY_TYPE = "champion";
    private static final String KEY_MAP = "map";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_BLOCKS = "blocks";

    //Blocks Table
//    private static final String KEY_BLOCKS_TYPE = "type";
//    private static final String KEY_BLOCKS_RECMATH = "recMath";
//    private static final String KEY_BLOCKS_RECOMMENDEDITEMS = "recommendeditems";


    //RecommendedItems Table
//    private static final String KEY_RECC_ITEMS_ID = "id";
//    private static final String KEY_RECC_ITEMS_COUNT = "count";


    private static final String DATABASE_CREATE_CHAMPIONS = "create table "
            + TABLE_CHAMPION + "(" +
            KEY_ID + " integer primary key, " +
            KEY_NAME + " text not null, " +
            KEY_TITLE + " text not null, " +
            KEY_IMAGE_FULL + " text not null, " +
            KEY_IMAGE_SPRITE + " text not null, " +
            KEY_X + " text not null, " +
            KEY_Y + " text not null, " +
            KEY_W + " text not null, " +
            KEY_H + " text not null, " +
            KEY_SKINS + " text not null, " +
            KEY_ALLYTIPS + " text not null, " +
            KEY_ENEMYTIPS + " text not null, " +
            KEY_TAGS + " text not null, " +
            KEY_PARTYPE + " text not null, " +
            KEY_ATTACK + " integer, " +
            KEY_DEFENSE + " integer, " +
            KEY_MAGIC + " integer, " +
            KEY_DIFFICULTY + " real, " +
            KEY_HP + " real, " +
            KEY_HPPERLEVEL + " real, " +
            KEY_MP + " real, " +
            KEY_MPPERLEVEL + " real, " +
            KEY_MOVESPEED + " real, " +
            KEY_ARMOR + " real, " +
            KEY_ARMORPERLEVEL + " real, " +
            KEY_SPELLBLOCK + " real, " +
            KEY_SPELLBLOCKPERLEVEL + " real, " +
            KEY_ATTACKRANGE + " real, " +
            KEY_HPREGEN + " real, " +
            KEY_HPREGENPERLEVEL + " real, " +
            KEY_MPREGEN + " real, " +
            KEY_MPREGENPERLEVEL + " real, " +
            KEY_CRIT + " real, " +
            KEY_CRITPERLEVEL + " real, " +
            KEY_ATTACKDAMAGE + " real, " +
            KEY_ATTACKDAMAGEPERLEVEL + " real, " +
            KEY_ATTACKSPEEDOFFSET + " real, " +
            KEY_ATTACKSPEEDPERLEVEL + " real, " +
            KEY_SPELLS + " text not null, " +
            KEY_PASSIVE_NAME + " text not null, " +
            KEY_PASSIVE_DESCRIPTION + " text not null, " +
            KEY_PASSIVE_IMAGE_FULL + " text not null, " +
            KEY_PASSIVE_IMAGE_SPRITE + " text not null, " +
            KEY_PASSIVE_X + " integer, " +
            KEY_PASSIVE_Y + " integer, " +
            KEY_PASSIVE_W + " integer, " +
            KEY_PASSIVE_H + " integer, " +
            ");";

    private static final String DATABASE_CREATE_SKINS = "create table "
            + TABLE_SKIN + "(" +
            KEY_ID + " integer, " +
            KEY_NUM + " integer, " +
            KEY_NAME + " text not null, " +
            ");";

    private static final String DATABASE_CREATE_SPELLS = "create table "
            + TABLE_SPELL + "(" +
            KEY_ID + " text primary key, " +
            KEY_NAME + " text not null, " +
            KEY_DESCRIPTION + " text not null, " +
            KEY_TOOLTIP + " text not null, " +
            KEY_LABEL + " text not null, " +
            KEY_TOOLTIP_EFFECT + " text not null, " +
            KEY_MAXRANK + " integer, " +
            KEY_COOLDOWN + "1 integer, " +
            KEY_COOLDOWN + "2 integer, " +
            KEY_COOLDOWN + "3 integer, " +
            KEY_COOLDOWN + "4 integer, " +
            KEY_COOLDOWN + "5 integer, " +
            KEY_COOLDOWNBURN + " text not null, " +
            KEY_COST + "1 integer, " +
            KEY_COST + "2 integer, " +
            KEY_COST + "3 integer, " +
            KEY_COST + "4 integer, " +
            KEY_COST + "5 integer, " +
            KEY_COSTBURN + " text not null, " +
            KEY_EFFECT + " text not null, " +
            KEY_EFFECTBURN + "1 text not null, " +
            KEY_EFFECTBURN + "2 text not null, " +
            KEY_EFFECTBURN + "3 text not null, " +
            KEY_EFFECTBURN + "4 text not null, " +
            KEY_EFFECTBURN + "5 text not null, " +
            KEY_VARS + " text not null, " +
            KEY_COSTTYPE + " text not null, " +
            KEY_RANGE + "1 text not null, " +
            KEY_RANGE + "2 text not null, " +
            KEY_RANGE + "3 text not null, " +
            KEY_RANGE + "4 text not null, " +
            KEY_RANGE + "5 text not null, " +
            KEY_RANGEBURN + " text not null, " +
            KEY_IMAGE_FULL + " text not null, " +
            KEY_IMAGE_SPRITE + " text not null, " +
            KEY_X + " integer, " +
            KEY_Y + " integer, " +
            KEY_W + " integer, " +
            KEY_H + " integer, " +
            KEY_RESOURCE + " text not null, " +
            ");";

    private static final String DATABASE_CREATE_RECOMMENDED = "create table "
            + TABLE_RECOMMENDED + "(" +
            KEY_TITLE + " text primary key, " +
            KEY_TYPE + " text not null, " +
            KEY_MAP + " text not null, " +
            KEY_PRIORITY + " integer, " +
            KEY_BLOCKS + " blob," +
            ");";
    public DBHelper(Context context) {

        super(context, DATABSE_NAME, null, DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE_CHAMPIONS);
        db.execSQL(DATABASE_CREATE_SKINS);
        db.execSQL(DATABASE_CREATE_SPELLS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
