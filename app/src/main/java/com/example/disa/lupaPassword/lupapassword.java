package com.example.disa.lupaPassword;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.R;
import com.example.disa.loginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class lupapassword extends AppCompatActivity {

    EditText usermame;
    Button send;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupapassword);

        usermame = findViewById(R.id.username_lupapassword);
        send = findViewById(R.id.kirim_email);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference().child("User").child(usermame.getText().toString());
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.exists()){


                            final String password = dataSnapshot.child("password").getValue().toString();
                            final String email = dataSnapshot.child("email").getValue().toString();



                            Thread sender = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        Gmailsender sender = new Gmailsender("adamhawaa142@gmail.com", "tamado123");
                                        sender.sendMail("Password Anda",
                                                "password anda adalah : "+password,
                                                "adamhawaa142@gmail.com",
                                                email);

                                    } catch (Exception e) {
                                        Log.e("mylog", "Error: " + e.getMessage());
                                    }
                                }
                            });
                            sender.start();
                            Toast.makeText(getApplicationContext(),"Password telah dikirim pada email anda",Toast.LENGTH_SHORT).show();
                            usermame.setText("");


                        } else {

                            Toast.makeText(getApplicationContext(),"username tidak ada",Toast.LENGTH_SHORT).show();

                        }





                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





            }
        });





    }

    public void lupapasswordd(View view){

        Intent intent = new Intent(lupapassword.this,loginActivity.class);
        startActivity(intent);
        finish();


    }
}
