package com.example.familytracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class passwordActivity extends AppCompatActivity {

    String email;
    EditText pass_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        pass_4 = (EditText) findViewById(R.id.editText4);
        Intent myIntent = getIntent();
        if(myIntent!=null)
        {
            email = myIntent.getStringExtra("email");
        }
    }

    public void goToNamePicActivity(View v)
    {
        if(pass_4.getText().toString().length()>3)
        {
            Intent myIntent = new Intent(passwordActivity.this,NamePicActivity.class);
            myIntent.putExtra("email",email);
            myIntent.putExtra("password",pass_4.getText().toString());
            startActivity(myIntent);
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Password length must be more than 3",Toast.LENGTH_SHORT).show();
        }
    }
}
