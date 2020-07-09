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
import android.widget.Toast;

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


public class Registerpage2 extends AppCompatActivity {

    EditText Fullname, NIM, Faculty;
    Button RegisterButton, Addprofil;
    ImageView profil;
    Uri photo_location;
    Integer photo_max = 1;
    DatabaseReference reference,reference2;
    StorageReference storage;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page2);
        getUsernameLocal();

        Fullname = findViewById(R.id.fullname);
        NIM = findViewById(R.id.nim);
        Faculty = findViewById(R.id.faculty);
        RegisterButton = findViewById(R.id.register_button2);
        Addprofil = findViewById(R.id.add_profil);
        profil = findViewById(R.id.profile_pict2);

        Addprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findphoto();


            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reference = FirebaseDatabase.getInstance().getReference().
                        child("User").child(username_key_new);
                storage = FirebaseStorage.getInstance().getReference().
                        child("photouser").child(username_key_new);
                //validasi file

                if(photo_location == null){

                    Toast.makeText(getApplicationContext(),"foto todak boleh kosong",Toast.LENGTH_LONG).show();

                }else if(Fullname.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"Nama todak boleh kosong",Toast.LENGTH_LONG).show();
                }else if(NIM.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"NIM todak boleh kosong",Toast.LENGTH_LONG).show();
                }else if(Faculty.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"Fakultas todak boleh kosong",Toast.LENGTH_LONG).show();
                }else {


                    RegisterButton.setText("Loading..");

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



                                    reference.getRef().child("fullname").setValue(Fullname.getText().toString());
                                    reference.getRef().child("NIM").setValue(NIM.getText().toString());
                                    reference.getRef().child("Faculty").setValue(Faculty.getText().toString());



                                }
                            }).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            RegisterButton.setText("Register");

                            Intent intent = new Intent(Registerpage2.this, succes_login.class);
                            startActivity(intent);
                            Registerpage2.this.finish();


                        }
                    });
                }


                 reference2 = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresonklik").child("tuli");
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("materisatu").setValue(0);
                        dataSnapshot.getRef().child("materidua").setValue(0);
                        dataSnapshot.getRef().child("materitiga").setValue(0);
                        dataSnapshot.getRef().child("materiempat").setValue(0);
                        dataSnapshot.getRef().child("materilima").setValue(0);
                        dataSnapshot.getRef().child("materienam").setValue(0);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                reference2 = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresonklik").child("daksa");
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("materisatu").setValue(0);
                        dataSnapshot.getRef().child("materidua").setValue(0);
                        dataSnapshot.getRef().child("materitiga").setValue(0);
                        dataSnapshot.getRef().child("materiempat").setValue(0);
                        dataSnapshot.getRef().child("materilima").setValue(0);
                        dataSnapshot.getRef().child("materienam").setValue(0);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                reference2 = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresonklik").child("autis");
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("materisatu").setValue(0);
                        dataSnapshot.getRef().child("materidua").setValue(0);
                        dataSnapshot.getRef().child("materitiga").setValue(0);
                        dataSnapshot.getRef().child("materiempat").setValue(0);
                        dataSnapshot.getRef().child("materilima").setValue(0);
                        dataSnapshot.getRef().child("materienam").setValue(0);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                reference2 = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresonklik").child("tunet");
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("materisatu").setValue(0);
                        dataSnapshot.getRef().child("materidua").setValue(0);
                        dataSnapshot.getRef().child("materitiga").setValue(0);
                        dataSnapshot.getRef().child("materiempat").setValue(0);
                        dataSnapshot.getRef().child("materilima").setValue(0);
                        dataSnapshot.getRef().child("materienam").setValue(0);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                reference2 = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("progresmateri");
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("Tuli").setValue(0);
                        dataSnapshot.getRef().child("Autis").setValue(0);
                        dataSnapshot.getRef().child("TunaDaksa").setValue(0);
                        dataSnapshot.getRef().child("TunaNetra").setValue(0);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                reference2 = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("skor");
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        dataSnapshot.getRef().child("skorautis").setValue(0);
                        dataSnapshot.getRef().child("skordaksa").setValue(0);
                        dataSnapshot.getRef().child("skornetra").setValue(0);
                        dataSnapshot.getRef().child("skortuli").setValue(0);



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


                reference2 = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new);
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                     dataSnapshot.getRef().child("skorquiz").setValue("0");



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



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
            Picasso.with(this).load(photo_location).centerCrop().fit().into(profil);

        }

    }

    public  void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY,MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");


    }


}
