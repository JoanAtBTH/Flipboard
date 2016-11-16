package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.util.ArrayList;

public class AgenciesActivity extends AppCompatActivity {
    /*private TextView textView5, textView6, textView7,textView8;
    private CheckBox checkBox,checkBox2, checkBox3, checkBox4;*/
    private ListView lvAgencies;
    private Button btnGoHome;
    private ArrayList agenciesArrayList;
    private AgenciesAdapter adapter;
    private final String LOG_TAG = AgenciesActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agencies);
        /*textView5 = (TextView) findViewById(R.id.textView5);
        textView6 = (TextView) findViewById(R.id.textView6);
        textView7 = (TextView) findViewById(R.id.textView7);
        textView8 = (TextView) findViewById(R.id.textView8);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);*/
        lvAgencies = (ListView) findViewById(R.id.lvAgencies);
        btnGoHome = (Button) findViewById(R.id.btnAgenciesGoHome);
        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgenciesActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        fillArrayList();
        setListViewAdapter();
    }

    public void onClickGoHomeView(View view) {
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
        agenciesArrayList = new ArrayList();
        agenciesArrayList.add(new ListModelAgency("BBC", false));
        agenciesArrayList.add(new ListModelAgency("New York Times", true));
        agenciesArrayList.add(new ListModelAgency("The times", false));
    }

    private void setListViewAdapter() {
        adapter = new AgenciesAdapter(this, agenciesArrayList);
        lvAgencies.setAdapter(adapter);
    }
}
