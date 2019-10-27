package com.example.familytracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class NamePicActivity extends AppCompatActivity {

    String email,password;
    EditText et5_name;
    CircleImageView circleImageView;
    Uri resultUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_pic);
        circleImageView = (CircleImageView) findViewById(R.id.circleImageView);
        et5_name = (EditText) findViewById(R.id.editText5);
        Intent myIntent = getIntent();
        if(myIntent!=null)
        {
            email = myIntent.getStringExtra("email");
            password = myIntent.getStringExtra("password");
        }
    }

    public void generateCircleCode(View v)
    {
        Date mydate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
        String date = format.format(mydate);
        Random r = new Random();

        int n = 1000 + r.nextInt(9999);
        String code = String.valueOf(n);

        if(resultUri!=null)
        {
            Intent myIntent = new Intent(NamePicActivity.this,InviteCodeActivity.class);
            myIntent.putExtra("name",et5_name.getText().toString());
            myIntent.putExtra("email",email);
            myIntent.putExtra("password",password);
            myIntent.putExtra("date",date);
            myIntent.putExtra("sharingCode","false");
            myIntent.putExtra("code",code);
            myIntent.putExtra("imageUri",resultUri);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Choose an Image",Toast.LENGTH_SHORT).show();
        }
    }

    public void selectImage(View v)
    {
        Intent openGallery = new Intent();
        openGallery.setAction(Intent.ACTION_GET_CONTENT);
        openGallery.setType("image/*");
        startActivityForResult(openGallery,12);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 12 && resultCode == RESULT_OK && data != null)
        {
            CropImage.activity()
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);
        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                circleImageView.setImageURI(resultUri); // this shows image on the page
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

    }

}
