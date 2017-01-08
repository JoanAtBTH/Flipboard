package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class HelpActivity extends AppCompatActivity {
    private TextView textView5;
    private final String helpInfo = "What is Lorem Ipsum?\n" +
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem " +
            "Ipsum has been the industry's standard dummy text ever since the 1500s, when an "+
            "unknown printer took a galley of type and scrambled it to make a type specimen book. " +
            "It has survived not only five centuries, but also the leap into electronic " +
            "typesetting, remaining essentially unchanged. It was popularised in the 1960s with " +
            "the release of Letraset sheets containing Lorem Ipsum passages, and more recently " +
            "with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
    private final String LOG_TAG = HelpActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setText(getHelpInfo());
    }
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
            intent = new Intent(HelpActivity.this, HelpActivity.class);
        else if (id == R.id.subscribe_item)
            intent = new Intent(HelpActivity.this, SubscribeActivity.class);
        else if (id == R.id.logout_item)
            intent = new Intent(HelpActivity.this, loginActivity.class);

        //noinspection SimplifiableIfStatement
        if (intent.resolveActivity(getPackageManager()) != null)
            startActivityForResult(intent, 0);
        else
            Log.e(LOG_TAG, "Error on changing view with help options");

        return super.onOptionsItemSelected(item);
    }

    /*
     * Getters
     */

    public String getHelpInfo() {
        return helpInfo;
    }
}
