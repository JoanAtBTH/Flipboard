package com.example.administrator.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
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
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class SubcategoryActivity extends Activity {
    private ListView lvSubcategories;
    private String category;
    private final String LOG_TAG = SubcategoryActivity.class.getSimpleName();
    private ArrayList<String> subcategoriesArrayList;

    //rss feed fonts
    private static String rssNews = "http://rss.cnn.com/rss/edition_europe.rss";
    private static String rssCulture = "http://www.washingtontimes.com/rss/headlines/culture/entertainment/";
    private static String rssTrendingTopics = "http://rss.cnn.com/rss/edition.rss";
    private static String rssSports = "http://rss.cnn.com/rss/edition_football.rss";
    private static String rssTechnology = "http://rss.cnn.com/rss/edition_technology.rss";
    private static String rssScience = "http://rss.cnn.com/rss/edition_space.rss";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        lvSubcategories = (ListView) findViewById(R.id.lvSubcategories);

        category = this.getIntent().getExtras().getString("category");
        DBHelper db = DBHelper.getInstance(this);
        Cursor cursor = db.select_subcategories_subscribed(category);
        int n = 0;
        subcategoriesArrayList = new ArrayList<String>();
        if (cursor != null && cursor.moveToFirst()) {
            n = cursor.getCount();
            do {
                subcategoriesArrayList.add(cursor.getString(0).toString());
            }
            while (cursor.moveToNext());
        }
        String array[] = subcategoriesArrayList.toArray(new String[n]);
        /*String log_info = "ArrayList:\n\t\t" + subcategoriesArrayList.toString() +
                "\narray:\n\t\t";
        for (int i = 0; i < array.length; i++) {
            log_info += "  ( " + array[i] + " )  , ";
        }
        log_info += "\n\n\n";
        Log.d(LOG_TAG, log_info);*/
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, array);
        lvSubcategories.setAdapter(adapter);
        lvSubcategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String subcValue = (String) lvSubcategories.getItemAtPosition(position);
                int pos = position - lvSubcategories.getFirstVisiblePosition();
                subcValue = (String) adapterView.getItemAtPosition(pos);
                /*Log.d(LOG_TAG, "subcategory passed to newsActivity is:\n\t\t" + subcValue);*/
                Intent intent = new Intent(SubcategoryActivity.this, NewsActivity.class);
                intent.putExtra("subcategory", subcValue);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the subcategory\n\t\t" + subcValue);
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
            intent = new Intent(SubcategoryActivity.this, HelpActivity.class);
        else if (id == R.id.subscribe_item)
            intent = new Intent(SubcategoryActivity.this, SubscribeActivity.class);

        //noinspection SimplifiableIfStatement
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, 0);
        else
            Log.e(LOG_TAG, "Error on changing view with ShowActivity options");
        return super.onOptionsItemSelected(item);
    }

    // update the db news of the subcategory with the rss feed
    private void updateSubcategoryNews() {
        String rssResult, rssCategory;
        URL rssURL;
        if (category.contentEquals("News"))
            rssCategory = rssNews;
        else if (category.contentEquals("Culture"))
            rssCategory = rssCulture;
        else if (category.contentEquals("Trending topics"))
            rssCategory = rssTrendingTopics;
        else if (category.contentEquals("Sports"))
            rssCategory = rssSports;
        else if (category.contentEquals("Technology"))
            rssCategory = rssTechnology;
        else rssCategory = rssScience;
        try {
            rssURL = new URL(rssCategory);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();

            // We will get the XML from an input stream
            //xpp.setInput(getInputStream(rssURL), "UTF_8");
        } catch (MalformedURLException e) {
            Log.d(LOG_TAG, "Problem with rss url:\n\t" + e.getMessage());
        } catch (XmlPullParserException e) {
            Log.d(LOG_TAG, "Problem with xmlPullParser:\n\t" + e.getMessage());
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Subcategory Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
