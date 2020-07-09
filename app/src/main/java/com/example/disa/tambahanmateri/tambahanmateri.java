package com.example.disa.tambahanmateri;

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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disa.R;
import com.example.disa.materi.ListMateri;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


public class tambahanmateri extends AppCompatActivity {

    EditText judul,tambahan;
    Button save, Addprofil;
    ImageView photomateri;
    Uri photo_location;
    Integer photo_max = 1;
    DatabaseReference reference;
    StorageReference storage;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambahanmateri);
        getUsernameLocal();

        judul = findViewById(R.id.judulmateri);
        tambahan = findViewById(R.id.isimateri);
        save = findViewById(R.id.simpanmateri);
        Addprofil = findViewById(R.id.addphoto_materi);
        photomateri = findViewById(R.id.photomateri);

        Addprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findphoto();


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reference = FirebaseDatabase.getInstance().getReference().
                        child("materi").push();
                storage = FirebaseStorage.getInstance().getReference().
                        child("photomateri");
                //validasi file

                if(photo_location == null){

                    Toast.makeText(getApplicationContext(),"foto tidak boleh kosong",Toast.LENGTH_LONG).show();

                }else if(judul.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"judul tidak boleh kosong",Toast.LENGTH_LONG).show();
                }else if(tambahan.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"Materi tidak boleh kosong",Toast.LENGTH_LONG).show();

                }else {


                    save.setText("Loading..");

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
                                            reference.getRef().child("url_photo_materi").setValue(uri_photo);
                                        }
                                    });



                                    reference.getRef().child("judul").setValue(judul.getText().toString());
                                    reference.getRef().child("id").setValue(reference.getKey());
                                    reference.getRef().child("isi").setValue(tambahan.getText().toString());



                                }
                            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            save.setText("save");

                            Intent intent = new Intent(tambahanmateri.this, ListMateri.class);
                            startActivity(intent);
                            finish();


                        }
                    });
                }





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
            Picasso.with(this).load(photo_location).centerCrop().fit().into(photomateri);

        }

    }

    public  void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");


    }


}
