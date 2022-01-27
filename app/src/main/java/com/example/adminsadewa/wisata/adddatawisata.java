package com.example.adminsadewa.wisata;


import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.example.adminsadewa.R;
import com.example.adminsadewa.oleholeh.AddDataOleholeh;
import com.example.adminsadewa.oleholeh.KelolaOleholehActivity;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class adddatawisata extends AppCompatActivity implements OnMapReadyCallback
{
    int Image_Request_Code = 7;
    EditText name, alamat, deskripsi, sumber, img, imgsat, imgdu, imgti;
    TextView lat, lng;
    private ImageView imageView;
    Button submit,back, btnbrowse;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    ProgressDialog progressDialog;
    private Uri FilePathUri;
    private GoogleMap mMap;
    public Double lati, longi;
    private Location mLastLocation;
    private Marker mCurrLocationMarker;
    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_spinner_item);


        imageView=(ImageView) findViewById(R.id.add_img);
        btnbrowse = (Button)findViewById(R.id.add_image);

        name=(EditText)findViewById(R.id.add_name);
        alamat=(EditText)findViewById(R.id.add_alamat);
        deskripsi=(EditText)findViewById(R.id.add_deskripsi);
        sumber=(EditText)findViewById(R.id.add_sumber);
        lat=(TextView)findViewById(R.id.add_lat);
        lng=(TextView)findViewById(R.id.add_lng);

        progressDialog = new ProgressDialog(adddatawisata.this);


        final ScrollView scroll = (ScrollView) findViewById(R.id.sv_container);
        ImageView transparent = (ImageView)findViewById(R.id.imagetrans);

        transparent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        scroll.requestDisallowInterceptTouchEvent(true);
                        // Disable touch on transparent view
                        return false;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        scroll.requestDisallowInterceptTouchEvent(false);
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        scroll.requestDisallowInterceptTouchEvent(true);
                        return false;

                    default:
                        return true;
                }
            }
        });


        back=(Button)findViewById(R.id.add_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), KelolaWisataActivity.class));
                finish();
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("wisata");

        btnbrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), Image_Request_Code);

            }
        });

        submit=(Button)findViewById(R.id.add_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().length()==0){
                    name.setError("Nama Masih Kosing!");
                }else if(alamat.getText().toString().length()==0){
                    alamat.setError("Alamat Masih Kosing!");
                }else if(deskripsi.getText().toString().length()==0){
                    deskripsi.setError("Deskripsi Masih Kosing!");
                } else if(sumber.getText().toString().length()==0){
                    sumber.setError("Sumber Masih Kosing!");
                }
                else {
                    UploadImage();
                }
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }



    public void UploadImage() {

        if (FilePathUri != null) {

            progressDialog.setTitle("Image is Uploading...");
            progressDialog.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(FilePathUri));
            storageReference2.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                public void onSuccess(Uri uri) {

                                    Map<String,Object> map=new HashMap<>();
                                    map.put("name",name.getText().toString());
                                    map.put("alamat",alamat.getText().toString());
                                    map.put("sumber",sumber.getText().toString());
                                    map.put("img",uri.toString());
                                    map.put("deskripsi",deskripsi.getText().toString());
                                    map.put("lat", lat.getText().toString());
                                    map.put("lng", lng.getText().toString());

                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                                    @SuppressWarnings("VisibleForTests")
                                    String ImageUploadId = databaseReference.push().getKey();
                                    databaseReference.child(ImageUploadId)
                                            .setValue(map)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    name.setText("");
                                                    alamat.setText("");
                                                    deskripsi.setText("");
                                                    sumber.setText("");
                                                    Toast.makeText(getApplicationContext(),"Inserted Successfully",Toast.LENGTH_LONG).show();
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e)
                                                {
                                                    Toast.makeText(getApplicationContext(),"Could not insert",Toast.LENGTH_LONG).show();
                                                }
                                            });;
                                }
                            });
                        }
                    });
        }

        else {

            Toast.makeText(adddatawisata.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng currentDinat = new LatLng(-7.3248370853777605, 110.50474935709796);
        MarkerOptions currentMarker = new MarkerOptions();
        currentMarker.position(currentDinat);
        currentMarker.title("default location");
        currentMarker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        mMap.addMarker(currentMarker);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentDinat, 13));


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);

                String latt = String.valueOf(latLng.latitude);
                String lngg = String.valueOf(latLng.longitude);

                lat.setText(latt);
                lng.setText(lngg);

                mMap.clear();
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                mMap.addMarker(markerOptions);
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