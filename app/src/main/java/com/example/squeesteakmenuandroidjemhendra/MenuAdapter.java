package com.example.squeesteakmenuandroidjemhendra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.squeesteakmenuandroidjemhendra.databinding.ItemProdukBinding;

import java.util.ArrayList;

public class MenuAdapter extends
        RecyclerView.Adapter<MenuAdapter.MenuViewHolder>{

    private ArrayList<Menu> listMenu;

    public MenuAdapter(ArrayList<Menu> listMenu) {
        this.listMenu = listMenu;
    }

    @NonNull
    @Override
    public MenuAdapter.MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuViewHolder(ItemProdukBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }



    @Override
    public void onBindViewHolder(@NonNull MenuAdapter.MenuViewHolder holder, int position) {
        holder.binding.textNama.setText(listMenu.get(position).getNama());
        String harga = String.format("%,d", listMenu.get(position).getHarga()).replace(',', '.');
        holder.binding.textHarga.setText("Rp. " + harga);
        holder.binding.textDeskripsi.setText(listMenu.get(position).getDeskripsi());

        Glide.with(holder.binding.imageView.getContext())
                .load(holder.binding.textDeskripsi.getContext().getString(R.string.img) + listMenu.get(position).getNama_kategori() + "/" + listMenu.get(position).getUrl())
                .into(holder.binding.imageView);

    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder{
        private ItemProdukBinding binding;
        public MenuViewHolder(ItemProdukBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(rvItemClicked != null){
                        rvItemClicked.itemClicked(itemView, getAdapterPosition());
                    }
                }
            });
        }
    }

    // untuk klik
    private onItemClickListener rvItemClicked;

    public interface onItemClickListener{
        void itemClicked(View v, int i);
    }

    public void setItemClick(onItemClickListener rvItemClicked){
        this.rvItemClicked = rvItemClicked;
    }
}
