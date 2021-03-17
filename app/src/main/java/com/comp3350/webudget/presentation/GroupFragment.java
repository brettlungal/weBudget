package com.comp3350.webudget.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.comp3350.webudget.R;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.objects.Group;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;

public class GroupFragment extends Fragment implements View.OnClickListener {

    private Button create_group_button;
    private ArrayList<String> users_groups_names;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group , container, false);

        // Buttons
        create_group_button = (Button)view.findViewById(R.id.group_create_group_button);
        create_group_button.setOnClickListener(this);

        // List View
        users_groups_names = new ArrayList<String>();
        users_groups_names.add("nothing");
        users_groups_names.add("appears");
        users_groups_names.add("to");
        users_groups_names.add("be");
        users_groups_names.add("here");
        try {
            // ArrayList<Group> users_groups = Services.groupLogic().getUserGroups(Services.userLogic().getCurrentUser());
            // turn users_groups into an array list of strings to display to
        } catch (Exception e) {

        }
        ListView group_list = (ListView) view.findViewById(R.id.group_list);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, users_groups_names);
        group_list.setAdapter((listViewAdapter));

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
