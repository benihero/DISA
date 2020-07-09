package com.example.disa.materi.tuli;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.R;
import com.example.disa.materi.ListMateri;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class materitlenam extends AppCompatActivity {

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference;

    VideoView alphabet,seharihari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materitlenam);
        getUsernameLocal();
        if (username_key_new.isEmpty()){


            Button tn1 = findViewById(R.id.selesaitl6);
            tn1.setClickable(false);
        }else {

            chek();

        }


        alphabet = findViewById(R.id.isyaratalphabet);
        seharihari = findViewById(R.id.isyaratseharihari);

        MediaController mediaController = new MediaController(this);
        alphabet.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FAlfabet%20Bahasa%20Isyarat%20Indonesia%20-%20YouTube.mkv?alt=media&token=df984ae1-3de9-4bca-8fce-6ea770340dba"));
        alphabet.start();
        alphabet.setMediaController(mediaController);

        MediaController mediaController1 = new MediaController(this);
        seharihari.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FBahasa%20Isyarat%20Salam%2C%20Kata%20Tunjuk%20Orang%20%26%20Tempat%2C%20Anggota%20Keluarga.mp4?alt=media&token=5b2f7798-59f6-4ff8-bd6e-83c0c8775f16"));
        seharihari.setMediaController(mediaController1);











    }

    public void pindahtl6(View view){

        Intent intent = new Intent(materitlenam.this, ListMateri.class);
        startActivity(intent);
        finish();


    }

    public void pindahtl6back(View view){

        Intent intent = new Intent(materitlenam.this, materitllima.class);
        startActivity(intent);
        finish();


    }



    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    public void selesaitl6(View view){

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresmateri");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String nilai = dataSnapshot.child("Tuli").getValue().toString();
                int nilaii = Integer.valueOf(nilai);
                nilaii+=1;
                dataSnapshot.getRef().child("Tuli").setValue(nilaii);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresonklik").child("tuli");
        reference.child("materienam").setValue(1);

        Button tn1 = findViewById(R.id.selesaitl6);
        tn1.setBackgroundColor(getResources().getColor(R.color.hijaumateri));




    }

    public  void chek (){

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresonklik").child("tuli");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String nilai = dataSnapshot.child("materienam").getValue().toString();
                int nilaii = Integer.valueOf(nilai);

                if (nilaii ==1){

                    Button tn1 = findViewById(R.id.selesaitl6);
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
