package com.example.disa.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disa.R;
import com.example.disa.profile;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class halamanuser extends AppCompatActivity {

    RecyclerView reyuser;
    Button back;

    DatabaseReference reference;
    ArrayList<EntityUser> user;
    AdapterUser useradap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halamanuser);

        reyuser = findViewById(R.id.reyuser);
        back = findViewById(R.id.back_user);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(halamanuser.this, profile.class);
                startActivity(intent);
                finish();

            }
        });


        reyuser.setLayoutManager(new LinearLayoutManager(this));
        user = new ArrayList<EntityUser>();


        reference = FirebaseDatabase.getInstance().getReference().child("User");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    EntityUser p = dataSnapshot1.getValue(EntityUser.class);
                    user.add(p);


                }
                useradap= new AdapterUser( user,halamanuser.this);
                reyuser.setAdapter(useradap);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {


    }


}
