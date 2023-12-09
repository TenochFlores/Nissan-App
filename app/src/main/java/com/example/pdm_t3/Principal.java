package com.example.pdm_t3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Principal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Bundle usuario = new Bundle();

    private HomeFragment homeFragment = new HomeFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Toolbar toolbar = findViewById(R.id.toolbar); //Ignore red line errors
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        usuario = getIntent().getBundleExtra("values");

        // Asignar el Bundle al fragmento
        homeFragment.setArguments(usuario);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desplegable, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_info) {
            Log.i("ActionBar", "Info del menu de desbordamiento ha sido seleccionado");
            Toast.makeText(this, "Info", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_help) {
            Log.i("ActionBar", "Contact del menu de desbordamiento ha sido seleccionado");
            Toast.makeText(this, "Contact", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if (id == R.id.action_terms) {
            Log.i("ActionBar", "Terms & conditions del menu de desbordamiento ha sido seleccionado");
            Toast.makeText(this, "Terms & conditions", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_home){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, homeFragment).commit();
            Log.i("NavigationDrawer", "Opcion Home seleccionada");
        }
        else if(item.getItemId() == R.id.nav_cars){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CarsFragment()).commit();
            Log.i("NavigationDrawer", "Opcion Cars seleccionada");
        }
        else if(item.getItemId() == R.id.nav_buy){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BuyFragment()).commit();
            Log.i("NavigationDrawer", "Opcion Buy seleccionada");
        }
        else if(item.getItemId() == R.id.nav_about){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
            Log.i("NavigationDrawer", "Opcion Abour seleccionada");
        }
        else if(item.getItemId() == R.id.nav_logout){
            Intent intentToLogin = new Intent(this, MainActivity.class);
            startActivity(intentToLogin);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}