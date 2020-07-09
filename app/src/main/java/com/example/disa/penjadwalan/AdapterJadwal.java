package com.example.disa.penjadwalan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disa.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class AdapterJadwal extends RecyclerView.Adapter<AdapterJadwal.Myholder>{

    ArrayList<entityJadwal> jadwal;
    Context context;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference;

    public AdapterJadwal(ArrayList<entityJadwal> jadwal, Context context) {
        this.jadwal = jadwal;
        this.context = context;
    }

    @NonNull
    @Override
    public Myholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterJadwal.Myholder(LayoutInflater.from(context).inflate(R.layout.list_lihat_jadwal, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Myholder holder, final int position) {

        holder.tema.setText(jadwal.get(position).getTema());
        holder.tutor.setText(jadwal.get(position).getTutor());
        holder.waktu.setText(jadwal.get(position).getWaktu());
        holder.tempat.setText(jadwal.get(position).getTempat());
        holder.tanggal.setText(jadwal.get(position).getTanggal());
        getUsernameLocal();

        if (username_key_new.equals("admin")){

            holder.hapus.setVisibility(View.VISIBLE);

        }else {

            holder.hapus.setVisibility(View.GONE);

        }

        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference().child("jadwal").child(jadwal.get(position).getId());
                reference.removeValue();
                Intent intent = new Intent(context, lihat_jadwal.class);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return jadwal.size();
    }


    class Myholder extends RecyclerView.ViewHolder{

        TextView tutor,waktu,tempat,tema,tanggal;
        Button hapus;

        public Myholder(@NonNull View itemView) {
            super(itemView);

            tutor = itemView.findViewById(R.id.namatutor);
            waktu = itemView.findViewById(R.id.waktupelatihan);
            tempat =itemView.findViewById(R.id.tempatpelatihan);
            tema = itemView.findViewById(R.id.temapelatihan);
            tanggal = itemView.findViewById(R.id.tanggalpelatihan);
            hapus = itemView.findViewById(R.id.hapusjadwal);

        }
    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }
}
