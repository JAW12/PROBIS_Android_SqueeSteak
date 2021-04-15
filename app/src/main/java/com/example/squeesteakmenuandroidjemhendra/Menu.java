package com.example.squeesteakmenuandroidjemhendra;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
    private int id;
    private String nama;
    private String nama_kategori;
    private int harga;
    private String url;
    private String deskripsi;

    public Menu(int id, String nama, String nama_kategori, int harga, String url, String deskripsi) {
        this.id = id;
        this.nama = nama;
        this.nama_kategori = nama_kategori;
        this.harga = harga;
        this.url = url;
        this.deskripsi = deskripsi;
    }

    protected Menu(Parcel in) {
        id = in.readInt();
        nama = in.readString();
        nama_kategori = in.readString();
        harga = in.readInt();
        url = in.readString();
        deskripsi = in.readString();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getNama_kategori() {
        return nama_kategori;
    }

    public void setNama_kategori(String nama_kategori) {
        this.nama_kategori = nama_kategori;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nama);
        parcel.writeString(nama_kategori);
        parcel.writeInt(harga);
        parcel.writeString(url);
        parcel.writeString(deskripsi);
    }
}
