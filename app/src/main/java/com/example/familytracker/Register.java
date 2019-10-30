package com.example.familytracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;

public class Register extends AppCompatActivity {

    EditText email_3;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email_3 = (EditText) findViewById(R.id.editText3);
        auth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
    }


    public void goToPasswordActivity(View v)
    {
        dialog.setMessage("Checking Email Address");
        dialog.show();
        auth.fetchSignInMethodsForEmail(email_3.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                        if(task.isSuccessful())
                        {
                            dialog.dismiss();
                            boolean  check = !task.getResult().getSignInMethods().isEmpty(); // if email does not exist before then go inside if statement

                            if(!check)
                            {
                                Intent myIntent = new Intent(Register.this,passwordActivity.class);  // moving to password screen
                                myIntent.putExtra("email",email_3.getText().toString()); // sending email as well
                                startActivity(myIntent); // go !!! to new screen - password
                                finish();
                            }
                            else
                            {
                                dialog.dismiss();
                                Toast.makeText(getApplicationContext(), "This Email is taken already", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });

    }
}
