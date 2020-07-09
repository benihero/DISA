package com.example.disa.materi.tunanetra;

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

public class materienam extends AppCompatActivity {

    VideoView duduk,naiktangga,turuntangga,bukapintu;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materienam);

        getUsernameLocal();
        if (username_key_new.isEmpty()){


            Button tn1 = findViewById(R.id.selesaitn6);
            tn1.setClickable(false);
        }else {

            chek();

        }


        duduk = findViewById(R.id.dudukkursi);
        naiktangga = findViewById(R.id.naiktangga);
        turuntangga = findViewById(R.id.turuntangga);
        bukapintu = findViewById(R.id.membukapintu);

        MediaController mediaController = new MediaController(this);

        duduk.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FUntitled%20Project%20%E2%80%90%20Made%20with%20Clipchamp%20(6).mp4?alt=media&token=d07371ce-dadd-4049-ac52-383599878cba"));
        duduk.start();
        duduk.setMediaController(mediaController);

        MediaController mediaController1 = new MediaController(this);

        naiktangga.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FUntitled%20Project%20%E2%80%90%20Made%20with%20Clipchamp%20(8).mp4?alt=media&token=23ff6bb3-86a2-428a-9524-9d1ceee57ee0"));
        naiktangga.setMediaController(mediaController1);

        MediaController mediaController2 = new MediaController(this);

        turuntangga.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FUntitled%20Project%20%E2%80%90%20Made%20with%20Clipchamp%20(7).mp4?alt=media&token=85ceb005-f0c0-42a0-9f6b-7e9dc4071f7f"));
        turuntangga.setMediaController(mediaController2);

        MediaController mediaController3 = new MediaController(this);

        bukapintu.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FUntitled%20Project%20%E2%80%90%20Made%20with%20Clipchamp%20(9).mp4?alt=media&token=c0578a81-00aa-47e0-9b78-657848a8d09c"));
        bukapintu.setMediaController(mediaController3);
    }

    public void selesai (View view){

        Intent intent = new Intent(materienam.this, ListMateri.class);
        startActivity(intent);
        finish();

    }

    public void pindah6back (View view){

        Intent intent = new Intent(materienam.this, materilima.class);
        startActivity(intent);
        finish();

    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    public void selesaitn6(View view){


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
        reference.child("materienam").setValue(1);

        Button tn1 = findViewById(R.id.selesaitn6);
        tn1.setBackgroundColor(getResources().getColor(R.color.hijaumateri));





    }

    public  void chek (){

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresonklik").child("tunet");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String nilai = dataSnapshot.child("materienam").getValue().toString();
                int nilaii = Integer.valueOf(nilai);

                if (nilaii ==1){

                    Button tn1 = findViewById(R.id.selesaitn6);
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
