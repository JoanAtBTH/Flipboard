package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    private TextView textView12, textView13;
    private EditText editText, editText2;
    private Button button6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView12=(TextView)findViewById(R.id.textView12);
        textView13=(TextView)findViewById(R.id.textView13);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        button6=(Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("abc")&&editText2.getText().toString().equals("123")){
                Intent intent = new Intent(loginActivity.this, MainActivity.class);
                startActivity(intent);}
                if(!editText.getText().toString().equals("abc")&&editText2.getText().toString().equals("123")){
                    Toast.makeText(loginActivity.this, "user name is wrong", Toast.LENGTH_SHORT).show();
                    }
                if(editText.getText().toString().equals("abc")&&!editText2.getText().toString().equals("123")){
                    Toast.makeText(loginActivity.this, "password is wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
