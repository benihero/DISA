package com.example.disa;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class edit_profile extends AppCompatActivity {

    EditText Fullname,Nim,Email,Faculty;
    ImageView Profile_edit;
    Button searchphoto,savee,back;
    Uri photo_location;
    Integer photo_max = 1;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference,reference2;
    StorageReference storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        searchphoto = findViewById(R.id.add_profil_edit);
        Fullname = findViewById(R.id.fullname_profile_edit);
        Nim = findViewById(R.id.nim_profile_edit);
        Email = findViewById(R.id.email_profile_edit);
        Faculty = findViewById(R.id.faculty_profile_edit);
        Profile_edit = findViewById(R.id.profile_pict_edit);
        savee = findViewById(R.id.save_edit);
        back = findViewById(R.id.back_editprofil);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(edit_profile.this,profile.class);
                startActivity(intent);
                finish();
            }
        });

        searchphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findphoto();

            }
        });

        savee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        reference.getRef().child("fullname").setValue(Fullname.getText().toString());
                        reference.getRef().child("NIM").setValue(Nim.getText().toString());
                        reference.getRef().child("Faculty").setValue(Faculty.getText().toString());
                        reference.getRef().child("email").setValue(Email.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                if(photo_location != null  ) {

                    final StorageReference storageReference1 =
                            storage.child(System.currentTimeMillis() + "." +
                                    getFileExtension(photo_location));

                    storageReference1.putFile(photo_location)
                            .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                                    storageReference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            String uri_photo = uri.toString();
                                            reference.getRef().child("url_photo_profile").setValue(uri_photo);
                                        }
                                    });


                                }
                            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {


                            Intent intent = new Intent(edit_profile.this, profile.class);
                            startActivity(intent);
                            finish();


                        }
                    });
                }else {
                    Intent intent = new Intent(edit_profile.this, profile.class);
                    startActivity(intent);
                    finish();

                }

            }


        });

        getUsernameLocal();

        reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new);

        storage = FirebaseStorage.getInstance().getReference().
                child("photouser").child(username_key_new);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Fullname.setText(dataSnapshot.child("fullname").getValue().toString());
                Nim.setText(dataSnapshot.child("NIM").getValue().toString());
                Faculty.setText(dataSnapshot.child("Faculty").getValue().toString());
                Email.setText(dataSnapshot.child("email").getValue().toString());
                Picasso.with(edit_profile.this).load(dataSnapshot.child("url_photo_profile").
                        getValue().toString()).centerCrop().fit().into(Profile_edit);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



    String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));


    }

    public void findphoto() {

        Intent pic = new Intent();
        pic.setType("image/*");
        pic.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(pic, photo_max);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == photo_max && resultCode == RESULT_OK && data != null && data.getData() != null) {

            photo_location = data.getData();
            Picasso.with(this).load(photo_location).centerCrop().fit().into(Profile_edit);

        }

    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    @Override
    public void onBackPressed() {

    }
}
