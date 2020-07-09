package com.example.disa.tambahanmateri;

public class EntityTambahan {

    String judul,isi,url_photo_materi,id;


    public EntityTambahan(){}


    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public EntityTambahan(String judul, String isi, String url_photo_materi, String id) {
        this.judul = judul;
        this.isi = isi;
        this.url_photo_materi = url_photo_materi;
        this.id = id;
    }

    public String getUrl_photo_materi() {
        return url_photo_materi;
    }

    public void setUrl_photo_materi(String url_photo_materi) {
        this.url_photo_materi = url_photo_materi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
