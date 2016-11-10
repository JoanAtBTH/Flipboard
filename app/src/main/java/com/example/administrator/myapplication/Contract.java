package com.example.administrator.myapplication;

import android.net.Uri;
import android.provider.BaseColumns;

import java.sql.Date;

/**
 * Created by joancolom on 10/11/16.
 */

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
    private static final String DATE_TYPE = " DATE";
    private static final String COMMA_SEP = ",";
    public static final String SQL_CREATE_ENTRIES1 =
            "CREATE TABLE " + TABLE_NEWS + " (" +
                    TNews.COLUMN_TOPIC + " " + TEXT_TYPE + COMMA_SEP +         //topic of the new
                    TNews.COLUMN_SUBCATEGORY + " " + TEXT_TYPE + COMMA_SEP +   //subcategory of the new
                    TNews.COLUMN_SOURCE_FONT + " " + TEXT_TYPE + COMMA_SEP +   //source font of the new
                    TNews.COLUMN_NEW_CONTENT + " " + TEXT_TYPE + COMMA_SEP +   //text of the new
                    TNews.COLUMN_IMAGE +  " " + TEXT_TYPE + COMMA_SEP +        //image of new
                    TNews.COLUMN_DATE +  " " + DATE_TYPE + COMMA_SEP +        //date of new
                    " PRIMARY KEY (" + TNews.COLUMN_TOPIC + COMMA_SEP + " " +  //multiple PK
                    TNews.COLUMN_SUBCATEGORY + COMMA_SEP + " " +
                    TNews.COLUMN_SOURCE_FONT + COMMA_SEP + " " +
                    TNews.COLUMN_NEW_CONTENT+ ")" +" )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NEWS;

    public Contract() {
    }

    public static abstract class TNews implements BaseColumns {

        public static final String COLUMN_TOPIC = "_topic";
        public static final String COLUMN_SUBCATEGORY = "_subcategory";
        public static final String COLUMN_SOURCE_FONT = "_source_font";
        public static final String COLUMN_NEW_CONTENT = "_new_content";
        public static final String COLUMN_IMAGE = "image";
        public static final String COLUMN_DATE = "date";
    }
}
