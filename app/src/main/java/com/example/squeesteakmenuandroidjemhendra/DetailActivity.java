package com.example.squeesteakmenuandroidjemhendra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;

import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityDetailBinding;
import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityMainBinding;

public class DetailActivity extends AppCompatActivity {

    private ActivityDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textPetunjuk.setText(Html.fromHtml("Klik tombol <font color='#219653'>DIATAS</font> untuk menambahkan menu!"));
    }
}