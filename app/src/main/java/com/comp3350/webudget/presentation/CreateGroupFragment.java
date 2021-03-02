package com.comp3350.webudget.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.comp3350.webudget.R;

public class CreateGroupFragment extends Fragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homeView = inflater.inflate(R.layout.fragment_create_group , container, false);


        return homeView;
    }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ){
            //listen for clicks

        }

    }
}
