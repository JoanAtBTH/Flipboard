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
        /*button11 = (Button) findViewById(R.id.button11);
        button12 = (Button) findViewById(R.id.button12);
        button14 = (Button) findViewById(R.id.button14);
        button15 = (Button) findViewById(R.id.button15);
        button16 = (Button) findViewById(R.id.button16);
        button17 = (Button) findViewById(R.id.button17);
        button18 = (Button) findViewById(R.id.button18);
        button19 = (Button) findViewById(R.id.button19);*/

        category = this.getIntent().getExtras().getString("category");
        DBHelper db = DBHelper.getInstance(this);
        Cursor cursor = db.select_subcategories(category);
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, array);
        lvSubcategories.setAdapter(adapter);
        lvSubcategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                int subcPos = position;
                String  subcValue = (String) lvSubcategories.getItemAtPosition(position);
                Intent intent = new Intent(SubcategoryActivity.this, NewsActivity.class);
                intent.putExtra("subcategory", subcValue);
                if (intent.resolveActivity(getPackageManager()) != null)
                    startActivityForResult(intent, 0);
                else
                    Log.e(LOG_TAG, "Error on changing view clicking the subcategory\n\t\t" + subcValue);
            }
        });
        /*int i = 0;

        if (i < n) {
            final String subc11 = subcategoriesArrayList.get(i);
            button11.setText(subc11);
            button11.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SubcategoryActivity.this, NewsActivity.class);
                    intent.putExtra("subcategory", subc11);
                    if (intent.resolveActivity(getPackageManager()) != null)
                        startActivityForResult(intent, 0);
                    else
                        Log.e(LOG_TAG, "Error on changing view clicking the subcategory");
            //    }
            });
            i++;
        }
        else button11.setVisibility(0);
        if (i < n) {
            final String subc12 = subcategoriesArrayList.get(i);
            button12.setText(subc12);
            button12.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SubcategoryActivity.this, NewsActivity.class);
                    intent.putExtra("subcategory", subc12);
                    if (intent.resolveActivity(getPackageManager()) != null)
                        startActivityForResult(intent, 0);
                    else
                        Log.e(LOG_TAG, "Error on changing view clicking the subcategory");
                }
            });
            i++;
        }
        else button12.setVisibility(0);
        if (i < n) {
            final String subc14 = subcategoriesArrayList.get(i);
            button14.setText(subc14);
            button14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SubcategoryActivity.this, NewsActivity.class);
                    intent.putExtra("subcategory", subc14);
                    if (intent.resolveActivity(getPackageManager()) != null)
                        startActivityForResult(intent, 0);
                    else
                        Log.e(LOG_TAG, "Error on changing view clicking the subcategory");
                }
            });
            i++;
        }
        else button14.setVisibility(0);
        if (i < n) {
            final String subc15 = subcategoriesArrayList.get(i);
            button15.setText(subc15);
            button15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SubcategoryActivity.this, NewsActivity.class);
                    intent.putExtra("subcategory", subc15);
                    if (intent.resolveActivity(getPackageManager()) != null)
                        startActivityForResult(intent, 0);
                    else
                        Log.e(LOG_TAG, "Error on changing view clicking the subcategory");
                }
            });
            i++;
        }
        else button15.setVisibility(0);
        if (i < n) {
            final String subc16 = subcategoriesArrayList.get(i);
            button16.setText(subc16);
            button16.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SubcategoryActivity.this, NewsActivity.class);
                    intent.putExtra("subcategory", subc16);
                    if (intent.resolveActivity(getPackageManager()) != null)
                        startActivityForResult(intent, 0);
                    else
                        Log.e(LOG_TAG, "Error on changing view clicking the subcategory");
                }
            });
            i++;
        }
        else button16.setVisibility(0);
        if (i < n) {
            final String subc17 = subcategoriesArrayList.get(i);
            button17.setText(subc17);
            button17.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SubcategoryActivity.this, NewsActivity.class);
                    intent.putExtra("subcategory", subc17);
                    if (intent.resolveActivity(getPackageManager()) != null)
                        startActivityForResult(intent, 0);
                    else
                        Log.e(LOG_TAG, "Error on changing view clicking the subcategory");
                }
            });
            i++;
        }
        else button17.setVisibility(0);
        if (i < n) {
            final String subc18 = subcategoriesArrayList.get(i);
            button18.setText(subc18);
            button18.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SubcategoryActivity.this, NewsActivity.class);
                    intent.putExtra("subcategory", subc18);
                    if (intent.resolveActivity(getPackageManager()) != null)
                        startActivityForResult(intent, 0);
                    else
                        Log.e(LOG_TAG, "Error on changing view clicking the subcategory");
                }
            });
            i++;
        }
        else button18.setVisibility(0);
        if (i < n) {
            final String subc19 = subcategoriesArrayList.get(i);
            button19.setText(subc19);
            button19.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SubcategoryActivity.this, NewsActivity.class);
                    intent.putExtra("subcategory", subc19);
                    if (intent.resolveActivity(getPackageManager()) != null)
                        startActivityForResult(intent, 0);
                    else
                        Log.e(LOG_TAG, "Error on changing view clicking the subcategory");
                }
            });
            i++;
        }
        else button19.setVisibility(0);*/
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
