package com.example.disa.materi.tunanetra;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class materiempat extends AppCompatActivity {

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materiempat);

        getUsernameLocal();
        if (username_key_new.isEmpty()){


            Button tn1 = findViewById(R.id.selesaitn4);
            tn1.setClickable(false);
        }else {

            chek();

        }
    }

    public void pindah4(View view){

        Intent intent = new Intent(materiempat.this,materilima.class);
        startActivity(intent);
        finish();


    }
    public void pindah4back(View view){

        Intent intent = new Intent(materiempat.this,materitiga.class);
        startActivity(intent);
        finish();


    }
    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    public void selesaitn4(View view){


        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresmateri");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String nilai = dataSnapshot.child("TunaNetra").getValue().toString();
                int nilaii = Integer.valueOf(nilai);
                nilaii+=1;
                dataSnapshot.getRef().child("TunaNetra").setValue(nilaii);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresonklik").child("tunet");
        reference.child("materiempat").setValue(1);

        Button tn1 = findViewById(R.id.selesaitn4);
        tn1.setBackgroundColor(getResources().getColor(R.color.hijaumateri));



    }

    public  void chek (){

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresonklik").child("tunet");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String nilai = dataSnapshot.child("materiempat").getValue().toString();
                int nilaii = Integer.valueOf(nilai);

                if (nilaii ==1){

                    Button tn1 = findViewById(R.id.selesaitn4);
                    tn1.setBackgroundColor(getResources().getColor(R.color.hijaumateri));
                    tn1.setClickable(false);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
