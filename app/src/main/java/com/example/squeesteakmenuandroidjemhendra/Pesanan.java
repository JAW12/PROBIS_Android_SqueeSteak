package com.example.squeesteakmenuandroidjemhendra;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Pesanan implements Parcelable {
    private String nama;
    private int jumlah;
    private int subtotal;

    public Pesanan(String nama, int jumlah, int subtotal) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.subtotal = subtotal;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public static Creator<Pesanan> getCREATOR() {
        return CREATOR;
    }

    protected Pesanan(Parcel in) {
        nama = in.readString();
        jumlah = in.readInt();
        subtotal = in.readInt();
    }

    public static final Creator<Pesanan> CREATOR = new Creator<Pesanan>() {
        @Override
        public Pesanan createFromParcel(Parcel in) {
            return new Pesanan(in);
        }

        @Override
        public Pesanan[] newArray(int size) {
            return new Pesanan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama);
        parcel.writeInt(jumlah);
        parcel.writeInt(subtotal);
    }
}
