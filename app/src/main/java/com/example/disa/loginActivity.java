package com.example.disa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.lupaPassword.lupapassword;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginActivity extends AppCompatActivity {

    Button loginn;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    DatabaseReference reference;
    EditText Username, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Username = findViewById(R.id.username_login);
        Password = findViewById(R.id.password_login);


        loginn = findViewById(R.id.login_primary);
        loginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String userr = Username.getText().toString();
                final String passwordd = Password.getText().toString();


                if (userr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();


                } else if (passwordd.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password tidakboleh kosong", Toast.LENGTH_SHORT).show();
                } else {


                    reference = FirebaseDatabase.getInstance().getReference().
                            child("User").child(userr);

                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                //ambil
                                String passwordfirebase = dataSnapshot.child("password").getValue().toString();
                                //validasi
                                if (passwordd.equals(passwordfirebase)) {
                                    loginn.setEnabled(false);
                                    loginn.setText("loading...");

                                    SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString(username_key, Username.getText().toString());
                                    editor.apply();

                                    Intent intent = new Intent(loginActivity.this, Dashboard.class);
                                    startActivity(intent);
                                    finish();


                                } else {

                                    Toast.makeText(getApplicationContext(), "Password salah", Toast.LENGTH_SHORT).show();

                                }


                            } else {
                                Toast.makeText(getApplicationContext(), "Username tidak ada", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }
            }
        });
    }

    public void lupapasswordd(View view){

        Intent intent = new Intent(loginActivity.this, lupapassword.class);
        startActivity(intent);
        finish();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(loginActivity.this,Startexplore.class);
        startActivity(intent);
        finish();

    }
}
