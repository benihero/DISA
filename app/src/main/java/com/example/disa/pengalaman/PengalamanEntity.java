package com.example.disa.pengalaman;

public class PengalamanEntity {

    String isi;
    String judul;
    String user;
    String verify;

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public PengalamanEntity(String isi, String judul, String user, String verify) {
        this.isi = isi;
        this.judul = judul;
        this.user = user;
        this.verify = verify;
    }



    public PengalamanEntity(){}


    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
