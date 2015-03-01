package com.thelastcouncil.lolfiend;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raff on 2/23/2015.
 */
public class FavoritesDatabaseHelper extends SQLiteOpenHelper {

    //database version
    private final static int DATABASE_VERSON = 1;

    //database name
    private final static String DATABASE_NAME = "favoritesDB";

    //Summoner attribute keys
    private final static String KEY_ID = "id",
                                KEY_SID = "sid",
                                KEY_REGION = "region",
                                KEY_NAME = "name",
                                KEY_PROFILE_ICON_ID = "profile_icon_id",
                                KEY_LEVEL = "level",
                                KEY_LEAGUE = "league",
                                KEY_TIER = "tier",
                                KEY_LP = "lp",
                                KEY_WINS = "wins",
                                KEY_RECENT_MATCH = "recent_match";

    //favorites table name
    private final static String TABLE_FAVORITES = "favorites";

    public FavoritesDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "(" +
                                        KEY_ID + "PRIMARY INTEGER KEY, " +
                                        KEY_SID + "INTEGER" +
                                        KEY_REGION + " TEXT, " +
                                        KEY_NAME + " TEXT, " +
                                        KEY_PROFILE_ICON_ID + " INTEGER, " +
                                        KEY_LEVEL + " INTEGER, " +
                                        KEY_LEAGUE + " TEXT, " +
                                        KEY_TIER + " TEXT, " +
                                        KEY_LP + " INTEGER, " +
                                        KEY_WINS + " INTEGER, " +
                                        KEY_RECENT_MATCH + " INTEGER)";
        db.execSQL(CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(db);
    }

    //adds the given Summoner object's attributes to the "favorites" table.
    public void addSummoner(Summoner summoner) {
        SQLiteDatabase db = this.getWritableDatabase();

        //extract attributes from object into ContentValues object.
        ContentValues values = new ContentValues();
        values.put(KEY_SID, summoner.getID());
        values.put(KEY_REGION, summoner.getRegion());
        values.put(KEY_NAME, summoner.getName());
        values.put(KEY_PROFILE_ICON_ID, summoner.getProfileIconID());
        values.put(KEY_LEVEL, summoner.getLevel());
        values.put(KEY_LEAGUE, summoner.getLeague());
        values.put(KEY_TIER, summoner.getTier());
        values.put(KEY_LP, summoner.getLP());
        values.put(KEY_WINS, summoner.getWins());
        values.put(KEY_RECENT_MATCH, summoner.getRecentMatch());

        //insert values into "favorites" table.
        db.insert(TABLE_FAVORITES, null, values);
        //close the writable database.
        db.close();
    }

    //return Summoner object with given id.
    public Summoner getSummoner(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_FAVORITES, new String[] { KEY_ID,
                                                                KEY_SID,
                                                                KEY_REGION,
                                                                KEY_NAME,
                                                                KEY_PROFILE_ICON_ID,
                                                                KEY_LEVEL,
                                                                KEY_LEAGUE,
                                                                KEY_TIER,
                                                                KEY_LP,
                                                                KEY_WINS,
                                                                KEY_RECENT_MATCH }, KEY_ID + "=?", new String[] {String.valueOf(id)}, null, null, null, null );
        if (cursor != null)
            cursor.moveToFirst();

        Summoner summoner = new Summoner(Integer.parseInt(cursor.getString(1)),
                                        cursor.getString(2),
                                        cursor.getString(3),
                                        Integer.parseInt(cursor.getString(4)),
                                        Integer.parseInt(cursor.getString(5)),
                                        cursor.getString(6),
                                        cursor.getString(7),
                                        Integer.parseInt(cursor.getString(8)),
                                        Integer.parseInt(cursor.getString(9)),
                                        Integer.parseInt(cursor.getString(10)));
        return summoner;
    }

    //retrieve all Summoners from the "favorites" table.
    public List<Summoner> getAllSummoner() {
        ArrayList<Summoner> summonerList = new ArrayList<Summoner>();
        SQLiteDatabase db = this.getReadableDatabase();

        //select all the summoners in the table.
        String selectQuery = "SELECT * FROM " + TABLE_FAVORITES;
        Cursor cursor = db.rawQuery(selectQuery, null);

        //loop through all the summoners while adding them to the ArrayList.
        if(cursor.moveToFirst()) {
            do {
                Summoner summoner = new Summoner(Integer.parseInt(cursor.getString(1)),
                        cursor.getString(2),
                        cursor.getString(3),
                        Integer.parseInt(cursor.getString(4)),
                        Integer.parseInt(cursor.getString(5)),
                        cursor.getString(6),
                        cursor.getString(7),
                        Integer.parseInt(cursor.getString(8)),
                        Integer.parseInt(cursor.getString(9)),
                        Integer.parseInt(cursor.getString(10)));
                summonerList.add(summoner);
            } while (cursor.moveToNext());
        }
        return summonerList;
    }

    //return the number of Summoners in the "favorites" table.
    public int getSummonerCount() {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_FAVORITES;
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor.getCount();
    }

    //update given Summoner in the "favorites" table.
    public int updateSummoner(Summoner summoner) {
        SQLiteDatabase db = this.getWritableDatabase();

        //extract attributes from given Summoner object.
        ContentValues values = new ContentValues();
        values.put(KEY_SID, summoner.getID());
        values.put(KEY_REGION, summoner.getRegion());
        values.put(KEY_NAME, summoner.getName());
        values.put(KEY_PROFILE_ICON_ID, summoner.getProfileIconID());
        values.put(KEY_LEVEL, summoner.getLevel());
        values.put(KEY_LEAGUE, summoner.getLeague());
        values.put(KEY_TIER, summoner.getTier());
        values.put(KEY_LP, summoner.getLP());
        values.put(KEY_WINS, summoner.getWins());
        values.put(KEY_RECENT_MATCH, summoner.getRecentMatch());

        //update Summoner in table.
        return db.update(TABLE_FAVORITES, values, KEY_SID + " = ?", new String[] {String.valueOf(summoner.getID())});
    }

    //delete given Summoner from the "favorites" table.
    public void deleteSummoner(Summoner summoner) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_FAVORITES, KEY_SID + " = ?", new String[] {String.valueOf(summoner.getID())});
        db.close();
    }
}
