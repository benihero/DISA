package com.example.disa.soal.soalautis;

public class EntityAutis {

    String pertanyaan;

    String jawaban;

    int skor;
    String opsiA,opsiB,opsiC,opsiD,id;
    boolean result = false;

    public EntityAutis(){}

    public EntityAutis(String pertanyaan, String jawaban, int skor, String opsiA, String opsiB, String opsiC, String opsiD, String id) {

        this.pertanyaan = pertanyaan;
        this.jawaban = jawaban;
        this.skor = skor;
        this.opsiA = opsiA;
        this.opsiB = opsiB;
        this.opsiC = opsiC;
        this.opsiD = opsiD;
        this.id = id;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public void setPertanyaan(String pertanyaan) {
        this.pertanyaan = pertanyaan;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public int getSkor() {
        return skor;
    }

    public void setSkor(int skor) {
        this.skor = skor;
    }

    public String getOpsiA() {
        return opsiA;
    }

    public void setOpsiA(String opsiA) {
        this.opsiA = opsiA;
    }

    public String getOpsiB() {
        return opsiB;
    }

    public void setOpsiB(String opsiB) {
        this.opsiB = opsiB;
    }

    public String getOpsiC() {
        return opsiC;
    }

    public void setOpsiC(String opsiC) {
        this.opsiC = opsiC;
    }

    public String getOpsiD() {
        return opsiD;
    }

    public void setOpsiD(String opsiD) {
        this.opsiD = opsiD;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
