package com.example.familytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class InviteCodeActivity extends AppCompatActivity {
    String email,password,date,sharingCode,code,name;
    Uri imageUri;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);
        tv1 = (TextView) findViewById(R.id.textView); //reading value from screen
        Intent myIntent = getIntent();
        if(myIntent!=null)
        {
            email = myIntent.getStringExtra("email");
            password = myIntent.getStringExtra("password");
            name =  myIntent.getStringExtra("name");
            date = myIntent.getStringExtra("date");
            sharingCode = myIntent.getStringExtra("sharingCode");
            code = myIntent.getStringExtra("code");
            imageUri = myIntent.getParcelableExtra("imageUri");
        }

        tv1.setText(code);

    }

    }

