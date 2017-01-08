package com.example.administrator.myapplication;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity {
    private ListView listView;
    private NewsAdapter adapter;
    private List<ListModelNews> newsList;
    private Integer id, subscribed;
    private String topic, subcategory, tittle, newsContent, image;
    private TextView tvTittle, tvContent;
    private ImageView ivImage;
    private Date date;
    private ArrayList<String> arrayList;
    private final String LOG_TAG = NewsActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        listView = (ListView) findViewById(R.id.lvNews);
        tvTittle = (TextView) findViewById(R.id.tvTittleNews);
        tvContent = (TextView) findViewById(R.id.tvContentNews);
        ivImage = (ImageView) findViewById(R.id.ivNews);

        subcategory = (String)this.getIntent().getExtras().getString("subcategory");

        fillArrayList();

        String array[] = arrayList.toArray(new String[arrayList.size()]);
        adapter = new NewsAdapter(this, newsList);
        listView.setAdapter(adapter);
    }

    private void fillArrayList() {
        newsList = new ArrayList<ListModelNews>();
        DBHelper db = DBHelper.getInstance(this);
        Cursor cursor = db.select_news_subcategory(subcategory);
        /*Log.d(LOG_TAG, "Subcategory recibed on NewsActivity:\n\t\t" + subcategory);*/

        int n = 0;
        arrayList = new ArrayList<String>();
        if (cursor!= null && cursor.moveToFirst()) {
            n = cursor.getCount();
            /*Log.d(LOG_TAG, "Number of rows of the query:\n\t\t" + n);*/
            do {
                tittle = "tiitle";
                newsContent = cursor.getString(cursor.getColumnIndex(Contract.TNews.COLUMN_NEW_CONTENT)).toString();
                image = cursor.getString(cursor.getColumnIndex(Contract.TNews.COLUMN_IMAGE)).toString();
                /*if (image != "") {
                    Bitmap bitmap = new BitmapFactory().decodeFile(image);
                    Drawable d = new BitmapDrawable(bitmap);
                }*/
                ListModelNews modelNews = new ListModelNews(tittle, newsContent);
                newsList.add(modelNews);
            }
            while (cursor.moveToNext());
        }
        /*else Log.d(LOG_TAG, "Cursor is null or unable to moveToFirst()");
        Log.d(LOG_TAG, "ArrayList of news:\n\t\t" + arrayList.toString());*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent = new Intent();

        if (id == R.id.help_item)
            intent = new Intent(NewsActivity.this, HelpActivity.class);
        else if (id == R.id.subscribe_item)
            intent = new Intent(NewsActivity.this, SubscribeActivity.class);

        //noinspection SimplifiableIfStatement
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, 0);
        else
            Log.e(LOG_TAG, "Error on changing view with NewsActivity options");

        return super.onOptionsItemSelected(item);
    }



    //Getters and setters #######To Be Talked

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public Integer getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(Integer subscribed) {
        this.subscribed = subscribed;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
