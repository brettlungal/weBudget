package com.bagunit.webudget;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class AccountFragment extends Fragment implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View accountView = inflater.inflate(R.layout.fragment_account , container, false);


        return accountView;
    }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ){
            //listen for clicks

        }

    }
}
