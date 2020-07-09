package com.example.disa.soal.soaldaksa;

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
import com.example.disa.soal.soaltuli.soaltulidisplay;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class AdapterDaksa extends RecyclerView.Adapter<AdapterDaksa.MyHolder> {

    Context context;
    ArrayList<EntityDaksa> soaldaksa;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    int nilai = 0;
    DatabaseReference reference;

    public AdapterDaksa(Context context, ArrayList<EntityDaksa> soaldaksa) {
        this.context = context;
        this.soaldaksa = soaldaksa;


    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterDaksa.MyHolder(LayoutInflater.from(context).inflate(R.layout.list_soal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {


        getUsernameLocal();
        holder.soal.setText(soaldaksa.get(position).getPertanyaan());
        holder.a.setText(soaldaksa.get(position).getOpsiA());
        holder.b.setText(soaldaksa.get(position).getOpsiB());
        holder.c.setText(soaldaksa.get(position).getOpsiC());
        holder.d.setText(soaldaksa.get(position).getOpsiD());


        holder.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                if (checkedId == R.id.opsia) {

                    String jawaban = soaldaksa.get(position).getJawaban();
                    String jawabuser = soaldaksa.get(position).getOpsiA();

                    if (jawaban.equals(jawabuser)) {

                        nilai = nilai + 1;
                        soaldaksa.get(position).setResult(true);


                    }else {

                        soaldaksa.get(position).setResult(false);
                    }


                } else if (checkedId == R.id.opsib) {

                    String jawaban = soaldaksa.get(position).getJawaban();
                    String jawabuser = soaldaksa.get(position).getOpsiB();

                    if (jawaban.equals(jawabuser)) {
                        nilai = nilai + 1;
                        soaldaksa.get(position).setResult(true);


                    }else {

                        soaldaksa.get(position).setResult(false);
                    }
                } else if (checkedId == R.id.opsic) {

                    String jawaban = soaldaksa.get(position).getJawaban();
                    String jawabuser = soaldaksa.get(position).getOpsiC();

                    if (jawaban.equals(jawabuser)) {
                        nilai = nilai + 1;
                        soaldaksa.get(position).setResult(true);


                    }else {

                        soaldaksa.get(position).setResult(false);
                    }
                } else if (checkedId == R.id.opsid) {

                    String jawaban = soaldaksa.get(position).getJawaban();
                    String jawabuser = soaldaksa.get(position).getOpsiD();

                    if (jawaban.equals(jawabuser)) {
                        nilai = nilai + 1;
                        soaldaksa.get(position).setResult(true);


                    }else {

                        soaldaksa.get(position).setResult(false);
                    }


                }


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


                reference = FirebaseDatabase.getInstance().getReference().child("soal").child("Daksa").child(soaldaksa.get(position).getId());
                reference.removeValue();
                ((soaldaksadisplay)context).finish();
                Intent intent = new Intent(context,soaldaksadisplay.class);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return soaldaksa.size();
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

            hasil = itemView.findViewById(R.id.selesaidaksa);

        }
    }


    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }

    public ArrayList<EntityDaksa> getSoalDaksa() {
        return soaldaksa;
    }
}
