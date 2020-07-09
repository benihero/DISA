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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class materilima extends AppCompatActivity {

    VideoView pegangan,berjalan,balikarah,jalansempit;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materilima);

        pegangan= findViewById(R.id.pegangan);
        berjalan = findViewById(R.id.berjalan);
        balikarah = findViewById(R.id.balikarah);
        jalansempit = findViewById(R.id.jalansempitt);
        getUsernameLocal();
        if (username_key_new.isEmpty()){


            Button tn1 = findViewById(R.id.selesaitn5);
            tn1.setClickable(false);
        }else {

            chek();

        }



        MediaController mediaController = new MediaController(this);

        pegangan.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FUntitled%20Project%20%E2%80%90%20Made%20with%20Clipchamp%20(1).mp4?alt=media&token=a51c3458-5714-486e-b44e-27751b7f7020"));
        pegangan.start();
        pegangan.setMediaController(mediaController);

        MediaController mediaController1 = new MediaController(this);

        berjalan.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FUntitled%20Project%20%E2%80%90%20Made%20with%20Clipchamp%20(5).mp4?alt=media&token=8adfbd32-b547-417b-8b29-ddea44642b42"));
        berjalan.setMediaController(mediaController1);




        MediaController mediaController2 = new MediaController(this);

        jalansempit.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FUntitled%20Project%20%E2%80%90%20Made%20with%20Clipchamp%20(3).mp4?alt=media&token=f13fa717-1db6-4fed-88c9-23232fc88012"));
        jalansempit.setMediaController(mediaController2);

        MediaController mediaController3 = new MediaController(this);

        balikarah.setVideoURI(Uri.parse("https://firebasestorage.googleapis.com/v0/b/beni-31058.appspot.com/o/gif%2FUntitled%20Project%20%E2%80%90%20Made%20with%20Clipchamp%20(4).mp4?alt=media&token=e9ffc4da-d36a-49f7-8397-eaa40ca13a18"));
        balikarah.setMediaController(mediaController3);
    }

    public void pindah5(View view){

        Intent intent = new Intent(materilima.this,materienam.class);
        startActivity(intent);
        finish();


    }

    public void pindah5back(View view){

        Intent intent = new Intent(materilima.this,materiempat.class);
        startActivity(intent);
        finish();


    }
    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    public void selesaitn5(View view){


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
        reference.child("materilima").setValue(1);

        Button tn1 = findViewById(R.id.selesaitn5);
        tn1.setBackgroundColor(getResources().getColor(R.color.hijaumateri));




    }

    public  void chek (){

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresonklik").child("tunet");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String nilai = dataSnapshot.child("materilima").getValue().toString();
                int nilaii = Integer.valueOf(nilai);

                if (nilaii ==1){

                    Button tn1 = findViewById(R.id.selesaitn5);
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
