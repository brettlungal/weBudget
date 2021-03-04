package com.comp3350.webudget.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.comp3350.webudget.R;
import com.comp3350.webudget.objects.Transaction;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button group_button;
    private Fragment current_fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View homeView = inflater.inflate(R.layout.fragment_home , container, false);

        // Buttons
        group_button = (Button)homeView.findViewById(R.id.home_group_button);
        group_button.setOnClickListener(this);

        return homeView;
    }

    @Override
    public void onClick(View view) {
        switch ( view.getId() ){
            case R.id.home_group_button:
                load_fragment(new GroupFragment());
        }
    }

    private boolean load_fragment(Fragment frag) {
        boolean load_success = false;
        // switch to a try catch?
        if(frag != null) {
            FragmentTransaction transaction = getFragmentManager().beginTransaction().replace(R.id.home_fragment_container, frag);
            transaction.addToBackStack(null);
            transaction.commit();
            current_fragment = frag;
            load_success = true;
        }
        return load_success;
    }

}
