package com.example.administrator.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private Button button,button2,button3,button4,button5,button7;
//    private ListView lvTopics;
    private ArrayList topicsArrayList;
    /*private TopicsAdapter adapter;*/
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

        topicsArrayList = new ArrayList<>();
        DBHelper dbHelper = DBHelper.getInstance(this);
        if (dbHelper.select_new() == null) { //first fill of db
            dbHelper.add_new("Technology", "", 0, "", "", Date.valueOf("2000-11-01"));
            dbHelper.add_new("Science", "", 0, "", "", Date.valueOf("2001-12-02"));
            dbHelper.add_new("Sports", "", 0, "", "", Date.valueOf("2002-01-03"));
            dbHelper.add_new("Culture", "", 0, "", "", Date.valueOf("2003-02-04"));
            dbHelper.add_new("Trending topics", "", 0, "", "", Date.valueOf("2004-03-05"));
            dbHelper.add_new("News", "", 0, "", "", Date.valueOf("2005-04-06"));
        }
        Cursor cursor = dbHelper.select_topics();
        if (cursor.moveToFirst()) {
            for (int i = 0; i < 6 && !cursor.isAfterLast(); i++) {
                String aux = cursor.getString(i);
                topicsArrayList.add(aux);
                cursor.moveToNext();
            }
        }

        if (!topicsArrayList.isEmpty()) {
            button.setText(topicsArrayList.get(0).toString());
            if (topicsArrayList.size() > 1)
                button2.setText(topicsArrayList.get(1).toString());
            if (topicsArrayList.size() > 2)
                button3.setText(topicsArrayList.get(2).toString());
            if (topicsArrayList.size() > 3)
                button4.setText(topicsArrayList.get(3).toString());
            if (topicsArrayList.size() > 4)
                button5.setText(topicsArrayList.get(4).toString());
            if (topicsArrayList.size() > 5)
                button7.setText(topicsArrayList.get(5).toString());
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.help_item) {
            Intent intent = new Intent(MainActivity.this, ContentActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
