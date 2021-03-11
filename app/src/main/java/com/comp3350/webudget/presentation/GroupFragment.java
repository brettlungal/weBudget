package com.comp3350.webudget.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.comp3350.webudget.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class GroupFragment extends Fragment implements View.OnClickListener {

    private Button create_group_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group , container, false);

        // Buttons
        create_group_button = (Button)view.findViewById(R.id.group_create_group_button);
        create_group_button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch ( view.getId() ){
            case R.id.group_create_group_button:
                load_fragment(new CreateGroupFragment());
                break;
        }
    }

    private boolean load_groups() {
        boolean load_success = false;
        return load_success;
    }

    private boolean load_fragment(Fragment frag) {
        boolean load_success = false;
        // switch to a try catch?
        if(frag != null) {
            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragmentContainer, frag);
            transaction.addToBackStack(null);
            transaction.commit();
            load_success = true;
        }
        return load_success;
    }
}
