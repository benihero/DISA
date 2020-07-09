package com.example.disa.pengalaman;

public class commentEntity {

    String penulis;
    String komentar;

    public commentEntity(String penulis, String komentar) {
        this.penulis = penulis;
        this.komentar = komentar;
    }

    public commentEntity() {
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }
}
