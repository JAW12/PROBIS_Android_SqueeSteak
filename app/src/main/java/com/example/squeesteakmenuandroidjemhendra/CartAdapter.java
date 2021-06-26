package com.example.squeesteakmenuandroidjemhendra;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.squeesteakmenuandroidjemhendra.databinding.ItemHeaderBinding;


import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends
        RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private ArrayList<Pesanan> listPesanan;

    public CartAdapter(ArrayList<Pesanan> listPesanan) {
        this.listPesanan = listPesanan;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartAdapter.CartViewHolder(ItemHeaderBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.CartViewHolder holder, int position) {
        holder.binding.textNama.setText(listPesanan.get(position).getNama());
        int harga = listPesanan.get(position).getSubtotal() / listPesanan.get(position).getJumlah();
        String hargaIndividu = String.format("%,d", harga).replace(',', '.');
        holder.binding.textTJumlah.setText("x" + String.valueOf(listPesanan.get(position).getJumlah()));
        holder.binding.textSubtotal.setText("Rp. " + hargaIndividu);
    }

    @Override
    public int getItemCount() {
        return listPesanan.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        private ItemHeaderBinding binding;
        public CartViewHolder(ItemHeaderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
