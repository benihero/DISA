package com.example.disa;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.materi.ListMateri;
import com.example.disa.pengalaman.Pengalaman;
import com.example.disa.penjadwalan.lihat_jadwal;
import com.example.disa.soal.halamanutamasoal;
import com.example.disa.video.Lihat_Video;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Dashboard extends AppCompatActivity {

    TextView username_dash;
    ImageView profilpic;

    Button logoutt,jadwal;


    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    LinearLayout soal,pengalaman;


    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        getUsernameLocal();


        username_dash = findViewById(R.id.username_user);
        profilpic = findViewById(R.id.profile_pict);
        jadwal = findViewById(R.id.jadwal_pelatihan);

        if(username_key_new.equals("")){


            profilpic.setClickable(false);


        }





        soal = findViewById(R.id.liniersoal);
        pengalaman = findViewById(R.id.linierpengalaman);

        if (username_key_new.isEmpty()) {

            soal.setVisibility(View.GONE);
            pengalaman.setVisibility(View.GONE);
            jadwal.setVisibility(View.GONE);

        }


        if (username_key_new.isEmpty()) {

            username_dash.setText("Silahkan login untuk fitur lebih");
        } else {

            reference = FirebaseDatabase.getInstance().getReference()
                    .child("User").child(username_key_new);

            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    username_dash.setText(dataSnapshot.child("fullname").getValue().toString());
                    Picasso.with(Dashboard.this).load(dataSnapshot.child("url_photo_profile").
                            getValue().toString()).centerCrop().fit().into(profilpic);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


        }
    }

    public void materi(View view) {

        Intent intent = new Intent(Dashboard.this, ListMateri.class);
        startActivity(intent);
        finish();


    }

    public void soal(View view) {

        Intent intent = new Intent(Dashboard.this, halamanutamasoal.class);
        startActivity(intent);
        finish();


    }

    public void Pengalaman(View view) {

        Intent intent = new Intent(Dashboard.this, Pengalaman.class);
        startActivity(intent);
        finish();


    }

    public void video(View view) {

        Intent intent = new Intent(Dashboard.this, Lihat_Video.class);
        startActivity(intent);
        finish();


    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    public void OpenProfile (View view){

        Intent intent = new Intent(Dashboard.this,profile.class);
        startActivity(intent);
        finish();


    }
    public void Pelatihan (View view){

        Intent intent = new Intent(Dashboard.this, lihat_jadwal.class);
        startActivity(intent);
        finish();



    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("Do you want exit ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
                Dashboard.this.finish();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onResume();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();


    }

    public void Logoutt(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setMessage("Do you want Logout ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key,"");
                editor.apply();

                Intent intent = new Intent(Dashboard.this,Startexplore.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onResume();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();


    }


    }



