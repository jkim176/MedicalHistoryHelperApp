package com.jkim176.project1;

public class Vaccination {

    private long fk;
    private String vaccinationName;
    private String boosterNumber;
    private String date;

    public Vaccination() {}

    public Vaccination(long fk, String vaccinationName, String boosterNumber, String date) {
        this.fk = fk;
        this.vaccinationName = vaccinationName;
        this.boosterNumber = boosterNumber;
        this.date = date;
    }

    public long getFk() {
        return fk;
    }

    public void setFk(long fk) {
        this.fk = fk;
    }

    public String getVaccinationName() {
        return vaccinationName;
    }

    public void setVaccinationName(String vaccinationName) {
        this.vaccinationName = vaccinationName;
    }

    public String getBoosterNumber() {
        return boosterNumber;
    }

    public void setBoosterNumber(String boosterNumber) {
        this.boosterNumber = boosterNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
