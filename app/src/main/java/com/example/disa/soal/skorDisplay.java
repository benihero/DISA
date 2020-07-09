package com.example.disa.soal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class skorDisplay extends AppCompatActivity {

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    TextView tampil;

    DatabaseReference reference;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(skorDisplay.this, halamanutamasoal.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skor_display);
        getUsernameLocal();
        tampil = findViewById(R.id.skormu);

        Intent intent = getIntent();
        String kunci = intent.getStringExtra("daksa");
        String kunci1 = intent.getStringExtra("autis");
        String kunci2 = intent.getStringExtra("netra");
        String kunci3 = intent.getStringExtra("tuli");
        String kunciquiz = intent.getStringExtra("quiz");

        if (kunci == null) {


            if (kunci1 == null) {


                if (kunciquiz == null) {


                    if (kunci2 == null) {


                        if (kunci3 == null) {


                        } else {


                            reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("skor").child("skortuli");
                            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    tampil.setText(dataSnapshot.getValue(). toString());
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }


                    } else {


                        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("skor").child("skornetra");
                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                tampil.setText(dataSnapshot.getValue().toString());
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });


                    }


                } else {


                    reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("skorquiz");
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            tampil.setText(dataSnapshot.getValue().toString());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }


            } else {


                reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("skor").child("skorautis");
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        tampil.setText(dataSnapshot.getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }


        } else {


            reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("skor").child("skordaksa");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    tampil.setText(dataSnapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }


    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }
}
