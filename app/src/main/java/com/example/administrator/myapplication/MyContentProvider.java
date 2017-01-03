package com.example.administrator.myapplication;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

/**
 * Created by joancolom on 2/1/17.
 */

public class MyContentProvider extends ContentProvider {

    private DBHelper dbHelper;

    private static final String AUTHORITY = "com.example.administrator.myapplication.MyContentProvider";
    public static final String NEWS_TABLE = Contract.TABLE_NEWS;
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + NEWS_TABLE);

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    public static final int SUBCATEGORY = 1;
    public static final int NEWS_ID = 2;

    static {
        sURIMatcher.addURI(AUTHORITY, NEWS_TABLE, SUBCATEGORY);
        sURIMatcher.addURI(AUTHORITY, NEWS_TABLE + "/#", NEWS_ID);
    }

    @Override
    public boolean onCreate() {
        dbHelper = DBHelper.getInstance(this.getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(Contract.TABLE_NEWS);

        int uriType = sURIMatcher.match(uri);

        switch (uriType) {
            case NEWS_ID:
                queryBuilder.appendWhere(Contract.TNews.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            case SUBCATEGORY:
                break;
            default:
                throw new IllegalArgumentException("Unknown URI");
        }

        Cursor cursor = queryBuilder.query(dbHelper.getReadableDatabase(),
                projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        int uriType = sURIMatcher.match(uri);

        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();

        long id = 0;
        switch (uriType) {
            case SUBCATEGORY:
                id = sqlDB.insert(Contract.TABLE_NEWS, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return Uri.parse(NEWS_TABLE + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
        int rowsDeleted = 0;

        switch (uriType) {
            case SUBCATEGORY:
                rowsDeleted = sqlDB.delete(Contract.TABLE_NEWS,
                        selection,
                        selectionArgs);
                break;

            case NEWS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqlDB.delete(Contract.TABLE_NEWS,
                            Contract.TNews.COLUMN_ID + "=" + id,
                            null);
                }
                else {
                    rowsDeleted = sqlDB.delete(Contract.TABLE_NEWS,
                            Contract.TNews.COLUMN_ID + "=" + id
                                    + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
        int rowsUpdated = 0;

        switch (uriType) {
            case SUBCATEGORY:
                rowsUpdated = sqlDB.update(Contract.TABLE_NEWS,
                        values,
                        selection,
                        selectionArgs);
                break;
            case NEWS_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated =
                            sqlDB.update(Contract.TABLE_NEWS,
                                    values,
                                    Contract.TNews.COLUMN_ID + "=" + id,
                                    null);
                }
                else {
                    rowsUpdated =
                            sqlDB.update(Contract.TABLE_NEWS,
                                    values,
                                    Contract.TNews.COLUMN_ID + "=" + id
                                            + " and "
                                            + selection,
                                    selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }
}
