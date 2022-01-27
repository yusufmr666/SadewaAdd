package com.example.adminsadewa.Adaper;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.adminsadewa.R;
import com.example.adminsadewa.model.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class AdapterCagarBudaya extends FirebaseRecyclerAdapter<User, AdapterCagarBudaya.MyViewHolder> {


    public AdapterCagarBudaya (@NonNull FirebaseRecyclerOptions<User> options) {
        super(options);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listd, viewGroup, false);
        return new MyViewHolder(v);

    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull User User) {

        holder.firstName.setText(User.getName());
        holder.lastName.setText(User.getAlamat());
        Glide.with(holder.img.getContext()).load(User.getImg()).into(holder.img);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true, 1400)
                        .create();

                View myview = dialogPlus.getHolderView();
                final EditText name = myview.findViewById(R.id.namades);
                final EditText alamat = myview.findViewById(R.id.alamatdes);
                final EditText deskripsi = myview.findViewById(R.id.deskripsides);
                final EditText sumber = myview.findViewById(R.id.sumdes);

                Button submit = myview.findViewById(R.id.usubmit);

                name.setText(User.getName());
                alamat.setText(User.getAlamat());
                deskripsi.setText(User.getDeskripsi());
                sumber.setText(User.getSumber());


                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("name", name.getText().toString());
                        map.put("alamat", alamat.getText().toString());
                        map.put("deskripsi", deskripsi.getText().toString());
                        map.put("sumber", sumber.getText().toString());


                        FirebaseDatabase.getInstance().getReference().child("cagar_budaya")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });


            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Hapus Data?");

                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseDatabase.getInstance().getReference().child("cagar_budaya")
                                .child(getRef(position).getKey()).removeValue();
                    }
                });

                builder.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView firstName, lastName, getTvJarak;
        ImageView img;
        Button edit, delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.id);
            lastName = itemView.findViewById(R.id.idd);
            img = itemView.findViewById(R.id.image);

            edit = (Button) itemView.findViewById(R.id.editicon);
            delete = (Button) itemView.findViewById(R.id.deleteicon);
        }
    }
}


