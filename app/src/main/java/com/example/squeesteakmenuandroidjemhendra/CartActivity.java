package com.example.squeesteakmenuandroidjemhendra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityCartBinding;
import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityDetailBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    private ActivityCartBinding binding;
    Intent intent;
    private ArrayList<Pesanan> arrPesanan = new ArrayList<>();
    private ArrayList<Pesanan> arrAccepted = new ArrayList<>();
    CartAdapter cartAdapter;
    CartAdapter cartAccepted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setTitle(Html.fromHtml("<font color='#000000'>Keranjang</font>"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.rvHTambahan.setHasFixedSize(true);
        binding.rvHTambahan.setLayoutManager(new LinearLayoutManager(this));
        cartAdapter = new CartAdapter(arrPesanan);
        binding.rvHTambahan.setAdapter(cartAdapter);
        binding.rvHSudah.setHasFixedSize(true);
        binding.rvHSudah.setLayoutManager(new LinearLayoutManager(this));
        cartAccepted = new CartAdapter(arrAccepted);
        binding.rvHSudah.setAdapter(cartAccepted);
        showList("0");
        binding.btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrPesanan.clear();
                cartAdapter.notifyDataSetChanged();
                String harga = String.format("%,d", 0).replace(',', '.');
                binding.textSubtotal.setText(harga);
                binding.textTotal2.setText(harga);
                updateStatus();
            }
        });
        binding.btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), ConfirmationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showList(final String status){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url) +"showList",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");
                            arrPesanan.clear();
                            if(code == 1){
                                JSONArray jsonArray = jsonObject.getJSONArray("message");
                                for(int i = 0; i < jsonArray.length(); i++){
                                    JSONObject cartObj = jsonArray.getJSONObject(i);
                                    Pesanan pesanan = new Pesanan(
                                            cartObj.getString("nama"),
                                            cartObj.getInt("jumlah"),
                                            cartObj.getInt("subtotal")
                                    );
                                    arrPesanan.add(pesanan);
                                }
                                int total = 0;
                                for(int i = 0; i < arrPesanan.size(); i++){
                                    total = total + arrPesanan.get(i).getSubtotal();
                                }
                                String harga = String.format("%,d", total).replace(',', '.');
                                binding.textSubtotal.setText(harga);
                            }
                            cartAdapter.notifyDataSetChanged();
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
                params.put("function", "showList");
                params.put("kode", MainActivity.kode);
                params.put("status", status);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void updateStatus(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url) +"updateStatus",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");
                            if(code == 1){
                                JSONArray jsonArray = jsonObject.getJSONArray("message");
                                for(int i = 0; i < jsonArray.length(); i++){
                                    JSONObject cartObj = jsonArray.getJSONObject(i);
                                    Pesanan pesanan = new Pesanan(
                                            cartObj.getString("nama"),
                                            cartObj.getInt("jumlah"),
                                            cartObj.getInt("subtotal")
                                    );
                                    arrAccepted.add(pesanan);
                                }
                                int total = 0;
                                for(int i = 0; i < arrAccepted.size(); i++){
                                    total = total + arrAccepted.get(i).getSubtotal();
                                }
                                String harga = String.format("%,d", total).replace(',', '.');
                                binding.textTotal2.setText(harga);
                            }
                            cartAccepted.notifyDataSetChanged();
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
                params.put("function", "updateStatus");
                params.put("kode", MainActivity.kode);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}