package com.comp3350.webudget.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.comp3350.webudget.R;
import com.comp3350.webudget.objects.Transaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MasterActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment current_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        BottomNavigationView nav = (BottomNavigationView)findViewById(R.id.navigation);
        nav.setOnNavigationItemSelectedListener(this);
        load_fragment(new HomeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment frag = null;

        switch( menuItem.getItemId() ){
            case R.id.navigation_settings:
                frag = new SettingsFragment();
                break;

            case R.id.navigation_account:
                frag = new AccountFragment();
                break;

            case R.id.navigation_home:
                frag = new HomeFragment();
                break;

            case R.id.navigation_calendar:
                frag = new CalendarFragment();
                break;
        }
        return load_fragment(frag);
    }

    private boolean load_fragment(Fragment frag){
        if ( frag != null ) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer , frag ).addToBackStack(null).commit();
            current_fragment = frag;
            return true;
        }
        return false;
    }

}