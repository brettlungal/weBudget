package com.comp3350.webudget.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.comp3350.webudget.R;
import com.comp3350.webudget.application.Services;
import com.comp3350.webudget.business.LoginLogic;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText email_field,pwrd_field;
    private Button login_button,signup_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email_field = (EditText)findViewById(R.id.username);
        pwrd_field = (EditText)findViewById(R.id.password_input);
        login_button = (Button)findViewById(R.id.login_button);
        signup_button = (Button)findViewById(R.id.signup_button);

        login_button.setOnClickListener(this);
        signup_button.setOnClickListener(this);

    }

    private void hideKeyboard(){
        View v = getCurrentFocus();
        if ( v != null ){
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    public String[] getInputValues(EditText email,EditText password){
        String[] values = new String[2];

        values[0] = email.getText().toString();
        values[1] = password.getText().toString();

        return values;
    }

    @Override
    public void onClick(View view) {

        switch ( view.getId() ){
            case R.id.login_button:
                //login has been clicked
                hideKeyboard();
                String[] inputValues = getInputValues(this.email_field,this.pwrd_field);
                LoginLogic login = new LoginLogic(inputValues);
                if(login.isUserValid()){
                    System.out.println("XXXXXXXXXXXXXXXX");
                    startActivity(new Intent(this , MasterActivity.class));
                }
            break;
            case R.id.signup_button:
                hideKeyboard();
                startActivity(new Intent(this , SignupActivity.class));
            break;
        }

    }
}