package com.example.familytracker;

import android.net.Uri;

public class MakeUser {
    public String name;
    public String email;
    public String password;
    public String code;
    public String sharingCode;
    public String imageUri;
    public String lati;
    public String longi;

    public MakeUser()
    {}
    public MakeUser(String name, String email, String password, String code, String sharingCode, String imageUri, String lati, String longi) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.code = code;
        this.sharingCode = sharingCode;
        this.imageUri = imageUri;
        this.lati = lati;
        this.longi = longi;
    }

}
