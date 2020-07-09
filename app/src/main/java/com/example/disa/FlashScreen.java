package com.example.disa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class FlashScreen extends AppCompatActivity {

    ProgressBar progressBar;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                getUsernameLocal();

                if(username_key_new.isEmpty()){

                    Intent intent = new Intent(FlashScreen.this, Dashboard.class);
                    startActivity(intent);
                    finish();



                }else {

                    Intent intent = new Intent(FlashScreen.this, Dashboard.class);
                    startActivity(intent);
                    finish();


                }



            }
        }, 2000);


    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }


}
