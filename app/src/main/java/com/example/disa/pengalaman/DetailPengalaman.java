package com.example.disa.pengalaman;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disa.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DetailPengalaman extends AppCompatActivity {

    TextView nama, judul, isi;
    Button komen;

    String j;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference, reference2;

    RecyclerView recyclerComment;
    ArrayList<commentEntity> koment;
    adapterComment komenadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengalaman);
        nama = findViewById(R.id.namapenulis);
        judul = findViewById(R.id.judultulisan);
        isi = findViewById(R.id.isitulisan);
        komen = findViewById(R.id.popupkomen);
        Intent intent = getIntent();
        j = intent.getStringExtra("judul");
        String p = intent.getStringExtra("penulis");
        String i = intent.getStringExtra("pengalaman");
        nama.setText(p);
        judul.setText(j);
        isi.setText(i);
        recyclerComment = findViewById(R.id.reykomen);


        recyclerComment.setLayoutManager(new LinearLayoutManager(this));
        koment = new ArrayList<commentEntity>();

        reference2 = FirebaseDatabase.getInstance().getReference().child("pengalaman").child(j).child("komentar");
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    commentEntity p = dataSnapshot1.getValue(commentEntity.class);
                    koment.add(p);


                }
                komenadapter = new adapterComment(DetailPengalaman.this, koment);
                recyclerComment.setAdapter(komenadapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        komen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getUsernameLocal();
                popUpEditText();
            }
        });

    }


    private void popUpEditText() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Komentar");

        final EditText input = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        input.setLayoutParams(lp);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                reference = FirebaseDatabase.getInstance().getReference().child("pengalaman").child(j).child("komentar").push();
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataSnapshot.getRef().child("penulis").setValue(username_key_new);
                        dataSnapshot.getRef().child("komentar").setValue(input.getText().toString());
                        finish();
                        startActivity(getIntent());


                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
