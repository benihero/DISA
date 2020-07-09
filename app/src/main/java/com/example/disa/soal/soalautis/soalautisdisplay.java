package com.example.disa.soal.soalautis;

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

public class soalautisdisplay extends AppCompatActivity {

    RecyclerView reysoal;
    DatabaseReference reference;
    ArrayList<EntityAutis> soalll;
    AdapterAutis soaladapter;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    Button selesai,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soalautisdisplay);

        reysoal = findViewById(R.id.reysoalautis);
        back = findViewById(R.id.back_soalautis);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(soalautisdisplay.this, halamanutamasoal.class);
                startActivity(intent);
                finish();

            }
        });

        reysoal.setLayoutManager(new LinearLayoutManager(this));
        soalll = new ArrayList<EntityAutis>();


        reference = FirebaseDatabase.getInstance().getReference().child("soal").child("Autis");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    EntityAutis p = dataSnapshot1.getValue(EntityAutis.class);
                    soalll.add(p);


                }
                soaladapter = new AdapterAutis( soalautisdisplay.this,soalll);
                reysoal.setAdapter(soaladapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        selesai = findViewById(R.id.selesaiautis);
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<EntityAutis> entityAutis = soaladapter.getSoalautis();

                int score =0;

                for (EntityAutis item : entityAutis){

                    if(item.isResult() != false){


                        score +=1;

                    }


                }
                getUsernameLocal();

                int nilaifix = ((score *100)/entityAutis.size());

                reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("skor").child("skorautis");
                reference.getRef().setValue(nilaifix);



                Intent intent = new Intent(soalautisdisplay.this, skorDisplay.class);
                intent.putExtra("autis","autis");
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
