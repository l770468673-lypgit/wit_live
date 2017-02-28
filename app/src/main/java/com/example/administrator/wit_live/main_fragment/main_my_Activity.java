package com.example.administrator.wit_live.main_fragment;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.wit_live.R;

public class main_my_Activity extends Fragment {
    public main_my_Activity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view= inflater.inflate(R.layout.activity_main_my_,container,false);
        return view;
    }
}
