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
    private Button button6, button9;
    private String [] a = new String [10];
    private String [] b = new String [10];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView12=(TextView)findViewById(R.id.textView12);
        textView13=(TextView)findViewById(R.id.textView13);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        button6=(Button)findViewById(R.id.button6);
        button9=(Button)findViewById(R.id.button9);
        a[0]="abc";
        b[0]="123";

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                if(bundle!=null) {
                    String userName = bundle.getString("userName");
                    String passWord = bundle.getString("passWord");
                    for (int i = 1; i < 10; i++) {
                        if (a[i]!=null) {

                        }
                        else{
                            a[i]=userName;
                            b[i]=passWord;
                            break;
                        }
                    }
                    //a[1]=userName;
                    //b[1]=passWord;
                }
                for(int i=0;i<10;i++) {
                    if (editText.getText().toString().equals(a[i]) && editText2.getText().toString().equals(b[i])) {
                        Intent intent1 = new Intent(loginActivity.this, MainActivity.class);
                        startActivity(intent1);
                    }
                }

            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent2 = new Intent(loginActivity.this, RegisterActivity.class);
                    startActivity(intent2);
            }
        });
    }
}

