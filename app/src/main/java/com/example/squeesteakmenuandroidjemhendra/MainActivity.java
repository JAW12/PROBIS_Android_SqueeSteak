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
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    Intent intent;
    public static String kode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setBottomNavigationView();
        if (savedInstanceState == null){
            binding.navigation.setSelectedItemId(R.id.menu_utama);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        makeHeader();
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
                intent.putExtra("activity", "main");
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

    public void makeHeader(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url) + "makeHeader",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");
                            String message = jsonObject.getString("message");
                            if(code == 1){
                                kode = message;
                                setTitle(String.format("%02d", Integer.valueOf(Preferences.getMeja(getApplicationContext()))) + " | " + message);
                            }
                            else{
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("meja", Preferences.getMeja(getApplicationContext()));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}