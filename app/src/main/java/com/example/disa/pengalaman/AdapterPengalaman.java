package com.example.disa.pengalaman;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disa.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class AdapterPengalaman extends RecyclerView.Adapter<AdapterPengalaman.MyHolder> {


    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference,reference2;
    AdapterPengalaman adapterPengalaman;


    Context context;
    ArrayList<PengalamanEntity> list;

    public AdapterPengalaman(Context context, ArrayList<PengalamanEntity> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.list_pengalaman,parent,false) );
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder holder,  final int position) {





        holder.hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reference = FirebaseDatabase.getInstance().getReference().child("pengalaman").child(list.get(position).getJudul());
                reference.removeValue();
                ((Pengalaman)context).finish();
                Intent intent = new Intent(context,Pengalaman.class);
                context.startActivity(intent);



            }

        });

        holder.veryf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                reference = FirebaseDatabase.getInstance().getReference().child("pengalaman").child(list.get(position).getJudul());
                reference.child("verify").setValue("1");
                Toast.makeText(context,"pengalaman telah diveryfikasi",Toast.LENGTH_SHORT).show();

            }
        });


        getUsernameLocal();

        holder.writter.setText(list.get(position).getUser());
        holder.title.setText(list.get(position).getJudul());
        holder.expe.setText(list.get(position).getIsi());








        if(username_key_new.equals("admin")){

            holder.hapus.setVisibility(View.VISIBLE);
            holder.veryf.setVisibility(View.VISIBLE);


        }else {
            holder.hapus.setVisibility(View.GONE);
            holder.veryf.setVisibility(View.GONE);

            if(list.get(position).getVerify().equals("0")){

                holder.coba.setVisibility(View.GONE);

            }else {

                holder.coba.setVisibility(View.VISIBLE);


            }




        }







        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String penulis = list.get(position).getUser();
                String judul = list.get(position).getJudul();
                String pengalaman = list.get(position).getIsi();


                Intent intent = new Intent(context,DetailPengalaman.class);
                intent.putExtra("penulis",penulis);
                intent.putExtra("judul",judul);
                intent.putExtra("pengalaman",pengalaman);
                context.startActivity(intent);

            }
        });






    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyHolder extends RecyclerView.ViewHolder {

        TextView writter,title,expe;
        LinearLayout coba;

        Button hapus,veryf;



        public MyHolder(@NonNull View itemView) {
            super(itemView);

            writter = itemView.findViewById(R.id.write);
            title = itemView.findViewById(R.id.judull);
            expe = itemView.findViewById(R.id.isii);
            coba = itemView.findViewById(R.id.cardpengalaman);
            hapus = itemView.findViewById(R.id.delete_exp);
            veryf = itemView.findViewById(R.id.verifikasi_exp);






        }






        }

    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");


    }





    }




