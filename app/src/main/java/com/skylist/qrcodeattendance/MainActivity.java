package com.skylist.qrcodeattendance;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener( this );

        //CARREGA O PRIMEIRO FRAGMENTO
        loadFragment( new DashboardFragment() );
        toolbar.setTitle( R.string.title_dashboard );

        //DBHelper dbHelper = new DBHelper(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu, menu);
        MenuItem item = menu.findItem(R.id.id_check);
        /*SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //banco de dados aqui adapter.getFilter().filter(newText);
                return false;
            }
        });
        */


        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(getApplicationContext(), "adicionando algo na lista", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return true;
    }


    public boolean loadFragment(Fragment fragment ){
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
                toolbar.setTitle( R.string.title_dashboard );
            break;
            case R.id.navigation_student:
                fragment = new StudentFragment();
                toolbar.setTitle( R.string.title_student );
                break;
            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                toolbar.setTitle( R.string.title_profile );
                break;
        }
        return loadFragment( fragment );
    }

    //REPASSA O RESULTADO PARA O FRAGMENTO
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
