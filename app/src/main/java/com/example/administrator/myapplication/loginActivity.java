package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;


public class loginActivity extends AppCompatActivity {
    private TextView textView12, textView13;
    private EditText editText, editText2;
    private Button button6, button9;
    private CheckBox checkBox12;
    private SharedPreferences sp;
    private SharedPreferences st;
    private static final String PREFERENCE_NAME="temp_data";


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
        checkBox12=(CheckBox)findViewById(R.id.checkBox12);
        sp=getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        st=getSharedPreferences("syllabus",MODE_PRIVATE);
        final String userName=sp.getString("userName","");
        final String passWord=sp.getString("passWord","");
        final Boolean isChecked=st.getBoolean("isChecked",false);
        editText.setText(userName);
        editText2.setText(passWord);
        checkBox12.setChecked(isChecked);


        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sp.edit();
                SharedPreferences.Editor editor1=st.edit();
                Intent intent = getIntent();
                Bundle bundle = intent.getExtras();
                if(bundle!=null) {
                    String userName = bundle.getString("userName");
                    String passWord = bundle.getString("passWord");
                    editor.putString("userName",userName);
                    editor.putString("passWord",passWord);
                    editor.apply();
                }
                if(editText.getText().toString().equals(sp.getString("userName",""))&&editText2.getText().toString().equals(sp.getString("passWord",""))){
                    if (checkBox12.isChecked()) {
                        editor.putString("userName", editText.getText().toString());
                        editor.putString("passWord", editText2.getText().toString());
                        editor1.putBoolean("isChecked",true);
                        editor.apply();
                        editor1.apply();
                    }else{
                        editor.putString("userName", "");
                        editor.putString("passWord", "");
                        editor1.putBoolean("isChecked",false);
                        editor.apply();;
                        editor1.apply();
                    }
                    Intent intent1 = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(intent1);
                }else if(editText.getText().toString().equals("abc")&&editText2.getText().toString().equals("123")){
                    if (checkBox12.isChecked()) {
                        editor.putString("userName", editText.getText().toString());
                        editor.putString("passWord", editText2.getText().toString());
                        editor1.putBoolean("isChecked",true);
                        editor.apply();
                        editor1.apply();


                    }else{
                        editor.putString("userName", "");
                        editor.putString("passWord", "");
                        editor1.putBoolean("isChecked",false);
                        editor.apply();
                        editor1.apply();
                    }
                    Intent intent1 = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(intent1);
                }else{
                    Toast.makeText(loginActivity.this, "log in message is wrong", Toast.LENGTH_SHORT).show();
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

