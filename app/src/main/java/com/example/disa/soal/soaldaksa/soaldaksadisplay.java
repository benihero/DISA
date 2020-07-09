package com.example.disa.soal.soaldaksa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

public class soaldaksadisplay extends AppCompatActivity {

    RecyclerView reysoal;
    DatabaseReference reference;
    ArrayList<EntityDaksa> soalll;
    AdapterDaksa soaladapter;
    Button selesai,back;
    Context context;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soaldaksadisplay);


        reysoal = findViewById(R.id.reysoaldaksa);
        selesai = findViewById(R.id.selesaidaksa);
        back = findViewById(R.id.back_soaldaksa);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(soaldaksadisplay.this, halamanutamasoal.class);
                startActivity(intent);
                finish();

            }
        });

        reysoal.setLayoutManager(new LinearLayoutManager(this));
        soalll = new ArrayList<EntityDaksa>();


        reference = FirebaseDatabase.getInstance().getReference().child("soal").child("Daksa");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    EntityDaksa p = dataSnapshot1.getValue(EntityDaksa.class);
                    soalll.add(p);
                }
                soaladapter = new AdapterDaksa(soaldaksadisplay.this,soalll);
                reysoal.setAdapter(soaladapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int point = 0;

                ArrayList<EntityDaksa> hasilresult = soaladapter.getSoalDaksa();

                for (EntityDaksa item : hasilresult ){

                    if (item.getResult() != false){

                        point+=1;

                    }


                }

                int endpoint = (point * 100)/ hasilresult.size();
                getUsernameLocal();

                reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("skor").child("skordaksa");
                reference.getRef().setValue(endpoint);

                Toast.makeText(getApplicationContext(),"skor anda : " + endpoint,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(soaldaksadisplay.this, skorDisplay.class);
                intent.putExtra("daksa","daksa");
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
