package com.example.adminsadewa.oleholeh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adminsadewa.R;


public class OleholehActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sadewa);

        Button kelola = (Button) findViewById(R.id.keldat);
        kelola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(OleholehActivity.this, KelolaOleholehActivity.class);
                startActivity(picture_intent );
            }
        });

        Button lihat = (Button) findViewById(R.id.lihdat);
        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(OleholehActivity.this, LihatOleholehActivity.class);
                startActivity(picture_intent );

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