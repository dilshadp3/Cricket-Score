package com.example.cricketscore.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cricketscore.R;

public class CommanteryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.commantery_fragment, viewGroup, false);
        TextView output= (TextView)view.findViewById(R.id.msg1);
        output.setText("No Commantery Found..!");
        return view;
    }
}
