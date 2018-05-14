package com.skylist.qrcodeattendance;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener( this );

        //CARREGA O PRIMEIRO FRAGMENTO
        loadFragment( new DashboardFragment() );
    }

    public boolean loadFragment( Fragment fragment ){
        //LOAD DO FRAGMENTO ESOLHIDO
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .replace( R.id.fragment_container, fragment )
                    .commit();
            return true;
        }
        return false;
    }

    //CONTROLE DA BOTTOM NAVIGATION BAR
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch ( item.getItemId() ){
            case R.id.navigation_dashboard:
                fragment = new DashboardFragment();
                break;
            case R.id.navigation_student:
                fragment = new StudentFragment();
                break;
            case R.id.navigation_profile:
                fragment = new DashboardFragment();
                break;
        }
        return loadFragment( fragment );
    }
}
