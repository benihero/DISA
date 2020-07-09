package com.example.disa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class succes_login extends AppCompatActivity {


    Button go;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_succes_login);

        go = findViewById(R.id.start_explore);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(succes_login.this,loginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }
}
