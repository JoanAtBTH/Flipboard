package com.example.administrator.myapplication;

import android.database.Cursor;
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

import java.util.ArrayList;

public class SubcategoryActivity extends AppCompatActivity {
    //private Button button11,button12,button14,button15,button16,button17,button18,button19;
    private ListView lvSubcategories;
    private String category;
    private final String LOG_TAG = SubcategoryActivity.class.getSimpleName();
    private ArrayList<String> subcategoriesArrayList;

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
        if (cursor!= null && cursor.moveToFirst()) {
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
                String  subcValue = (String) lvSubcategories.getItemAtPosition(position);
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
}
