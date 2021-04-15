package com.example.squeesteakmenuandroidjemhendra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;

import com.bumptech.glide.Glide;
import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityDetailBinding;
import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityMainBinding;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;
    Menu menu;

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

            Glide.with(getApplicationContext())
                    .load(getString(R.string.img) + menu.getNama_kategori() + "/" + menu.getUrl())
                    .into(binding.imgProduk);
        }
        binding.textPetunjuk.setText(Html.fromHtml("Klik tombol <font color='#219653'>DIATAS</font> untuk menambahkan menu!"));
    }
}