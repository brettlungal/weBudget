package com.comp3350.webudget.presentation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.comp3350.webudget.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MasterActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master);

        BottomNavigationView nav = (BottomNavigationView)findViewById(R.id.navigation);
        nav.setOnNavigationItemSelectedListener(this);
        loadFragment( new AccountFragment() );
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

        return loadFragment(frag);
    }

    private boolean loadFragment(Fragment f){
        if ( f != null ){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer , f ).commit();
            return true;
        }
        return false;
    }

}