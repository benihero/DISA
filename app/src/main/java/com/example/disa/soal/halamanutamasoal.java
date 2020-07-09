package com.example.disa.soal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.Dashboard;
import com.example.disa.R;
import com.example.disa.soal.soalautis.soalautisdisplay;
import com.example.disa.soal.soaldaksa.soaldaksadisplay;
import com.example.disa.soal.soalnetra.soalnetradisplay;
import com.example.disa.soal.soaltuli.soaltulidisplay;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class halamanutamasoal extends AppCompatActivity {

    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    LinearLayout ltuli,ldaksa,lnetra,lautis;
    TextView ptuli,pnetra,pautis,pdaksa;
    TextView skornetra,skortuli,skorautis,skordaksa;

    Button tambahsoal,back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamanutamasoal);

        getUsernameLocal();


        tambahsoal = findViewById(R.id.tambahsoall);
        back = findViewById(R.id.back_halamansoal);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(halamanutamasoal.this, Dashboard.class);
                startActivity(intent);
                finish();


            }
        });


        if(username_key_new.equals("admin")){

            tambahsoal.setVisibility(View.VISIBLE);
        }else {

            tambahsoal.setVisibility(View.GONE);
        }


        skorautis = findViewById(R.id.scoreautis);
        skordaksa = findViewById(R.id.scoredaksa);
        skornetra = findViewById(R.id.scorenetra);
        skortuli = findViewById(R.id.scoretuli);


        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("skor");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                skorautis.setText(dataSnapshot.child("skorautis").getValue().toString());
                skordaksa.setText(dataSnapshot.child("skordaksa").getValue().toString());
                skornetra.setText(dataSnapshot.child("skornetra").getValue().toString());
                skortuli.setText(dataSnapshot.child("skortuli").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        ltuli = findViewById(R.id.liniertuli);
        ldaksa = findViewById(R.id.linierdaksa);
        lnetra = findViewById(R.id.liniernetra);
        lautis = findViewById(R.id.linierautis);

        ptuli = findViewById(R.id.peringatantuli);
        pnetra = findViewById(R.id.peringatannetra);
        pautis = findViewById(R.id.peringatanautis);
        pdaksa = findViewById(R.id.peringatandaksa);





        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresmateri");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String daksa = dataSnapshot.child("TunaDaksa").getValue().toString();
                int daksanilai = Integer.valueOf(daksa);

                if(daksanilai != 6){

                    ldaksa.setClickable(false);

                }else {


                    ldaksa.setClickable(true);
                    pdaksa.setText("quiz telah terbuka");
                }


                String tuli = dataSnapshot.child("Tuli").getValue().toString();
                int tulinilai = Integer.valueOf(tuli);


                if(tulinilai != 6){

                    ltuli.setClickable(false);

                }else {


                    ltuli.setClickable(true);
                    ptuli.setText("quiz telah terbuka");
                }


                String netra = dataSnapshot.child("TunaNetra").getValue().toString();
                int netranilai = Integer.valueOf(netra);

                if(netranilai != 6){

                    lnetra.setClickable(false);

                }else {


                    lnetra.setClickable(true);
                    pnetra.setText("quiz telah terbuka");
                }

                String autis = dataSnapshot.child("Autis").getValue().toString();
                int autisnilai = Integer.valueOf(autis);

                if(autisnilai != 6){

                    lautis.setClickable(false);

                }else {


                    lautis.setClickable(true);
                    pautis.setText("quiz telah terbuka");
                }







            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }


    public void pindahsoalautis(View view){

        Intent intent = new Intent(halamanutamasoal.this, soalautisdisplay.class);
        startActivity(intent);
        finish();
    }

    public void pindahaddsoal(View view){

        Intent intent = new Intent(halamanutamasoal.this, add_soal.class);
        startActivity(intent);
        finish();
    }
    public void pindahsoalquiz(View view){

        Intent intent = new Intent(halamanutamasoal.this, quiz.class);
        startActivity(intent);
        finish();
    }
    public void pindahsoaldaksa(View view){

        Intent intent = new Intent(halamanutamasoal.this, soaldaksadisplay.class);
        startActivity(intent);
        finish();
    }

    public void pindahsoaltuli(View view){

        Intent intent = new Intent(halamanutamasoal.this, soaltulidisplay.class);
        startActivity(intent);
        finish();
    }

    public void pindahsoalnetra(View view){

        Intent intent = new Intent(halamanutamasoal.this, soalnetradisplay.class);
        startActivity(intent);
        finish();

    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    @Override
    public void onBackPressed() {


    }
}
