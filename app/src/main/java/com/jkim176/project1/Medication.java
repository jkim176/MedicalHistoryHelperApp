package com.jkim176.project1;

public class Medication {

    private long fk;
    private String medicationName;
    private String dose;
    private String frequency;
    private String startDate;
    private String endDate;

    public Medication() {}

    public Medication(long fk, String medicationName, String dose, String frequency, String startDate, String endDate) {
        this.fk = fk;
        this.medicationName = medicationName;
        this.dose = dose;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getFk() {
        return fk;
    }

    public void setFk(long fk) {
        this.fk = fk;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
