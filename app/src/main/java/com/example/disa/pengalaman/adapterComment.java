package com.example.disa.pengalaman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.disa.R;

import java.util.ArrayList;

public class adapterComment extends RecyclerView.Adapter<adapterComment.MyHolder> {


    Context context;
    ArrayList<commentEntity> komen;

    public adapterComment(Context context, ArrayList<commentEntity> komen) {
        this.context = context;
        this.komen = komen;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new adapterComment.MyHolder(LayoutInflater.from(context).inflate(R.layout.list_komentar,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        holder.koment.setText(komen.get(position).getKomentar());
        holder.penulis.setText(komen.get(position).getPenulis());

    }

    @Override
    public int getItemCount() {
        return komen.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView penulis,koment;


        public MyHolder(@NonNull View itemView) {
            super(itemView);


            penulis = itemView.findViewById(R.id.penuliskomentar);
            koment = itemView.findViewById(R.id.isikomentar);
        }
    }
}
