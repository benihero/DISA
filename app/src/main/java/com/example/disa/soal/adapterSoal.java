package com.example.disa.soal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disa.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class adapterSoal extends RecyclerView.Adapter<adapterSoal.MyHolder> {


    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    ArrayList<soalEntity> soall;
    int nilai = 0;
    Context context;

    DatabaseReference reference;


    public adapterSoal(ArrayList<soalEntity> soall, Context context) {
        this.soall = soall;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new adapterSoal.MyHolder(LayoutInflater.from(context).inflate(R.layout.list_soal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {
        getUsernameLocal();
        holder.soal.setText(soall.get(position).getPertanyaan());
        holder.a.setText(soall.get(position).getOpsiA());
        holder.b.setText(soall.get(position).getOpsiB());
        holder.c.setText(soall.get(position).getOpsiC());
        holder.d.setText(soall.get(position).getOpsiD());


        holder.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                if (checkedId == R.id.opsia) {

                    String jawaban = soall.get(position).getJawaban();
                    String jawabuser = soall.get(position).getOpsiA();

                    if (jawaban.equals(jawabuser)) {

                        soall.get(position).setResult(true);
                    } else {

                        soall.get(position).setResult(false);
                    }


                } else if (checkedId == R.id.opsib) {

                    String jawaban = soall.get(position).getJawaban();
                    String jawabuser = soall.get(position).getOpsiB();

                    if (jawaban.equals(jawabuser)) {
                        soall.get(position).setResult(true);
                    } else {

                        soall.get(position).setResult(false);
                    }

                } else if (checkedId == R.id.opsic) {

                    String jawaban = soall.get(position).getJawaban();
                    String jawabuser = soall.get(position).getOpsiC();

                    if (jawaban.equals(jawabuser)) {
                        soall.get(position).setResult(true);
                    } else {

                        soall.get(position).setResult(false);
                    }


                } else if (checkedId == R.id.opsid) {

                    String jawaban = soall.get(position).getJawaban();
                    String jawabuser = soall.get(position).getOpsiD();

                    if (jawaban.equals(jawabuser)) {
                        soall.get(position).setResult(true);
                    } else {

                        soall.get(position).setResult(false);
                    }


                }

                int nilaifix = ((nilai * 100) / soall.size());

                reference = FirebaseDatabase.getInstance().getReference().child("User").child(username_key_new).child("skor").child("skorquiz");
                reference.getRef().setValue(nilaifix);
            }

        });


        //seleksi apakah dia admin atau bukan.
        if (username_key_new.equals("admin")) {


            holder.hapus.setVisibility(View.VISIBLE);
        } else {


            holder.hapus.setVisibility(View.GONE);

        }

        // proses menjawab


        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reference = FirebaseDatabase.getInstance().getReference().child("soal").child("Quiz").child(soall.get(position).getId());
                reference.removeValue();
                Intent intent = new Intent(context, quiz.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return soall.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        RadioButton a, b, c, d, v;
        TextView soal;
        Button hapus;
        Button hasil;
        RadioGroup rg;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            a = itemView.findViewById(R.id.opsia);
            b = itemView.findViewById(R.id.opsib);
            c = itemView.findViewById(R.id.opsic);
            d = itemView.findViewById(R.id.opsid);
            soal = itemView.findViewById(R.id.soalmateri);
            hapus = itemView.findViewById(R.id.hapussoal);
            rg = itemView.findViewById(R.id.rdgroub);


        }
    }


    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    public ArrayList<soalEntity> getSoall(){
        return soall;


    }
}
