package com.example.administrator.myapplication;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public void addNew(String topic, String subcategory, String src_font, String new_content, String image, Date date) throws SQLiteConstraintException {
        ContentValues values = new ContentValues();
        values.put(Contract.TNews.COLUMN_TOPIC, topic);
        values.put(Contract.TNews.COLUMN_SUBCATEGORY, subcategory);
        values.put(Contract.TNews.COLUMN_SOURCE_FONT, src_font);
        values.put(Contract.TNews.COLUMN_NEW_CONTENT, new_content);
        values.put(Contract.TNews.COLUMN_IMAGE, image);
        values.put(Contract.TNews.COLUMN_DATE, date.toString());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(Contract.TABLE_NEWS, null, values);
        String selectQuery = "SELECT * FROM " + Contract.TABLE_NEWS;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            NewsActivity newsActivity = new NewsActivity();
            newsActivity.setTopic(cursor.getString(0));
            newsActivity.setSubcategory(cursor.getString(1));
            newsActivity.setSrcFont(cursor.getString(2));
            newsActivity.setNewsContent(cursor.getString(3));
            newsActivity.setImage(cursor.getString(4));
            newsActivity.setDate(Date.valueOf(cursor.getString(5)));

            /* // Pay attention, data comes from DB, not from a view Form
            String res = "Result selected: topic: " + newsActivity.getTopic() +
                    ", subcategory: " + newsActivity.getSubcategory() +
                    ", srcFont: " + newsActivity.getSrcFont() +
                    ", newsContent: " + newsActivity.getNewsContent() +
                    ", image: " + newsActivity.getImage() + "\n\n";
            Log.d(LOG_TAG, res);
            */
        }
    }

    /*
     * UPDATES
     */

    public void updateNew(String topic, String subcategory, String src_font, String new_content, String image, Date date) {
        ContentValues values = new ContentValues();
        values.put(Contract.TNews.COLUMN_TOPIC, topic);
        values.put(Contract.TNews.COLUMN_SUBCATEGORY, subcategory);
        values.put(Contract.TNews.COLUMN_SOURCE_FONT, src_font);
        values.put(Contract.TNews.COLUMN_NEW_CONTENT, new_content);
        values.put(Contract.TNews.COLUMN_IMAGE, image);
        values.put(Contract.TNews.COLUMN_DATE, date.toString());

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(Contract.TABLE_NEWS, values, Contract.TNews.COLUMN_TOPIC + "=" + topic, null);
    }


    /*
     * SELECTS
     */

    public Cursor select_new() {    // select a new from media
        String[] cols = new String [] {Contract.TNews.COLUMN_TOPIC,
                Contract.TNews.COLUMN_SUBCATEGORY,
                Contract.TNews.COLUMN_SOURCE_FONT,
                Contract.TNews.COLUMN_NEW_CONTENT,
                Contract.TNews.COLUMN_IMAGE};
        String ORDER_BY = Contract.TNews.COLUMN_TOPIC + " ASC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(true, Contract.TABLE_NEWS, cols,
                null, null, null, null, ORDER_BY, null);
        return cursor;
    }

    public Cursor select_news_by_topic(String topic) {
        String query = "Select * FROM " + Contract.TABLE_NEWS + " WHERE " +
                Contract.TNews.COLUMN_TOPIC + " =  '" + topic + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public Cursor select_news_subcategory_srcFont(String subcategory, String srcFont) {
        String query = "Select * FROM " + Contract.TABLE_NEWS + " WHERE " +
                Contract.TNews.COLUMN_SUBCATEGORY + " =  " + subcategory +
                " AND " + Contract.TNews.COLUMN_SOURCE_FONT + " = " + srcFont +
                "' ORDER BY '" + Contract.TNews.COLUMN_DATE + "' DESC' + ";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }


    /**
     * DELETE actions: metodes per eliminar
     */
    /*public void delete_new(String nom_r) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] cols = new String [] {Contract.TParticipa.COLUMN_RECEPTA,
                Contract.TParticipa.COLUMN_INGREDIENT};
        String condicio = Contract.TParticipa.COLUMN_RECEPTA + " = '" + nom_r + "'";
        Cursor cursor = db.query(true, Contract.TABLE_PARTICIPA, cols,
                condicio, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                String nom_i = cursor.getString(cursor.getColumnIndex(
                        Contract.TParticipa.COLUMN_INGREDIENT));
                Log.d(LOG_TAG, "rec: " + nom_r + "\ning: " + nom_i);
                this.delete_participa(nom_r, nom_i);
            }
            while (cursor.moveToNext());
        }

        String query = "DELETE FROM " + Contract.TABLE_RECEPTES + " WHERE " +
                Contract.TRecepta.COLUMN_NOM + " = '" + nom_r + "'";
        db.execSQL(query);
    }
    */

}
