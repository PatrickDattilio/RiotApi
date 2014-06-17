package com.howbig.riot.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.howbig.riot.type.Vars;
import com.howbig.riot.type.champion.Image;

/**
 * Created by Patrick Dattilio on 5/18/2014.
 */
public class DBHelper extends SQLiteOpenHelper{

    private static final int DATBASE_VERSION = 1;
    private static final String DATABSE_NAME = "lol.db";

    //Table Names
    private static final String TABLE_CHAMPION = "champion";
    private static final String TABLE_SKIN = "skin";
    private static final String TABLE_SPELL = "spell";


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
    private static final String KEY_X="x";
    private static final String KEY_Y="y";
    private static final String KEY_W="w";
    private static final String KEY_H="h";
    private static final String KEY_SKINS = "skins";
    private static final String KEY_ALLYTIPS = "allytips";
    private static final String KEY_ENEMYTIPS = "enemytips";
    private static final String KEY_TAGS = "tags";
    private static final String KEY_PARTYPE = "partype";
    // Champion Info
    private static final String KEY_ATTACK = "attack";
    private static final String KEY_DEFENSE= "defense";
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
    private static final String KEY_PASSIVE_X="passive_x";
    private static final String KEY_PASSIVE_Y="passive_y";
    private static final String KEY_PASSIVE_W="passive_w";
    private static final String KEY_PASSIVE_H="passive_h";
    private static final String KEY_RECCOMMENDED="recommended";

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
   // KEY_EFFECT
    private static final String KEY_MAXRANK= "maxrank";
    private static final String KEY_COOLDOWN = "cooldown";
    private static final String KEY_COOLDOWNBURN= "cooldownBurn";
    private static final String KEY_COST= "cost";
    private static final String KEY_COSTBURN= "costBurn";
    private static final String KEY_EFFECT= "effect";
    private static final String KEY_EFFECTBURN= "effectBurn";
    private static final String KEY_VARS= "vars";
    private static final String KEY_COSTTYPE= "costType";
    private static final String KEY_RANGE= "range";
    private static final String KEY_RANGEBURN= "rangeBurn";
    // Spell Image
    // KEY_IMAGE_FULL
    // KEY_IMAGE_SPRITE
    // KEY_X
    // KEY_Y
    // KEY_W
    // KEY_H
    private static final String KEY_RESOURCE= "resource";


    // Recommended Table
    private static final String KEY_CHAMPION= "champion";
    //private static final String KEY_TITLE= "title";
    private static final String KEY_TYPE= "champion";
    private static final String KEY_MAP= "map";
    private static final String KEY_PRIORITY= "priority";
    private static final String KEY_BLOCKS= "blocks";

    private static final String DATABASE_CREATE_CHAMPION = "create table "
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
            KEY_PASSIVE_X + " text not null, " +
            KEY_PASSIVE_Y + " text not null, " +
            KEY_PASSIVE_W + " text not null, " +
            KEY_PASSIVE_H + " text not null, " +

            ");";



    public DBHelper(Context context){
        super(context,DATABSE_NAME,null,DATBASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
