package com.example.disa.soal.soalnetra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disa.R;
import com.example.disa.soal.halamanutamasoal;
import com.example.disa.soal.skorDisplay;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class soalnetradisplay extends AppCompatActivity {

    RecyclerView reysoal;
    DatabaseReference reference;
    ArrayList<EntityNetra> soalll;
    AdapterNetra soaladapter;
    Button selesai,back;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soalnetradisplay);

        reysoal = findViewById(R.id.reysoalnetra);
        selesai = findViewById(R.id.selesainetra);
        back = findViewById(R.id.back_soalnetra);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(soalnetradisplay.this, halamanutamasoal.class);
                startActivity(intent);
                finish();

            }
        });

        reysoal.setLayoutManager(new LinearLayoutManager(this));
        soalll = new ArrayList<EntityNetra>();


        reference = FirebaseDatabase.getInstance().getReference().child("soal").child("Netra");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    EntityNetra p = dataSnapshot1.getValue(EntityNetra.class);
                    soalll.add(p);


                }
                soaladapter = new AdapterNetra(soalnetradisplay.this,soalll);
                reysoal.setAdapter(soaladapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<EntityNetra> entityNetras = soaladapter.getSoalnetra();

                int score = 0;

                for (EntityNetra item : entityNetras){

                    if (item.isResult() != false){

                        score +=1;


                    }


                }




                Intent intent = new Intent(soalnetradisplay.this, skorDisplay.class);
                intent.putExtra("netra","netra");
                startActivity(intent);
                finish();

            }



        });



    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    @Override
    public void onBackPressed() {

    }
}
