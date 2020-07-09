package com.example.disa.materi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.Dashboard;
import com.example.disa.R;
import com.example.disa.materi.autisme.materiatsatu;
import com.example.disa.materi.tuli.materitlsatu;
import com.example.disa.materi.tunadaksa.materitnsatu;
import com.example.disa.materi.tunanetra.materisatu;
import com.example.disa.tambahanmateri.listmateritambahan;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListMateri extends AppCompatActivity {

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference;

    ProgressBar prog,prog2,prog3,prog4;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_materi);
        getUsernameLocal();


        prog = findViewById(R.id.progresdaksa);
        prog2 = findViewById(R.id.progrestuli);
        prog3 = findViewById(R.id.progresautis);
        prog4 = findViewById(R.id.progresnetra);

        if(username_key_new.equals("")){



        }else {


            reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresmateri");
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    String daksa = dataSnapshot.child("TunaDaksa").getValue().toString();
                    int daksaa = Integer.parseInt(daksa);
                    prog.setProgress(daksaa);

                    String tuli = dataSnapshot.child("Tuli").getValue().toString();
                    int tulii = Integer.parseInt(tuli);
                    prog2.setProgress(tulii);


                    String autis = dataSnapshot.child("Autis").getValue().toString();
                    int autiss = Integer.parseInt(autis);
                    prog3.setProgress(autiss);

                    String netra = dataSnapshot.child("TunaNetra").getValue().toString();
                    int netraa = Integer.parseInt(netra);
                    prog4.setProgress(netraa);


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });





        }





    }


    public void MateriTUnet(View view){

        Intent intent = new Intent(ListMateri.this, materisatu.class);
        startActivity(intent);
        finish();


    }
    public void MateriDaksa(View view){

        Intent intent = new Intent(ListMateri.this, materitnsatu.class);
        startActivity(intent);
        finish();


    }
    public void MateriTuli(View view){

        Intent intent = new Intent(ListMateri.this, materitlsatu.class);
        startActivity(intent);
        finish();

    }

    public void MateriAutis(View view){

        Intent intent = new Intent(ListMateri.this, materiatsatu.class);
        startActivity(intent);
        finish();


    }


    public void MateriTambah(View view){

        Intent intent = new Intent(ListMateri.this, listmateritambahan.class);
        startActivity(intent);
        finish();


    }

    public void Onbek(View view){

        Intent intent = new Intent(ListMateri.this, Dashboard.class);
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
