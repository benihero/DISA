package com.example.disa.tambahanmateri;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disa.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class AdapterTambahan extends RecyclerView.Adapter<AdapterTambahan.MyHolder> {

    ArrayList<EntityTambahan> tambahanmateri;
    Context context;
    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";


    public AdapterTambahan(ArrayList<EntityTambahan> tambahanmateri, Context context) {
        this.tambahanmateri = tambahanmateri;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterTambahan.MyHolder(LayoutInflater.from(context).inflate(R.layout.list_materitambahan,parent,false) );
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder, final int position) {

        holder.judul.setText(tambahanmateri.get(position).getJudul());
        holder.isi.setText(tambahanmateri.get(position).getIsi());
        Picasso.with(context).load(tambahanmateri.get(position).getUrl_photo_materi()).centerCrop().fit().into(holder.photo);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String judul = tambahanmateri.get(position).getJudul();
                String isi = tambahanmateri.get(position).getIsi();
                String urll = tambahanmateri.get(position).getUrl_photo_materi();


                Intent intent = new Intent(context,DetailMateri.class);
                intent.putExtra("judul",judul);
                intent.putExtra("isi",isi);
                intent.putExtra("uurl",urll);

                context.startActivity(intent);

            }
        });


        getUsernameLocal();

        if(username_key_new.equals("admin")){

            holder.hapus.setVisibility(View.VISIBLE);

        }else {

            holder.hapus.setVisibility(View.GONE);

        }


        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference().child("materi").child(tambahanmateri.get(position).getId());
                reference.removeValue();
                Intent intent = new Intent(context,listmateritambahan.class);
                context.startActivity(intent);



            }
        });


    }

    @Override
    public int getItemCount() {
        return tambahanmateri.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView judul,isi,urlll;
        ImageView photo;
        Button hapus;


        public MyHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judulmateritambah);
            isi = itemView.findViewById(R.id.isimateritambah);
            photo = itemView.findViewById(R.id.potolistmateritambahan);
            hapus = itemView.findViewById(R.id.hapustambahan);



        }
    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }



}
