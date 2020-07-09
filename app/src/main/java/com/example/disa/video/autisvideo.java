package com.example.disa.video;

import android.content.Intent;
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

public class autisvideo extends AppCompatActivity {


    Button playat,back;
    DatabaseReference reference;
    String vid_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autisvideo);

        playat = findViewById(R.id.playoneat);
        back = findViewById(R.id.back_videoautis);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(autisvideo.this,Lihat_Video.class);
                startActivity(intent);
                finish();
            }
        });


        playat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reference = FirebaseDatabase.getInstance().getReference().child("vidio");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        vid_url = dataSnapshot.child("videoautissatu").getValue().toString();

                        Intent intent = new Intent(autisvideo.this, PlayVideo.class);
                        intent.putExtra("EXTRA_SESSION_ID", vid_url);
                        startActivity(intent);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });


    }

    @Override
    public void onBackPressed() {

    }
}
