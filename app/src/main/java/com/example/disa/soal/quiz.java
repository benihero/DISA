package com.example.disa.soal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import java.util.Locale;

public class quiz extends AppCompatActivity {

    private EditText mEditTextInput;
    private TextView mTextViewCountDown;
    private Button mButtonSet;
    private Button mButtonStartPause;
    private Button mButtonReset;

    private CountDownTimer mCountDownTimer;

    private boolean mTimerRunning;

    private long mStartTimeInMillis;
    private long mTimeLeftInMillis;
    private long mEndTime;


    RecyclerView reysoal;
    DatabaseReference reference,reference2;
    ArrayList<soalEntity> soalll;
    adapterSoal soaladapter;

    Button selesaiquiz,back;


    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal);

        getUsernameLocal();
        reysoal = findViewById(R.id.reysoal);
        back = findViewById(R.id.back_quiz);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(quiz.this,halamanutamasoal.class);
                startActivity(intent);
                finish();

            }
        });




        mEditTextInput = findViewById(R.id.edit_text_input);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);

        mButtonSet = findViewById(R.id.button_set);
        mButtonStartPause = findViewById(R.id.button_start_pause);



        if (username_key_new.equals("admin")){

            reysoal.setVisibility(View.VISIBLE);


        }else {
            mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);

            reysoal.setVisibility(View.INVISIBLE);

        }

        selesaiquiz = findViewById(R.id.selesai_quiz);
        selesaiquiz.setVisibility(View.GONE);

        selesaiquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int score = 0;

                ArrayList<soalEntity> obj = soaladapter.getSoall();

                for ( soalEntity item : obj){

                    if(item.isResult() !=false){

                        score+=1;

                    }


                }

                int nilaifix = ((score *100)/obj.size());
                getUsernameLocal();

                String nilaikuis = Integer.toString(nilaifix);

                reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("skorquiz");
                reference.getRef().setValue(nilaikuis);

                Intent intent = new Intent(quiz.this, skorDisplay.class);
                intent.putExtra("quiz","quiz");
                startActivity(intent);
                finish();
                pauseTimer();
            }
        });



        reysoal.setLayoutManager(new LinearLayoutManager(this));
        soalll = new ArrayList<soalEntity>();


        reference = FirebaseDatabase.getInstance().getReference().child("soal").child("Quiz");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    soalEntity p = dataSnapshot1.getValue(soalEntity.class);
                    soalll.add(p);


                }
                soaladapter = new adapterSoal(soalll, quiz.this);
                reysoal.setAdapter(soaladapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        reference2 = FirebaseDatabase.getInstance().getReference().child("timer").child("timersoal");
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String input = dataSnapshot.getValue().toString();

                if (input.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                long millisInput = Long.parseLong(input) * 60000;
                if (millisInput == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }

                setTime(millisInput);
                mEditTextInput.setText("");



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reference = FirebaseDatabase.getInstance().getReference().child("timer").child("timersoal");
                reference.setValue(mEditTextInput.getText().toString());

                reference2 = FirebaseDatabase.getInstance().getReference().child("timer").child("timersoal");
                reference2.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String input = dataSnapshot.getValue().toString();

                        if (input.length() == 0) {
                            Toast.makeText(getApplicationContext(), "Field can't be empty", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        long millisInput = Long.parseLong(input) * 60000;
                        if (millisInput == 0) {
                            Toast.makeText(getApplicationContext(), "Please enter a positive number", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        setTime(millisInput);
                        mEditTextInput.setText("");



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
getUsernameLocal();
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                selesaiquiz.setVisibility(View.VISIBLE);
                if (mTimerRunning) {

                    if(username_key_new.equals("admin")){

                        pauseTimer();
                        reysoal.setVisibility(View.INVISIBLE);
                    }


                } else {
                    startTimer();
                    reysoal.setVisibility(View.VISIBLE);
                }
            }
        });


    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }


    private void setTime(long milliseconds) {


            mStartTimeInMillis = milliseconds;
            resetTimer();
            closeKeyboard();




    }

    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;

        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                Intent intent = new Intent(quiz.this, skorDisplay.class);
                intent.putExtra("quiz","quiz");
                startActivity(intent);
                finish();

            }
        }.start();

        mTimerRunning = true;
        updateWatchInterface();
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        updateWatchInterface();
    }

    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }

    private void updateCountDownText() {


                int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
                int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
                int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

                String timeLeftFormatted;
                if (hours > 0) {
                    timeLeftFormatted = String.format(Locale.getDefault(),
                            "%d:%02d:%02d", hours, minutes, seconds);
                } else {
                    timeLeftFormatted = String.format(Locale.getDefault(),
                            "%02d:%02d", minutes, seconds);
                }

                mTextViewCountDown.setText(timeLeftFormatted);












    }

    private void updateWatchInterface() {
        if (mTimerRunning) {
            mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);

            if(username_key_new.equals("admin")){

                mButtonStartPause.setText("Pause");

            }


        } else {

            if(username_key_new.equals("admin")){

                mEditTextInput.setVisibility(View.VISIBLE);
                mButtonSet.setVisibility(View.VISIBLE);
                mButtonStartPause.setText("Start");

            }



            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);
            } else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }

            if (mTimeLeftInMillis < mStartTimeInMillis) {

            } else {

            }
        }
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//
//        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//
//        editor.putLong("startTimeInMillis", mStartTimeInMillis);
//        editor.putLong("millisLeft", mTimeLeftInMillis);
//        editor.putBoolean("timerRunning", mTimerRunning);
//        editor.putLong("endTime", mEndTime);
//
//        editor.apply();
//
//        if (mCountDownTimer != null) {
//            mCountDownTimer.cancel();
//        }
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
//
//        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
//        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
//        mTimerRunning = prefs.getBoolean("timerRunning", false);
//
//        updateCountDownText();
//        updateWatchInterface();
//
//        if (mTimerRunning) {
//            mEndTime = prefs.getLong("endTime", 0);
//            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
//
//            if (mTimeLeftInMillis < 0) {
//                mTimeLeftInMillis = 0;
//                mTimerRunning = false;
//                updateCountDownText();
//                updateWatchInterface();
//            } else {
//                startTimer();
//            }
//        }
//    }


    @Override
    public void onBackPressed() {

    }
}
