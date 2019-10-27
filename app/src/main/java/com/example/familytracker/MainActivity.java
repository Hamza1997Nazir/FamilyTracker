package com.example.familytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToLogin(View v)
    {
        Intent myIntent = new Intent(MainActivity.this,loginActivity.class);
        startActivity(myIntent);
    }

    public void goToRegister(View v)
    {
        Intent myIntent = new Intent(MainActivity.this,Register.class);
        startActivity(myIntent);
    }
}
