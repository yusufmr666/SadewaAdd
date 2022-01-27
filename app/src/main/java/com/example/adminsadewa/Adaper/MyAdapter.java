package com.example.adminsadewa.Adaper;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminsadewa.DetailActivity;
import com.example.adminsadewa.R;
import com.example.adminsadewa.model.User;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {



    private List<User> list = new ArrayList<User>();


    public MyAdapter(ArrayList<User> list)
    {
        this.list = list;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listdes, viewGroup, false);

        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        User user = list.get(position);

        holder.firstName.setText(user.getName());
        holder.lastName.setText(user.getAlamat());
        Glide.with(holder.img.getContext()).load(user.getImg()).into(holder.img);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView firstName, lastName, getTvJarak;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.id);
            lastName = itemView.findViewById(R.id.idd);
            img = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(v.getContext(), DetailActivity.class);
                    User modelWisata = list.get(getAdapterPosition());
                    intent.putExtra("pass_nama",modelWisata.getName());
                    intent.putExtra("alamat",modelWisata.getAlamat());
                    intent.putExtra("gambar",modelWisata.getImg());
                    intent.putExtra("gambar1",modelWisata.getImg1());
                    intent.putExtra("gambar2",modelWisata.getImg2());
                    intent.putExtra("gambar3",modelWisata.getImg3());
                    intent.putExtra("pass_deskripsi",modelWisata.getDeskripsi());
                    intent.putExtra("pass_lat",modelWisata.getLat());
                    intent.putExtra("pass_lng",modelWisata.getLng());
                    intent.putExtra("sumber", modelWisata.getSumber());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }






}