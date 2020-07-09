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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Registerpage extends AppCompatActivity {

    EditText Username,Email,Password;
    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";


    Button countinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);


        Username = findViewById(R.id.username);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);



        countinue = findViewById(R.id.sign_countinue);

        countinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {






                if (Username.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }
                 else if (Email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }
                 else if (Password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else {








                    //menyimpan lokal
                    SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(username_key, Username.getText().toString());
                    editor.apply();
                    ;

                    // simpan pada database
                    reference = FirebaseDatabase.getInstance().getReference().child("User").child(Username.getText().toString());
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if(dataSnapshot.exists()){


                                Toast.makeText(getApplicationContext(),"username sudah ada",Toast.LENGTH_SHORT).show();




                            } else {


                                dataSnapshot.getRef().child("username").setValue(Username.getText().toString());
                                dataSnapshot.getRef().child("email").setValue(Email.getText().toString());
                                dataSnapshot.getRef().child("password").setValue(Password.getText().toString());

                                //intent_pindah
                                Intent intent = new Intent(Registerpage.this, Registerpage2.class);
                                startActivity(intent);

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


}
