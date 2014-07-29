package com.howbig.riot.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class RiotContentProvider extends ContentProvider {
    public static final int CHAMPION = 10;
    public static final int ITEM = 30;
    public static final int MASTERY = 50;
    public static final int RUNE = 70;
    public static final int SUMMONER = 90;
    private static final int CHAMPION_ID = 20;
    private static final int ITEM_ID = 40;
    private static final int MASTERY_ID = 60;
    private static final int RUNE_ID = 80;
    private static final int SUMMONER_ID = 100;

    private static final String AUTHORITY = "com.howbig.riot.contentprovider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    private static final String BASE_PATH = "riot";
    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, "champion", CHAMPION);
        sURIMatcher.addURI(AUTHORITY, "champion/#", CHAMPION_ID);
        sURIMatcher.addURI(AUTHORITY, "item", ITEM);
        sURIMatcher.addURI(AUTHORITY, "item/#", ITEM_ID);
        sURIMatcher.addURI(AUTHORITY, "mastery", MASTERY);
        sURIMatcher.addURI(AUTHORITY, "mastery/#", MASTERY_ID);
        sURIMatcher.addURI(AUTHORITY, "rune", RUNE);
        sURIMatcher.addURI(AUTHORITY, "rune/#", RUNE_ID);
        sURIMatcher.addURI(AUTHORITY, "summoner", SUMMONER);
        sURIMatcher.addURI(AUTHORITY, "summoner/#", SUMMONER_ID);
    }

    DBHelper database;
    public RiotContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        //TODO Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = database.getWritableDatabase();
        int rowsDeleted = 0;
        long id = 0;
        id = sqlDB.insertWithOnConflict(getTable(uriType), null, values, SQLiteDatabase.CONFLICT_REPLACE);
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public boolean onCreate() {
        database = new DBHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
            String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(getTable(sURIMatcher.match(uri)));

        Cursor cursor = queryBuilder.query(database.getReadableDatabase(), projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
            String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private String getTable(int uri) throws IllegalArgumentException {
        String table = null;
        switch (uri) {
            case CHAMPION:
                table = DBHelper.TABLE_CHAMPION;
                break;
            case ITEM:
                table = DBHelper.TABLE_ITEM;
                break;
            case MASTERY:
                table = DBHelper.TABLE_MASTERY;
                break;
            case RUNE:
                table = DBHelper.TABLE_RUNE;
                break;
            case SUMMONER:
                table = DBHelper.TABLE_SUMMONER;
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        return table;
    }

}
