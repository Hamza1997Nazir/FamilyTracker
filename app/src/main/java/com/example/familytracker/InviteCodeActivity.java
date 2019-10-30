package com.example.familytracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InviteCodeActivity extends AppCompatActivity {
    String email,password,date,sharingCode,code,name, userId;
    Uri imageUri;
    TextView tv1;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_code);
        tv1 = (TextView) findViewById(R.id.textView); //reading value from screen

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference().child("user");
        progressDialog = new ProgressDialog(this);

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
    public void registerUser(View v)
    {
        progressDialog.setMessage("Creating your Account");
        progressDialog.show();

        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {                       // if user has been created
                            MakeUser makeUser = new MakeUser(name, email, password, code, "false", "NA", "NA","NA");
                            user = auth.getCurrentUser();
                            userId = user.getUid();

                            database.child(userId).setValue(makeUser) // sending values in database Firebase
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
                                                progressDialog.dismiss(); // user has been made


                                                Toast.makeText(getApplicationContext(),"User Registered Successfully",Toast.LENGTH_SHORT).show();
                                                finish();
                                                Intent myIntent = new Intent(InviteCodeActivity.this,MyNavigationActivity.class);
                                                startActivity(myIntent);
                                            }
                                            else
                                            {
                                                progressDialog.dismiss(); // user has been made
                                                Toast.makeText(getApplicationContext(),"Cannot make user ",Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });

                        }
                    }
                });
    }
    }

