package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private Button button10;
    private EditText editText3, editText4;
    private TextView textView14, textView15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editText3=(EditText)findViewById(R.id.editText3);
        editText4=(EditText)findViewById(R.id.editText4);
        textView14=(TextView)findViewById(R.id.textView14);
        textView15=(TextView)findViewById(R.id.textView15);
        button10=(Button)findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RegisterActivity.this, "registration completed", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, loginActivity.class);
                String userName = editText3.getText().toString();
                String passWord = editText4.getText().toString();
                intent.putExtra("userName", userName);
                intent.putExtra("passWord", passWord);
                startActivity(intent);

            }
        });
    }
}
