package com.example.disa.user;

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

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.MyHolder> {

    ArrayList<EntityUser> user;
    Context context;
    DatabaseReference reference;


    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    public AdapterUser(ArrayList<EntityUser> user, Context context) {
        this.user = user;
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterUser.MyHolder(LayoutInflater.from(context).inflate(R.layout.list_user,parent,false) );
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {

        holder.username.setText(user.get(position).getUsername());
        holder.email.setText(user.get(position).getEmail());
        holder.nim.setText(user.get(position).getNIM());
        holder.kuis.setText(user.get(position).getSkorquiz());


        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                reference = FirebaseDatabase.getInstance().getReference().child("User").child(user.get(position).getUsername());
                reference.removeValue();
                ((halamanuser)context).finish();
                Intent intent = new Intent(context,halamanuser.class);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return user.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{
TextView username,email,nim,faculty,fullname,kuis;

Button hapus;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.namauser);
            email = itemView.findViewById(R.id.emailuser);
            nim = itemView.findViewById(R.id.nimuser);
            hapus = itemView.findViewById(R.id.delete_user);
            kuis = itemView.findViewById(R.id.skorquiz);

        }
    }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }
}
