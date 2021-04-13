package com.comp3350.webudget.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.comp3350.webudget.Exceptions.GroupException;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.R;
import com.comp3350.webudget.Exceptions.WalletException;
import com.comp3350.webudget.objects.Group;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class GroupWalletFragment extends Fragment implements View.OnClickListener {


    private TextView balance,owner;
    private EditText deposit_amount;
    private Group current_group;

    public GroupWalletFragment(Group group) {
        current_group = group;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View walletView = inflater.inflate(R.layout.fragment_group_wallet , container, false);
        walletView.findViewById(R.id.gw_deposit_button).setOnClickListener(this);

        //get the required ui elements
        deposit_amount = (EditText)walletView.findViewById(R.id.gw_depost_amt_input);
        owner = (TextView) walletView.findViewById(R.id.gw_owner);
        balance = (TextView) walletView.findViewById(R.id.gw_balance);

        //set the values dynamically
        owner.setText(current_group.getName());
        updateBalance();

        return walletView;
    }

    public String getDepositInputValue(){
        return deposit_amount.getText().toString();
    }

    private void updateBalance(){
        try {
            balance.setText(String.valueOf(Services.groupWalletLogic().getAmount(Integer.toString(current_group.getId()))));
        }  catch (WalletException w) {
            Toast deposit_toast = Toast.makeText(getActivity().getApplicationContext(), w.getMessage(), Toast.LENGTH_SHORT);
            deposit_toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        switch ( v.getId() ) {

            case R.id.gw_deposit_button:
                String deposit_val = getDepositInputValue();
                try {
                    Services.groupWalletLogic().deposit(Integer.toString(current_group.getId()), deposit_val);
                    Toast success_toast = Toast.makeText(getActivity().getApplicationContext(), "Deposit Successful!", Toast.LENGTH_SHORT);
                    success_toast.show();
                }catch(WalletException | GroupException e ){
                    Toast deposit_toast = Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT);
                    deposit_toast.show();
                }
                updateBalance();

                deposit_amount.getText().clear();
                break;
        }

    }
}