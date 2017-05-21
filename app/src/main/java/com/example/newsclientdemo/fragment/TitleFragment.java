package com.example.newsclientdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.newsclientdemo.R;

/**
 * Created by 雪无痕 on 2017/5/14.
 */

public class TitleFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_title,container,false);

        return view;
    }
}
