package com.example.disa.materi.tunadaksa;

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

public class materitnenam extends AppCompatActivity {

    VideoView caranaik, pengenalan, turundepan, turunbelakang;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materitnenam);
        getUsernameLocal();
        if (username_key_new.isEmpty()){


            Button tn1 = findViewById(R.id.selesaitd6);
            tn1.setClickable(false);
        }else {

            chek();

        }

        caranaik = findViewById(R.id.prakteknaik);
        MediaController mediaController = new MediaController(this);
        caranaik.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FUntitled%20Project%20%E2%80%90%20Made%20with%20Clipchamp%20(12).mp4?alt=media&token=d1c4b5d6-be91-4ba0-9023-655df2a2dfc3"));
        caranaik.setMediaController(mediaController);

        pengenalan = findViewById(R.id.pengenalankursiroda);
        MediaController mediaController1 = new MediaController(this);
        pengenalan.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FCara%20menggunakan%20kursi%20roda%20standar%20-%20YouTube.mkv?alt=media&token=afdd39f1-a530-472b-b5b2-0fb8d76ba9d6"));
        pengenalan.start();
        pengenalan.setMediaController(mediaController1);

        turundepan = findViewById(R.id.turundepan);
        MediaController mediaController3 = new MediaController(this);
        turundepan.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FUntitled%20Project%20%E2%80%90%20Made%20with%20Clipchamp%20(14).mp4?alt=media&token=bdbb763a-eb76-41dd-ad67-8465c372f7ff"));
        turundepan.setMediaController(mediaController3);

        turunbelakang = findViewById(R.id.caraturunbelakang);
        MediaController mediaController2 = new MediaController(this);
        turunbelakang.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FUntitled%20Project%20%E2%80%90%20Made%20with%20Clipchamp%20(13).mp4?alt=media&token=1b25f954-9ead-4d32-a01a-542477c74c42"));
        turunbelakang.setMediaController(mediaController2);




    }

    public void selesai6(View view){

        Intent intent = new Intent(materitnenam.this, ListMateri.class);
        startActivity(intent);
        finish();


    }
    public void pindahtd6back(View view){

        Intent intent = new Intent(materitnenam.this, materitnlima.class);
        startActivity(intent);
        finish();


    }



    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    public void selesaitd6(View view){


        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresmateri");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String nilai = dataSnapshot.child("TunaDaksa").getValue().toString();
                int nilaii = Integer.valueOf(nilai);
                nilaii+=1;
                dataSnapshot.getRef().child("TunaDaksa").setValue(nilaii);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresonklik").child("daksa");
        reference.child("materienam").setValue(1);

        Button tn1 = findViewById(R.id.selesaitd6);
        tn1.setBackgroundColor(getResources().getColor(R.color.hijaumateri));




    }

    public  void chek (){

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresonklik").child("daksa");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String nilai = dataSnapshot.child("materienam").getValue().toString();
                int nilaii = Integer.valueOf(nilai);

                if (nilaii ==1){

                    Button tn1 = findViewById(R.id.selesaitd6);
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
