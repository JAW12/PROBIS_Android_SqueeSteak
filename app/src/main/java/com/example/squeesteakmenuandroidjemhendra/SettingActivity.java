package com.example.squeesteakmenuandroidjemhendra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;

import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityMainBinding;
import com.example.squeesteakmenuandroidjemhendra.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {

    private ActivitySettingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.textPetunjuk.setText(Html.fromHtml("Klik tombol <font color='#219653'>DIATAS</font> untuk mengubah no.meja!"));
    }
}