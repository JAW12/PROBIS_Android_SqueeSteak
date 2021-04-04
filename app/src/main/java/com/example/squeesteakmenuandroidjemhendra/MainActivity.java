package com.example.squeesteakmenuandroidjemhendra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setBottomNavigationView();
        if (savedInstanceState == null){
            binding.navigation.setSelectedItemId(R.id.menu_utama);
        }
        setTitle("05 | INV2021032000001");
    }

    public void setTitle(String s){
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>" + s + "</font>"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.option_keranjang:
                intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
                break;
            case R.id.option_pengaturan:
                intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

    private void setBottomNavigationView(){
        binding.navigation.setItemIconTintList(null);
        binding.navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.menu_utama:
                        fragment = ListFragment.newInstance(1);
                        getSupportFragmentManager().beginTransaction()
                                .replace(binding.container.getId(), fragment)
                                .commit();
                        return true;
                    case R.id.menu_minuman:
                        fragment = ListFragment.newInstance(2);
                        getSupportFragmentManager().beginTransaction()
                                .replace(binding.container.getId(), fragment)
                                .commit();
                        return true;
                    case R.id.menu_pendamping:
                        fragment = ListFragment.newInstance(3);
                        getSupportFragmentManager().beginTransaction()
                                .replace(binding.container.getId(), fragment)
                                .commit();
                        return true;
                }
                return false;
            }
        });
    }
}