package com.example.disa.soal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class add_soal extends AppCompatActivity {

    EditText soal, opsia, opsib, opsic, opsid, jawaban;
    Button save,back;
    DatabaseReference reference;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_soal);
        spinner = findViewById(R.id.spinkategori);

        soal = findViewById(R.id.soall);
        opsia = findViewById(R.id.jawabanA);
        opsib = findViewById(R.id.jawabanB);
        opsic = findViewById(R.id.jawabanC);
        opsid = findViewById(R.id.jawabanD);
        jawaban = findViewById(R.id.jawaban);
        back = findViewById(R.id.soal_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(add_soal.this,halamanutamasoal.class);
                startActivity(intent);
                finish();

            }
        });

        save = findViewById(R.id.save_soal);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (soal.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "quiz tidak boleh kosong", Toast.LENGTH_SHORT).show();

                } else if (opsia.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Opsi A tidak boleh kosong", Toast.LENGTH_SHORT).show();

                } else if (opsib.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Opsi B tidak boleh kosong", Toast.LENGTH_SHORT).show();

                } else if (opsic.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Opsi C tidak boleh kosong", Toast.LENGTH_SHORT).show();

                } else if (opsid.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Opsi D tidak boleh kosong", Toast.LENGTH_SHORT).show();

                } else if (jawaban.getText().toString().isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Jawaban tidak boleh kosong", Toast.LENGTH_SHORT).show();

                } else {


                    reference = FirebaseDatabase.getInstance().getReference().child("soal").child(spinner.getSelectedItem().toString()).push();
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            dataSnapshot.getRef().child("pertanyaan").setValue(soal.getText().toString());
                            dataSnapshot.getRef().child("opsiA").setValue(opsia.getText().toString());
                            dataSnapshot.getRef().child("opsiB").setValue(opsib.getText().toString());
                            dataSnapshot.getRef().child("opsiC").setValue(opsic.getText().toString());
                            dataSnapshot.getRef().child("opsiD").setValue(opsid.getText().toString());
                            dataSnapshot.getRef().child("id").setValue(reference.getKey());
                            dataSnapshot.getRef().child("jawaban").setValue(jawaban.getText().toString());

                            finish();

                            soal.setText("");
                            opsia.setText("");
                            opsib.setText("");
                            opsic.setText("");
                            opsid.setText("");
                            jawaban.setText("");

                            Toast.makeText(getApplicationContext(), "soal berhasil ditambah", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(add_soal.this, halamanutamasoal.class);
                            startActivity(intent);
                            finish();


                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {


                        }
                    });


                }


            }
        });


    }

    @Override
    public void onBackPressed() {

    }
}
