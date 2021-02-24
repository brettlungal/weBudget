package com.comp3350.webudget.presentation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.comp3350.webudget.R;
import com.comp3350.webudget.business.SignupLogic;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText username,fname,lname,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button signup = (Button)findViewById(R.id.signup_button);
        signup.setOnClickListener(this);
        username = (EditText)findViewById(R.id.username);
        fname = (EditText)findViewById(R.id.fName);
        lname = (EditText)findViewById(R.id.lName);
        password = (EditText)findViewById(R.id.password_input);
    }

    public String[] getInputValues(EditText fname, EditText lname, EditText username, EditText password){
        String[] values = new String[4];

        values[0] = username.getText().toString();
        values[1] = fname.getText().toString();
        values[2] = lname.getText().toString();
        values[3] = password.getText().toString();

        return values;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.signup_button:
                System.out.println("click signup");
                String[] inputValues = getInputValues(fname,lname,username,password);
                SignupLogic signup = new SignupLogic(inputValues);
                startActivity(new Intent(this,LoginActivity.class));
                finish();
        }

    }
}