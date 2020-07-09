package com.example.disa.pengalaman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class add_pengalaman extends AppCompatActivity {

    Button saveexp,back;
    EditText Title, Experi;


    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pengalaman);

        getUsernameLocal();

        saveexp = findViewById(R.id.save_experience);
        Title = findViewById(R.id.Titleexp);
        Experi = findViewById(R.id.experience);
        back = findViewById(R.id.back_tambahpengalaman);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(add_pengalaman.this,Pengalaman.class);
                startActivity(intent);
                finish();
            }
        });


        saveexp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Title.getText().toString().isEmpty()) {


                    Toast.makeText(getApplicationContext(), "judul tidak boleh kosong", Toast.LENGTH_SHORT).show();

                } else if (Experi.getText().toString().isEmpty()) {


                    Toast.makeText(getApplicationContext(), "isi tidak boleh kosong", Toast.LENGTH_SHORT).show();

                } else {


                    reference = FirebaseDatabase.getInstance().getReference().child("pengalaman").child(Title.getText().toString());


                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            dataSnapshot.getRef().child("judul").setValue(Title.getText().toString());
                            dataSnapshot.getRef().child("isi").setValue(Experi.getText().toString());
                            dataSnapshot.getRef().child("user").setValue(username_key_new);
                            dataSnapshot.getRef().child("verify").setValue("0");
                            finish();
                            Intent intent = new Intent(add_pengalaman.this, Pengalaman.class);
                            startActivity(intent);
                            finish();
                            Toast.makeText(getApplicationContext(), "pengalaman berhasil ditambah", Toast.LENGTH_SHORT).show();


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }

            }

        });


    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    @Override
    public void onBackPressed() {

    }
}
