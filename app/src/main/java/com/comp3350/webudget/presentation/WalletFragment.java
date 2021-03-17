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
    private EditText username,transfer_amount,deposit_amount;
    private double bal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View walletView = inflater.inflate(R.layout.fragment_wallet , container, false);
        walletView.findViewById(R.id.send_button).setOnClickListener(this);
        walletView.findViewById(R.id.deposit_button).setOnClickListener(this);
        String current_user = Services.userLogic().getCurrentUser();
        bal = 0;
        try {
            bal = Services.userWalletLogic().getAmount(current_user);
        } catch (AccountException e) {
            e.printStackTrace();
        } catch (WalletException e) {
            e.printStackTrace();
        }
        //get the required ui elements
        username = (EditText)walletView.findViewById(R.id.recipient_input);
        transfer_amount = (EditText)walletView.findViewById(R.id.amount_input);
        deposit_amount = (EditText)walletView.findViewById(R.id.depost_amt_input);
        owner = (TextView) walletView.findViewById(R.id.owner);
        balance = (TextView) walletView.findViewById(R.id.balance);

        //set the values dynamically
        owner.setText(current_user);
        balance.setText(String.valueOf(bal));


        return walletView;
    }

    public String[] getTransferInputValues(){
        String[] values = new String[2];

        values[0] = username.getText().toString();
        values[1] = transfer_amount.getText().toString();

        return values;
    }

    public String getDepositInputValue(){
        return deposit_amount.getText().toString();
    }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ){
            //listen for click
            case R.id.send_button:
                String[] vals = getTransferInputValues();
                int amt = Integer.parseInt(vals[1]);
                transfer_amount.getText().clear();
                username.getText().clear();
                break;

            case R.id.deposit_button:
                String deposit_val = getDepositInputValue();
                int deposit_amt = Integer.parseInt(deposit_val);
                double newAmt = bal+=deposit_amt;
                balance.setText(String.valueOf(newAmt));
                deposit_amount.getText().clear();
                break;
        }

    }
}
