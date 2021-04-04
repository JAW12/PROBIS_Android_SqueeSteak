package com.example.squeesteakmenuandroidjemhendra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityConfirmationBinding;
import com.example.squeesteakmenuandroidjemhendra.databinding.ActivityMainBinding;

public class ConfirmationActivity extends AppCompatActivity {

    private ActivityConfirmationBinding binding;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityConfirmationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnIya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getApplicationContext(), CompletedActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnTidak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}