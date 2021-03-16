package com.comp3350.webudget.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.comp3350.webudget.application.AccountException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.R;
import com.comp3350.webudget.application.WalletException;
import com.comp3350.webudget.business.IUserWalletLogic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class WalletFragment extends Fragment implements View.OnClickListener {


    private TextView balance,owner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View walletView = inflater.inflate(R.layout.fragment_wallet , container, false);
        walletView.findViewById(R.id.send_button).setOnClickListener(this);
        String current_user = Services.userLogic().getCurrentUser();
        double bal = 0;
        try {
            bal = Services.userWalletLogic().getAmount(current_user);
        } catch (AccountException e) {
            e.printStackTrace();
        } catch (WalletException e) {
            e.printStackTrace();
        }
        owner = (TextView) walletView.findViewById(R.id.owner);
        balance = (TextView) walletView.findViewById(R.id.balance);
        owner.setText(current_user);
        balance.setText(String.valueOf(bal));


        return walletView;
    }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ){
            //listen for click


        }

    }
}
