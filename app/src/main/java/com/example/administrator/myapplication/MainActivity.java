package com.example.administrator.myapplication;

import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.io.IOException;
import java.io.InputStream;
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
        /*Log.d(LOG_TAG, "Initialy, size(db) ---------->\t" + n);*/
        if (dbHelper.select_new().getCount() <= 1) { //first fill of db
            fill_db();      // initialize db content
        }
        Cursor cursor = dbHelper.select_topics();
        /*if (cursor != null) {
            String info = "\t>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + cursor.toString();
                    Log.d(LOG_TAG, info);
        }*/
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String aux = cursor.getString(cursor.getColumnIndex(Contract.TNews.COLUMN_TOPIC));
                topicsArrayList.add(aux);
            }
            while (cursor.moveToNext());
        }

        /*Log.d(LOG_TAG, "Size arrayL topics: " + topicsArrayList.size());*/

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
                /*button4.setBackgroundColor(p.getColor());*/
                Drawable drawable = getResources().getDrawable(R.drawable.topics_sports);
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
                Drawable d = getResources().getDrawable(R.drawable.ic_action_name);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    button7.setBackground(d);
                }
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                intent.putExtra("category", button.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                intent.putExtra("category", button2.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                intent.putExtra("category", button3.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                intent.putExtra("category", button4.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                intent.putExtra("category", button5.getText().toString());
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the topic");
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SubcategoryActivity.class);
                intent.putExtra("category", button7.getText().toString());
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
        dbHelper.add_new("Entertainment", "", 0, "", "", Date.valueOf("2001-12-02"));
        dbHelper.add_new("Sports", "", 0, "", "", Date.valueOf("2002-01-03"));
        dbHelper.add_new("Politics", "", 0, "", "", Date.valueOf("2003-02-04"));
        dbHelper.add_new("Life style", "", 0, "", "", Date.valueOf("2004-03-05"));
        dbHelper.add_new("Business", "", 0, "", "", Date.valueOf("2005-04-06"));

        // subcategories
        dbHelper.add_new("Technology", "Computers", 1, "", "", Date.valueOf("2000-11-01"));
        dbHelper.add_new("Technology", "SmartPhones", 1, "", "", Date.valueOf("2000-11-01"));
        dbHelper.add_new("Technology", "Automobiles", 1, "", "", Date.valueOf("2010-12-11"));
        dbHelper.add_new("Technology", "Green Technology", 1, "", "", Date.valueOf("2002-11-21"));
        dbHelper.add_new("Technology", "Information & Communication Technology", 1, "", "", Date.valueOf("2003-10-15"));

        dbHelper.add_new("Sports","Formula 1",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Sports","Cricket",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Sports","Football",1,"","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Sports","Tennis",1,"","",Date.valueOf("2002-11-21"));
        dbHelper.add_new("Sports","Badminton",1,"","",Date.valueOf("2015-10-15"));

        dbHelper.add_new("Entertainment","Music",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Entertainment","Trending",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Entertainment","Hollywood",1,"","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Entertainment","Television",1,"","",Date.valueOf("2002-11-21"));
        dbHelper.add_new("Entertainment","Celebrities",1,"","",Date.valueOf("2015-10-15"));

        dbHelper.add_new("Politics","Travel",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Politics","Food",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Politics","Fashion",1,"","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Politics","Health",1,"","",Date.valueOf("2002-11-21"));

        dbHelper.add_new("Life Style","Nordic Politics",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Life Style","US Politics",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Life Style","East asian",1,"","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Life Style","Indian politics",1,"","",Date.valueOf("2002-11-21"));

        dbHelper.add_new("Business","BBC",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Business","CNN",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Business","CNBC",1,"","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Business","Forbes",1,"","",Date.valueOf("2002-11-21"));
        dbHelper.add_new("Business","Bloomberg",1,"","",Date.valueOf("2015-10-15"));

        // apple news
        dbHelper.add_new("Technology", "Computers", 1, "This Dell 2-in-1 laptop can wirelessly charge through its keyboard", "", Date.valueOf("2000-11-01"));
        dbHelper.add_new("Technology", "SmartPhones", 1, "What Samsung expects from iPhone 8\n" +
                "Samsung works pretty hard to make sure its products offer more or less the same features as Apple's iPhone, so the rumors speak volumes here", "", Date.valueOf("2000-11-01"));
        dbHelper.add_new("Technology", "Automobiles", 1, "Mercedes will give Tesla's Autopilot its first real competition this year", "", Date.valueOf("2010-12-11"));
        dbHelper.add_new("Technology", "Green Technology", 1, "The World's First Solar Road Is Open in France", "", Date.valueOf("2002-11-21"));
        dbHelper.add_new("Technology", "Information & Communication Technology", 1, "Indian Govt to bring information and communication policy: I&B minister Venkaiah Naidu", "", Date.valueOf("2003-10-15"));

        dbHelper.add_new("Sports","Formula 1",1,"Pirelli says it will need to wait until after the first few races of the 2017 Formula 1 season to know for definite if its new wider tyres have hit their targets for the new generation of cars.","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Sports","Cricket",1,"Full schedule of India-England Test, ODI, T20I series (November 2016 to February 2017)","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Sports","Football",1,"Wayne Rooney equals record as Manchester United ease past Reading","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Sports","Tennis",1,"Qatar Open: Sir Andy Murray to face Novak Djokovic in final","",Date.valueOf("2002-11-21"));
        dbHelper.add_new("Sports","Badminton",1,"Nobody can understand how tough my life is: Carolina Marin","",Date.valueOf("2015-10-15"));

        dbHelper.add_new("Entertainment","Music",1,"Ed Sheeran Delivers Long-Awaited New Music: Listen to 'Shape of You' and 'Castle on the Hill","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Entertainment","Trending",1,"Carrie Fisher's ashes carried in 'a giant Prozac pill\n" +
                "Fisher and her mom Debbie Reynolds laid to rest together at Hollywood Hills cemetery","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Entertainment","Hollywood",1,"China Is Mad About Hollywood Remakes\n" +
                "Localized films and TV shows like “Mad About You” are luring viewers to China’s streaming sites","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Entertainment","Television",1,"What's New on HBO\n" +
                "That’s a lot of blood: The Nice Guys,That’s a lot of blood: The Nice Guys.TELEVISION Sesame Street, season 47 premiere (January 7),The Young Pope, series premiere (January 15),Real Time With Bill Maher premiere (January 21)","",Date.valueOf("2002-11-21"));
        dbHelper.add_new("Entertainment","Celebrities",1,"Last year really WAS a terrible year to be a celebrity: Number of famous deaths in 2016 is only expected once every 200 years","",Date.valueOf("2015-10-15"));

        dbHelper.add_new("Politics","Travel",1,"The World's First Solar Road Is Open in France\n" +
                "It's still unknown whether the investment will pay off.","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Politics","Food",1,"Prepare Your Taste Buds: McDonald's Is Now Offering a Chicken and Waffles Sandwich.","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Politics","Fashion",1,"Award-Season-Worthy Gowns From The SS17 Shows.","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Politics","Health",1,"Whether popcorn is \"healthy\" depends how you eat it.In its purest form -- that is, plain air-popped kernels -- it's a healthy, whole-grain, antioxidant-rich snack food that comes at a pretty low-calorie cost for those who like to mindlessly nibble.","",Date.valueOf("2002-11-21"));

        dbHelper.add_new("Life Style","Nordic Politics",1,"Elections in Finland.The Centre Party holds.Finns demote their pro-European prime minister but a hard line on Greece is unlikely","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Life Style","US Politics",1,"Auto industry's Trump fear: 'Everyone dreads being subject of a tweet'.Auto executives and analysts are fretting about how Trump will use the bully pulpit of his office – and his Twitter account – to try and force a radical change","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Life Style","East asian",1,"China Ready to Step Up Scrutiny of U.S. Firms If Trump Starts Feud: Sources","",Date.valueOf("2010-12-11"));
        dbHelper.add_new("Life Style","Indian politics",1,"BJP gets battle ready with Modi, surgical strikes and demonetisation in its arsenal","",Date.valueOf("2002-11-21"));

        dbHelper.add_new("Business","BBC",1,"Trump says Toyota will face tariffs on cars made in Mexico's","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Business","CNN",1,"SpaceX gets green light days before scheduled launch","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Business","CNBC",1,"Economic forecasting in crisis: Bank of England chief economist","",Date.valueOf("2000-11-01"));
        dbHelper.add_new("Business","Forbes",1,"First Volkswagen Manager Goes To Jail For Diesel Gate","",Date.valueOf("2002-11-21"));
        dbHelper.add_new("Business","Bloomberg",1,"The Strong Dollar Could Bash the Economy—and It’s Just Getting Started.The surging greenback could slam U.S. manufacturing and trigger capital flight from emerging markets","",Date.valueOf("2015-10-15"));
    }
}
