package com.example.squeesteakmenuandroidjemhendra;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.squeesteakmenuandroidjemhendra.databinding.FragmentListBinding;

public class ListFragment extends Fragment {

    FragmentListBinding binding;
    int kategori;

    public ListFragment() {
        // Required empty public constructor
    }

    public static ListFragment newInstance(int kategori) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt("kategori", kategori);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            kategori = getArguments().getInt("kategori");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}