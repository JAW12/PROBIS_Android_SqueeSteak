package com.example.squeesteakmenuandroidjemhendra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityDetailBinding;
import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    Menu menu; // ini untuk retrieve data menu di detail

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(getIntent().hasExtra("menu")){
            menu = getIntent().getParcelableExtra("menu");

            binding.textNama.setText(menu.getNama());
            String harga = String.format("%,d", menu.getHarga()).replace(',', '.');
            binding.textHarga.setText("Rp. " + harga);
            binding.textDeskripsi.setText(menu.getDeskripsi());
            binding.btnPesan.setText("Rp. " + "0");
            Glide.with(getApplicationContext())
                    .load(getString(R.string.img) + menu.getNama_kategori() + "/" + menu.getUrl())
                    .into(binding.imgProduk);
        }
        binding.textPetunjuk.setText(Html.fromHtml("Ubah jumlah <font color='#219653'>DIATAS</font> untuk menambahkan menu!"));
        binding.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = Integer.parseInt(binding.textSubtotal.getText().toString());
                total = total + 1;
                binding.textSubtotal.setText(String.valueOf(total));
                String harga = String.format("%,d", menu.getHarga()*total).replace(',', '.');
                binding.btnPesan.setText("Rp. " + harga);
                binding.textPetunjuk.setText(Html.fromHtml("Klik tombol <font color='#219653'>DIATAS</font> untuk menambahkan menu!"));
            }
        });
        binding.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = Integer.parseInt(binding.textSubtotal.getText().toString());
                total = total - 1;
                if(total <= 0){
                    String harga = String.format("%,d", 0).replace(',', '.');
                    binding.btnPesan.setText("Rp. " + harga);
                    binding.textPetunjuk.setText(Html.fromHtml("Ubah jumlah <font color='#219653'>DIATAS</font> untuk menambahkan menu!"));
                    binding.textSubtotal.setText("0");
                }
                else{
                    binding.textSubtotal.setText(String.valueOf(total));
                    String harga = String.format("%,d", menu.getHarga()*total).replace(',', '.');
                    binding.btnPesan.setText("Rp. " + harga);
                    binding.textPetunjuk.setText(Html.fromHtml("Klik tombol <font color='#219653'>DIATAS</font> untuk menambahkan menu!"));
                }
            }
        });
        binding.btnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }

    public void addItem(){
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                getResources().getString(R.string.url) + "addItem",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int code = jsonObject.getInt("code");
                            String message = jsonObject.getString("message");
                            if(code == 1){
                                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
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
                params.put("id", String.valueOf(menu.getId()));
                params.put("kode", String.valueOf(getTitle()));
                params.put("harga", String.valueOf(menu.getHarga()));
                params.put("jumlah", binding.textSubtotal.getText().toString());
                params.put("keterangan", binding.editKeterangan.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}