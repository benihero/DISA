package com.example.disa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class Startexplore extends AppCompatActivity {

    Button startlogin;
    TextView createnew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startexplore);


        startlogin = findViewById(R.id.sign_button);
        createnew = findViewById(R.id.creatnew);



        startlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Startexplore.this,loginActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    public void Createnew (View view){

        Intent intent = new Intent(Startexplore.this, Registerpage.class);
        startActivity(intent);

    }

    public void explorer(View view){

        Intent intent = new Intent(Startexplore.this,Dashboard.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Startexplore.this.finish();
    }
}
