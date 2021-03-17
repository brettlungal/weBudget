package com.comp3350.webudget.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.comp3350.webudget.R;

public class CreateGroupFragment extends Fragment implements View.OnClickListener {

    private EditText group_name_field, description_field;
    private Button create_group_button, add_members_button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_group , container, false);

        // Fields
        group_name_field = (EditText)view.findViewById(R.id.group_name);
        description_field = (EditText)view.findViewById(R.id.username);

        // Buttons
        create_group_button = (Button)view.findViewById(R.id.create_group_create_group_button);
        create_group_button.setOnClickListener(this);
        add_members_button = (Button)view.findViewById(R.id.create_group_add_members_button);
        add_members_button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch ( v.getId() ){
            case R.id.create_group_create_group_button:
                String[] inputValues = getInputValues();
                try {
                    // try to create group
                } catch(Exception e) {
                    // handle failure
                }
                break;
            case R.id.create_group_add_members_button:
                break;
        }
    }

    private String[] getInputValues(){
        String[] values = new String[2];
        values[0] = group_name_field.getText().toString();
        values[1] = description_field.getText().toString();
        return values;
    }
}
