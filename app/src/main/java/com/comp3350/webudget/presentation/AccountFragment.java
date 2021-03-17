package com.comp3350.webudget.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.comp3350.webudget.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class AccountFragment extends Fragment implements View.OnClickListener {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View accountView = inflater.inflate(R.layout.fragment_account , container, false);
        accountView.findViewById(R.id.wallet_icon).setOnClickListener(this);

        return accountView;
    }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ){
            //listen for clicks
            case R.id.wallet_icon:
                WalletFragment frag = new WalletFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainer, frag);
                transaction.addToBackStack(null);
                transaction.commit();
                System.out.println("PRESSED WALLET");
        }

    }
}
