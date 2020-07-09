package com.example.disa.penjadwalan;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.disa.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class jadwal extends AppCompatActivity {

    Button btnDatePicker, btnTimePicker, savejadwal;
    EditText txtDate, txtTime, tutor, tempat, tema;
    private int mYear, mMonth, mDay, mHour, mMinute;
    DatabaseReference reference;
    Context context;
    public static final String NOTIFICATION_CHANNEL_ID = "channel_id";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        btnDatePicker = findViewById(R.id.dateselect);
        btnTimePicker = findViewById(R.id.timeselect);
        txtDate = findViewById(R.id.tanggal);
        txtTime = findViewById(R.id.waktu);
        tema = findViewById(R.id.tema);
        tutor = findViewById(R.id.namatutor);
        tempat = findViewById(R.id.namatempat);
        savejadwal = findViewById(R.id.simpanjadwal);

        savejadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(txtDate.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"tanggal tidak boleh kosong",Toast.LENGTH_SHORT).show();


                }else if(txtTime.getText().toString().isEmpty()){


                    Toast.makeText(getApplicationContext(),"waktu tidak boleh kosong",Toast.LENGTH_SHORT).show();

                }else if(tema.getText().toString().isEmpty()){


                    Toast.makeText(getApplicationContext(),"tema tidak boleh kosong",Toast.LENGTH_SHORT).show();
                }else if(tutor.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"tutor tidak boleh kosong",Toast.LENGTH_SHORT).show();

                }else if(tempat.getText().toString().isEmpty()){

                    Toast.makeText(getApplicationContext(),"tempat tidak boleh kosong",Toast.LENGTH_SHORT).show();

                } else {

                    reference = FirebaseDatabase.getInstance().getReference().child("jadwal").push();
                    reference.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            dataSnapshot.getRef().child("id").setValue(reference.getKey());
                            dataSnapshot.getRef().child("tutor").setValue(tutor.getText().toString());
                            dataSnapshot.getRef().child("tema").setValue(tema.getText().toString());
                            dataSnapshot.getRef().child("tempat").setValue(tempat.getText().toString());
                            dataSnapshot.getRef().child("tanggal").setValue(txtDate.getText().toString());
                            dataSnapshot.getRef().child("waktu").setValue(txtTime.getText().toString());
                            notifyThis(tema.getText().toString(),txtDate.getText().toString());

                            tutor.setText("");
                            tema.setText("");
                            tempat.setText("");
                            txtDate.setText("");
                            txtTime.setText("");

                            Toast.makeText(getApplicationContext(), "jadwal berhasil ditambah", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(jadwal.this, lihat_jadwal.class);
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


    public void dateonClick(View view) {


        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void timee(View view) {

        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        txtTime.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void notifyThis(String title, String message) {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        builder.setContentTitle(title);
        builder.setContentText(message);
        builder.setSmallIcon(R.drawable.addsoal);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.addsoal));

        Notification notification = builder.build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(12, notification);
    }
}

