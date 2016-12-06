package com.example.administrator.myapplication;

import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button button,button2,button3,button4,button5,button7;
    private ArrayList topicsArrayList;
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button7 = (Button) findViewById(R.id.button7);

        topicsArrayList = new ArrayList<String>();
        DBHelper dbHelper = DBHelper.getInstance(this);
        int n = dbHelper.select_new().getCount();
        Log.d(LOG_TAG, "Initialy, size(db) ---------->\t" + n);
        if (dbHelper.select_new().getCount() <= 1) { //first fill of db
            fill_db();      // initialize db content
        }
        Cursor cursor = dbHelper.select_topics();
        if (cursor != null) {
            String info = "\t>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + cursor.toString();
                    Log.d(LOG_TAG, info);
        }
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String aux = cursor.getString(cursor.getColumnIndex(Contract.TNews.COLUMN_TOPIC));
                topicsArrayList.add(aux);
            }
            while (cursor.moveToNext());
        }

        Log.d(LOG_TAG, "Size arrayL topics: " + topicsArrayList.size());

        if (!topicsArrayList.isEmpty()) {
            //Random color = new Random();
            Paint p = new Paint();
            //p.setARGB(200, color.nextInt(256), color.nextInt(256), color.nextInt(256));
            p.setARGB(200, 255, 0, 0);
            button.setText(topicsArrayList.get(0).toString());
            button.setTypeface(null, Typeface.BOLD);
            button.setBackgroundColor(p.getColor());
            if (topicsArrayList.size() > 1) {
                //p.setARGB(200, color.nextInt(256), color.nextInt(256), color.nextInt(256));
                p.setARGB(200, 0, 255, 0);
                button2.setText(topicsArrayList.get(1).toString());
                button2.setTypeface(null, Typeface.BOLD);
                button2.setBackgroundColor(p.getColor());
            }
            if (topicsArrayList.size() > 2) {
                //p.setARGB(200, color.nextInt(256), color.nextInt(256), color.nextInt(256));
                p.setARGB(200, 0, 0, 255);
                button3.setText(topicsArrayList.get(2).toString());
                button3.setTypeface(null, Typeface.BOLD);
                button3.setBackgroundColor(p.getColor());
            }
            if (topicsArrayList.size() > 3) {
                //p.setARGB(200, color.nextInt(256), color.nextInt(256), color.nextInt(256));
                p.setARGB(200, 255, 255, 0);
                button4.setText(topicsArrayList.get(3).toString());
                button4.setTypeface(null, Typeface.BOLD);
                button4.setBackgroundColor(p.getColor());
            }
            if (topicsArrayList.size() > 4) {
                //p.setARGB(200, color.nextInt(256), color.nextInt(256), color.nextInt(256));
                p.setARGB(200, 0, 255, 255);
                button5.setText(topicsArrayList.get(4).toString());
                button5.setTypeface(null, Typeface.BOLD);
                button5.setBackgroundColor(p.getColor());
            }
            if (topicsArrayList.size() > 5) {
                //p.setARGB(200, color.nextInt(256), color.nextInt(256), color.nextInt(256));
                p.setARGB(200, 255, 0, 255);
                button7.setText(topicsArrayList.get(5).toString());
                button7.setTypeface(null, Typeface.BOLD);
                button7.setBackgroundColor(p.getColor());
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = new Intent();

        if (id == R.id.help_item)
            intent = new Intent(MainActivity.this, HelpActivity.class);
        else if (id == R.id.subscribe_item)
            intent = new Intent(MainActivity.this, SubscribeActivity.class);

        //noinspection SimplifiableIfStatement
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, 0);
        else
            Log.e(LOG_TAG, "Error on changing view with main options");

        return super.onOptionsItemSelected(item);
    }

    /**
     *  private methods of the class
     */

    /*
     * first fill of the database with initial content
     */
    private void fill_db() {
        DBHelper dbHelper = DBHelper.getInstance(this);

        // topics
        dbHelper.add_new("Technology", "", 0, "", "", Date.valueOf("2000-11-01"));
        dbHelper.add_new("Science", "", 0, "", "", Date.valueOf("2001-12-02"));
        dbHelper.add_new("Sports", "", 0, "", "", Date.valueOf("2002-01-03"));
        dbHelper.add_new("Culture", "", 0, "", "", Date.valueOf("2003-02-04"));
        dbHelper.add_new("Trending topics", "", 0, "", "", Date.valueOf("2004-03-05"));
        dbHelper.add_new("News", "", 0, "", "", Date.valueOf("2005-04-06"));

        // subcategories
        dbHelper.add_new("Technology", "Android", 0, "", "", Date.valueOf("2000-11-01"));
        dbHelper.add_new("Technology", "Apple", 1, "", "", Date.valueOf("2000-11-01"));
        dbHelper.add_new("Technology", "Gadgets", 1, "", "", Date.valueOf("2010-12-11"));
        dbHelper.add_new("Technology", "Google", 0, "", "", Date.valueOf("2002-11-21"));
        dbHelper.add_new("Technology", "Mac OS", 0, "", "", Date.valueOf("2003-10-15"));
    }
}
