package com.example.administrator.myapplication;

import android.net.Uri;
import android.provider.BaseColumns;

import java.sql.Date;

/**
 * Created by joancolom on 10/11/16.
 */
//
public class Contract {

    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.
    public static final String CONTENT_AUTHORITY = "com.example.administrator.myapplication.app";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final Uri CONTENT_URI =
            BASE_CONTENT_URI.buildUpon().build();

    public static final String TABLE_NEWS = "news";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " INTEGER";
    private static final String DATE_TYPE = " DATE";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES1 =
            "CREATE TABLE " + TABLE_NEWS + " (" +
                    TNews.COLUMN_ID + " " + INT_TYPE + " PRIMARY KEY " + COMMA_SEP +//id of the new
                    TNews.COLUMN_TOPIC + " " + TEXT_TYPE + COMMA_SEP +              //topic of the new
                    TNews.COLUMN_SUBCATEGORY + " " + TEXT_TYPE + COMMA_SEP +        //subcategory of the new
                    TNews.COLUMN_SUBSCRIBED +  " " + INT_TYPE + COMMA_SEP +         //subscribed to subcategory
                    TNews.COLUMN_NEW_CONTENT + " " + TEXT_TYPE + COMMA_SEP +        //text of the new
                    TNews.COLUMN_IMAGE +  " " + TEXT_TYPE + COMMA_SEP +             //image of new
                    TNews.COLUMN_DATE +  " " + DATE_TYPE +              //date of new
            " )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NEWS;

    public Contract() {
    }

    public static abstract class TNews implements BaseColumns {
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_TOPIC = "topic";
        public static final String COLUMN_SUBCATEGORY = "subcategory";
        public static final String COLUMN_SUBSCRIBED = "subscribed";
        public static final String COLUMN_NEW_CONTENT = "new_content";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_DATE = "date";
    }
}
