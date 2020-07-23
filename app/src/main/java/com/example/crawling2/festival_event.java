package com.example.crawling2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class festival_event extends Fragment {

    public festival_event(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){


        return inflater.inflate(R.layout.festival_event,container,false);

    }

}
