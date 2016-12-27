package com.example.administrator.myapplication;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.content.Intent;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.administrator.myapplication.R.layout.lv_subscribe_item;

public class SubscribeActivity extends AppCompatActivity {
    /*private TextView textView5, textView6, textView7,textView8;
    private CheckBox checkBox,checkBox2, checkBox3, checkBox4;*/
    private ListView lvSubscribe;
    private Button btnGoHome;
    private List<ListModelSubscribe> subscribeList;
    private SubscribeAdapter adapter;
    private TextView textView;
    private CheckBox checkBox;
    private final String LOG_TAG = SubscribeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        lvSubscribe = (ListView) findViewById(R.id.lvSubscribe);
        textView = (TextView) findViewById(R.id.tvSubscribedSubcategory);
        checkBox = (CheckBox) findViewById(R.id.cbSubscribedSubcategory);
        btnGoHome = (Button) findViewById(R.id.btnSubscribeGoHome);

        //Debugging info
        /*String info = "Initial arrayList:\n\t\t";
        info += dataToString();
        Log.d(LOG_TAG, info);*/

        fillArrayList();

        //Debugging info
        /*info = "After fill, arrayList:\n\t\t";
        info += dataToString();
        Log.d(LOG_TAG, info);*/

        // Apply adapter to ListView
        setListViewAdapter();

        lvSubscribe.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                ListModelSubscribe lms = (ListModelSubscribe)lvSubscribe.getSelectedItem();
                String msupplier = lms.getName();

                Log.d(LOG_TAG, "Selected item :\n\n\t" + msupplier);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) { }
        });

        lvSubscribe.setOnItemClickListener(new  AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListModelSubscribe lms = (ListModelSubscribe)lvSubscribe.getItemAtPosition(i);
                Log.d(LOG_TAG, "clicked element:\n\t" + lms.getName());
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
            intent = new Intent(SubscribeActivity.this, HelpActivity.class);
        else if (id == R.id.subscribe_item)
            intent = new Intent(SubscribeActivity.this, SubscribeActivity.class);

        //noinspection SimplifiableIfStatement
        if (id != R.id.subscribe_item && intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, 0);
        else if (id != R.id.subscribe_item)
            Log.e(LOG_TAG, "Error on changing view with SubscribeAct options");
        return super.onOptionsItemSelected(item);
    }

    public void onClickGoHomeView(View view) {
        String data = dataToString();
        Log.d(LOG_TAG, data);
        Intent intent = new Intent(this, MainActivity.class);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 0);
        }
        else {
            Log.e(LOG_TAG, "Error on changing view with go home");
        }
    }

    /*
     * Private methods
     */
    private void fillArrayList() {
        subscribeList = new ArrayList<ListModelSubscribe>();
        DBHelper db = DBHelper.getInstance(this);
        Cursor cursor = db.select_subcategories();
        try {
            if (cursor != null && cursor.moveToFirst()) {
                /*Log.d(LOG_TAG, "Number subcategories:\n\t\t" + cursor.getCount());*/
                do {
                    String subcat = cursor.getString(cursor.getColumnIndex(Contract.TNews.COLUMN_SUBCATEGORY));
                    Boolean subscribed = db.isSubscribed(subcat);
                    ListModelSubscribe subscription = new ListModelSubscribe(subcat, subscribed);
                    if (subcat != "")
                        subscribeList.add(subscription);
                }
                while (cursor.moveToNext());
            }
        }
        catch (Exception e) {
            Log.d(LOG_TAG, "Error reading subcategories \n\t\tfrom DB");
        }
    }

    private void setListViewAdapter() {
        adapter = new SubscribeAdapter(this, subscribeList);
        lvSubscribe.setAdapter(adapter);
    }

    private String dataToString() {
        String result = "null";
        if (subscribeList != null) {
            int n = subscribeList.size();
            ListModelSubscribe subscription;
            result = "";
            for (int i = 0; i < n; i++) {
                subscription = (ListModelSubscribe) subscribeList.get(i);
                result += "(  " + subscription.getName() + ", " + subscription.isSubscribed() + "   ) ;";
            }
        }
        return result;
    }

    private void updateDataBase() {

    }
}
