package com.example.adminsadewa.kuliner;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.adminsadewa.Adaper.MyAdapter;
import com.example.adminsadewa.R;
import com.example.adminsadewa.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LihatKulinerActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference database;
    MyAdapter myadapter;

    ArrayList<User> list;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_sadewa);


        recyclerView = findViewById(R.id.listcagi);
        database = FirebaseDatabase.getInstance().getReference("kuliner");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();



        database.addValueEventListener(new ValueEventListener() {
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    User user = dataSnapshot.getValue(User.class);


                    list.add(user);

                }
                myadapter = new MyAdapter(list);
                recyclerView.setAdapter(myadapter);
                myadapter.notifyDataSetChanged();

            }




            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onResume() {
        super.onResume();
        ActionBar supportActionBar = ((AppCompatActivity)this).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.hide();
    }
}