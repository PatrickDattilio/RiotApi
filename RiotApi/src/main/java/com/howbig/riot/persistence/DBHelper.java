package com.howbig.riot.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

    // Champion Table columns
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_TITLE = "title";
    private static final String KEY_IMAGE_FULL = "full";
    private static final String KEY_IMAGE_SPRITE = "sprite";
    // Same as TABLE_CHAMPION private static final String KEY_IMAGE_CHAMPION
    private static final String KEY_X="x";
    private static final String KEY_Y="y";
    private static final String KEY_W="w";
    private static final String KEY_H="h";
    private static final String KEY_SKINS = "skins";
    private static final String KEY_ALLYTIPS = "allytips";
    private static final String KEY_ENEMYTIPS = "enemytips";
    private static final String KEY_TAGS = "tags";
    // Champion Info
    private static final String KEY_PARTYPE = "partype";
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





    // Skin Table columns
    // ID, NAME
    private static final String KEY_NUM = "num";

    // Spell Table columns
    // ID, NAME
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_TOOLTIP = "tooltip";
    // Spell LevelTip
    private static final String KEY_LABEL = "label";
    private static final String KEY_EFFECT = "effect";


    //private static final String DATABASE_CREATE_CHAMPION = "CREATE TABLE +


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
