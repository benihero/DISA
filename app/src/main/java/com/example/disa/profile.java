package com.example.disa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.user.halamanuser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    TextView Username, Fullname, Nim, Faculty, Email;
    ImageView profile_picture;

    DatabaseReference reference, reference2;
    Button back, lihatuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getUsernameLocal();

        Username = findViewById(R.id.username_profile);
        Fullname = findViewById(R.id.fullname_profile);
        Nim = findViewById(R.id.nim_profile);
        Email = findViewById(R.id.email_profile);
        Faculty = findViewById(R.id.faculty_profile);
        profile_picture = findViewById(R.id.profile_pict2);
        lihatuser = findViewById(R.id.lihatuser);


        if (username_key_new.equals("admin")) {

            lihatuser.setVisibility(View.VISIBLE);

        } else {

            lihatuser.setVisibility(View.GONE);

        }

        lihatuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, halamanuser.class);
                startActivity(intent);
                finish();
            }
        });

        back = findViewById(R.id.backprofil);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(profile.this, Dashboard.class);
                startActivity(intent);
                finish();

            }
        });

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Username.setText(dataSnapshot.child("username").getValue().toString());
                Fullname.setText(dataSnapshot.child("fullname").getValue().toString());
                Nim.setText(dataSnapshot.child("NIM").getValue().toString());
                Faculty.setText(dataSnapshot.child("Faculty").getValue().toString());
                Email.setText(dataSnapshot.child("email").getValue().toString());
                Picasso.with(profile.this).load(dataSnapshot.child("url_photo_profile").
                        getValue().toString()).centerCrop().fit().into(profile_picture);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");

    }

    public void EditPeofile(View view) {

        Intent intent = new Intent(profile.this, edit_profile.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void onBackPressed() {

    }
}
