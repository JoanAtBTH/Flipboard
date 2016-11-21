package com.example.administrator.myapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;

/**
 * Created by joancolom on 10/11/16.
 */

public class DBHelper extends SQLiteOpenHelper {

    /**
     *  Private variables of the class
     */
    private final String LOG_TAG = DBHelper.class.getSimpleName();

    // Applying Singleton pattern
    private static DBHelper savedInstance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "newsDB.db";

    private ContentResolver myCR;


    /**
     *  Public methods
     * @param context
     */

    public static synchronized DBHelper getInstance(Context context) {
        if (savedInstance == null) {
            savedInstance = new DBHelper(context.getApplicationContext());
        }
        return savedInstance;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Called when configuring DB connection
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        //db.setForeignKeyConstraintsEnabled(false);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contract.SQL_CREATE_ENTRIES1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Contract.TABLE_NEWS);

            onCreate(db);
        }
    }

    /*
     *  INSERTS
     */
    public void add_new(String topic, String subcategory, Integer subscribed, String new_content, String image, Date date) throws SQLiteConstraintException {
        Integer max_id = get_max_id();
        Integer id = max_id + 1;
        ContentValues values = new ContentValues();
        values.put(Contract.TNews.COLUMN_ID, id);
        values.put(Contract.TNews.COLUMN_TOPIC, topic);
        values.put(Contract.TNews.COLUMN_SUBCATEGORY, subcategory);
        values.put(Contract.TNews.COLUMN_SUBSCRIBED, subscribed);
        values.put(Contract.TNews.COLUMN_NEW_CONTENT, new_content);
        values.put(Contract.TNews.COLUMN_IMAGE, image);
        values.put(Contract.TNews.COLUMN_DATE, date.toString());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(Contract.TABLE_NEWS, null, values);
        String selectQuery = "SELECT * FROM " + Contract.TABLE_NEWS + " " +
                "WHERE " + Contract.TNews.COLUMN_ID + "='" + id + "'";
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
        }
        catch (SQLiteException e) {
            Log.d(LOG_TAG, "Error function add_new:\n\t" + e.getMessage());
        }

        if (cursor != null && cursor.moveToFirst()) {
            NewsActivity newsActivity = new NewsActivity();
            newsActivity.setId(Integer.valueOf(cursor.getString(0)));
            newsActivity.setTopic(cursor.getString(1));
            newsActivity.setSubcategory(cursor.getString(2));
            newsActivity.setSubscribed(Integer.valueOf(cursor.getString(3)));
            newsActivity.setNewsContent(cursor.getString(4));
            newsActivity.setImage(cursor.getString(5));
            newsActivity.setDate(Date.valueOf(cursor.getString(6)));

            /* // Pay attention, data comes from DB, not from a view Form
            // We will use this function to precharge data on DB
            String res = "Result selected: id: " + newsActivity.getId() +
                ",/n topic: " + newsActivity.getTopic() +
                ",/n subcategory: " + newsActivity.getSubcategory() +
                ",/n subscribed: " + newsActivity.getSubscribed() +
                ",/n newsContent: " + newsActivity.getNewsContent() +
                ",/n image: " + newsActivity.getImage() + "\n\n";
            Log.d(LOG_TAG, res);
            */
        }
    }

    /*
     * UPDATES
     */

    public void updateNew(Integer id, String topic, String subcategory, Integer subscribed, String new_content, String image, Date date) {
        Integer subsc = new Integer(subscribed.intValue());
        if (subsc > 1) subsc = 1;   // 0 --> false ; 1 --> true
        ContentValues values = new ContentValues();
        values.put(Contract.TNews.COLUMN_TOPIC, topic);
        values.put(Contract.TNews.COLUMN_SUBCATEGORY, subcategory);
        values.put(Contract.TNews.COLUMN_SUBSCRIBED, subsc.toString());
        values.put(Contract.TNews.COLUMN_NEW_CONTENT, new_content);
        values.put(Contract.TNews.COLUMN_IMAGE, image);
        values.put(Contract.TNews.COLUMN_DATE, date.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.update(Contract.TABLE_NEWS, values, Contract.TNews.COLUMN_ID + "=" + id, null);
        }
        catch (SQLiteException e) {
            Log.d(LOG_TAG, "Error function subscribe_to_subcategory(subc):\n\t" + e.getMessage());
        }
        db.close();
    }

    /** Subscribe to a particular subcategory
     * @pre the subcategory exists in the db
     * @post all the db instances where that subcategory appears, is subscribed to
     */
    public void subsribe_to_subcategory(String subcategory) {
        Integer subsc = 1;
        ContentValues values = new ContentValues();
        values.put(Contract.TNews.COLUMN_SUBSCRIBED, subsc.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.update(Contract.TABLE_NEWS, values, Contract.TNews.COLUMN_SUBCATEGORY + "=" + subcategory, null);
        }
        catch (SQLiteException e) {
            Log.d(LOG_TAG, "Error function subscribe_to_subcategory(subc):\n\t" + e.getMessage());
        }
        db.close();
    }

    /*
     * SELECTS
     */

    /* Get all the news stored in the db */
    public Cursor select_new() {    // select all new from media
        String[] cols = new String [] {Contract.TNews.COLUMN_ID,
                Contract.TNews.COLUMN_TOPIC,
                Contract.TNews.COLUMN_SUBCATEGORY,
                Contract.TNews.COLUMN_SUBSCRIBED,
                Contract.TNews.COLUMN_NEW_CONTENT,
                Contract.TNews.COLUMN_IMAGE,
                Contract.TNews.COLUMN_DATE};
        String ORDER_BY = Contract.TNews.COLUMN_DATE + " DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.query(true, Contract.TABLE_NEWS, cols,
                    null, null, null, null, ORDER_BY, null);
        }
        catch (SQLiteException e) {
            Log.d(LOG_TAG, "Error funcio select_new: " + e.getMessage());
        }

        return cursor;
    }

    /* Get all the news of an speific topic */
    /*public Cursor select_news_by_topic(String topic) {
        String query = "Select * FROM " + Contract.TABLE_NEWS + " WHERE " +
                Contract.TNews.COLUMN_TOPIC + " =  '" + topic + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }*/

    /* Get all the news of an specific subcategory */
    public Cursor select_news_subcategory(String subcategory) {
        String query = "Select * FROM " + Contract.TABLE_NEWS + " WHERE " +
                Contract.TNews.COLUMN_SUBCATEGORY + " =  '" + subcategory + "'" +
                " ORDER BY " + Contract.TNews.COLUMN_DATE + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);
        }
        catch (SQLiteException e) {
            Log.d(LOG_TAG, "Error funcio select_news_subcategory(subcategory):\n\t" + e.getMessage());
        }

        db.close();
        return cursor;
    }

    /* Get all topics sorted by date */
    public Cursor select_topics() {
        String query = "SELECT distinct " + Contract.TNews.COLUMN_TOPIC +
                " FROM " + Contract.TABLE_NEWS +
                " ORDER BY " + Contract.TNews.COLUMN_DATE + " DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);
        }
        catch (SQLiteException e) {
            Log.d(LOG_TAG, "Error funcio select_topics: " + e.getMessage());
        }

        db.close();
        return cursor;
    }

    /* Get all subcategories sorted alphabeticaly */
    public Cursor select_subcategories() {
        String query = "SELECT distinct " + Contract.TNews.COLUMN_SUBCATEGORY +
                " FROM " + Contract.TABLE_NEWS +
                " ORDER BY " + Contract.TNews.COLUMN_SUBCATEGORY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(query, null);
        }
        catch (SQLiteException e) {
            Log.d(LOG_TAG, "Error funcio select subcategories: " + e.getMessage());
        }

        db.close();

        return cursor;
    }

    /* Get all subcategories of one topic sorted alphabeticaly */
    public Cursor select_subcategories(String topic) {
        String query = "SELECT distinct " + Contract.TNews.COLUMN_SUBCATEGORY +
                " FROM " + Contract.TABLE_NEWS +
                " WHERE " + Contract.TNews.COLUMN_TOPIC + "='" + topic + "'" +
                " ORDER BY " + Contract.TNews.COLUMN_SUBCATEGORY;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);
        }
        catch (SQLiteException e) {
            Log.d(LOG_TAG, "Error funcio select subcategories(topic): " + e.getMessage());
        }

        db.close();
        return cursor;
    }

    /* Get the maximum id in DB in order to insert a valid one automatically */
    private Integer get_max_id () {
        String query = "Select MAX(" + Contract.TNews._ID + ") FROM " + Contract.TABLE_NEWS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(query, null);
        }
        catch (SQLiteException e) {
            Log.d(LOG_TAG, "Error getting max id: " + e.getMessage());
            Integer res = 0;
            db.close();
            return res;
        }

        Integer res = 0;
        if (cursor != null && cursor.moveToFirst()) {
            res = cursor.getColumnIndex(Contract.TNews.COLUMN_ID);
            cursor.close();
            db.close();
        }

        return res;
    }

    /**
     * DELETE actions: methods for deleting
     */
    /* Delete a new with an specific ID */
    public void delete_new(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = new String [] {Contract.TNews.COLUMN_ID};
        String condition = Contract.TNews.COLUMN_ID + " = '" + id + "'";
        Cursor cursor = null;
        try {
            cursor = db.query(Contract.TABLE_NEWS, cols, condition, null, null, null, null, null);
        }
        catch (SQLiteException e) {
            Log.d(LOG_TAG, "Error: message: " + e.getMessage());
        }

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String topic = cursor.getString(cursor.getColumnIndex(
                        Contract.TNews.COLUMN_TOPIC));
                String subcategory = cursor.getString(cursor.getColumnIndex(
                        Contract.TNews.COLUMN_SUBCATEGORY));
                String subscribed = cursor.getString(cursor.getColumnIndex(
                        Contract.TNews.COLUMN_SUBSCRIBED));
                Log.d(LOG_TAG, "rec: " + id + "\ntopic: " + topic +
                    "\nsubcategory: " + subcategory + "\nsubscribed: " + subscribed);
                this.delete_new(id);
            }
            while (cursor.moveToNext());
        }
        else {
            Log.d(LOG_TAG, "Message with id = " + id + " /tdidn't exist");
        }

        String query = "DELETE FROM " + Contract.TABLE_NEWS + " WHERE " +
                Contract.TNews.COLUMN_ID + " = '" + id + "'";
        try {
            db.execSQL(query);
        }
        catch (Exception e) {
            Log.d(LOG_TAG, "Exception type: " + e.getMessage() +
            "\nwhile trying to delete new with id = " + id);
        }
    }
}
