package com.bagunit.webudget;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private String email_input;
    private String pwrd_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText email_field = (EditText)findViewById(R.id.email_input);
        EditText pwrd_field = (EditText)findViewById(R.id.password_input);
        Button login_button = (Button)findViewById(R.id.login_button);



    }

    private void hideKeyboard(){
        View v = getCurrentFocus();
        if ( v != null ){
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
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
                String[] vals = getInputValues(); //TODO need to either pass the edit text vars or make them global

        }

    }
}