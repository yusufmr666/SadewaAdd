package com.example.adminsadewa.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.adminsadewa.R;
import com.example.adminsadewa.cagarbudaya.CagarBudayaActivity;
import com.example.adminsadewa.kuliner.KulinerActivity;
import com.example.adminsadewa.oleholeh.OleholehActivity;
import com.example.adminsadewa.penginapan.KelolaPenginapanActivity;
import com.example.adminsadewa.penginapan.PenginapanActivity;
import com.example.adminsadewa.wisata.WisataActivity;

public class HomeFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayout menu_photos = (LinearLayout)root.findViewById(R.id.layy);
        menu_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(getActivity().getApplication(), WisataActivity.class);
                startActivity(picture_intent );
            }
        });

        LinearLayout menu_kuliner = (LinearLayout)root.findViewById(R.id.kuliner);
        menu_kuliner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(getActivity().getApplication(), KulinerActivity.class);
                startActivity(picture_intent );
            }
        });

        LinearLayout menu_oleh = (LinearLayout)root.findViewById(R.id.oleh);
        menu_oleh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(getActivity().getApplication(), OleholehActivity.class);
                startActivity(picture_intent );
            }
        });

        LinearLayout menu_penginapan = (LinearLayout)root.findViewById(R.id.peng);
        menu_penginapan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(getActivity().getApplication(), PenginapanActivity.class);
                startActivity(picture_intent );
            }
        });

        LinearLayout menu_cagar = (LinearLayout)root.findViewById(R.id.cag);
        menu_cagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent picture_intent = new Intent(getActivity().getApplication(), CagarBudayaActivity.class);
                startActivity(picture_intent );
            }
        });

        return root;
    }

    public void onResume() {
        super.onResume();
        ActionBar supportActionBar = ((AppCompatActivity) requireActivity()).getSupportActionBar();
        if (supportActionBar != null)
            supportActionBar.hide();
    }

}

