package com.example.adminsadewa;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView tv_nama_detail, tv_deskripsi, tv_sumber, tv_alamat, tv_lat, tv_lng;
    ImageView iv_wisata_detail, iv_gambar1, iv_gambar2, iv_gambar3;
    Button btn_lihat_rute;

    private String nama, deskripsi, gambar, lat, lng, alamat, gambar1, gambar2, gambar3, sumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getDataIntent();
        initView();
    }

    private void getDataIntent() {
        nama        = getIntent().getExtras().getString("pass_nama");
        deskripsi   = getIntent().getExtras().getString("pass_deskripsi");
        gambar      = getIntent().getExtras().getString("gambar");
        gambar1     = getIntent().getExtras().getString("gambar1");
        sumber      = getIntent().getExtras().getString("sumber");
        alamat      = getIntent().getExtras().getString("alamat");
        lat         = getIntent().getExtras().getString("pass_lat");
        lng         = getIntent().getExtras().getString("pass_lng");

        Log.e("ALLDATA",lat+lng);

    }

    private void initView() {


        tv_nama_detail   = (TextView)findViewById(R.id.namdet);
        tv_deskripsi     = (TextView)findViewById(R.id.deskripsi);
        tv_alamat        = (TextView)findViewById(R.id.alamatt);
        iv_wisata_detail = (ImageView) findViewById(R.id.images);
        iv_gambar1       = (ImageView) findViewById(R.id.gambar1);
        tv_lat           = (TextView)findViewById(R.id.lat);
        tv_lng           = (TextView)findViewById(R.id.lng);
        tv_sumber        = (TextView)findViewById(R.id.sumber);

        tv_nama_detail.setText(nama);
        tv_deskripsi.setText(deskripsi);
        tv_alamat.setText(alamat);
        tv_sumber.setText("Sumber:  " + sumber);
        tv_lat.setText("Koordinat Latitude:  " + lat);
        tv_lng.setText("Koordinat Longitude:  " + lng);
        Glide.with(iv_wisata_detail.getContext()).load(gambar).into(iv_wisata_detail);
        Glide.with(iv_gambar1.getContext()).load(gambar).into(iv_gambar1);

    }

    public void onResume() {
        super.onResume();
        ActionBar supportActionBar = ((AppCompatActivity)this).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.hide();
    }

}