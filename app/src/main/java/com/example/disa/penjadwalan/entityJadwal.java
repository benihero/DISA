package com.example.disa.penjadwalan;

public class entityJadwal {

    String tutor,waktu,tanggal,tempat,tema,id;

    public entityJadwal(){}

    public entityJadwal(String tutor, String waktu, String tanggal, String tempat, String tema) {
        this.tutor = tutor;
        this.waktu = waktu;
        this.tanggal = tanggal;
        this.tempat = tempat;
        this.tema = tema;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }
}
