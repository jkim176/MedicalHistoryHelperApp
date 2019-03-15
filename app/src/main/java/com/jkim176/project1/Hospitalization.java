package com.jkim176.project1;

public class Hospitalization {

    private long fk;
    private String hospitalizationName;
    private String complication;
    private String startDate;
    private String endDate;

    public Hospitalization() {}

    public Hospitalization(long fk, String hospitalizationName, String complication, String startDate, String endDate) {
        this.fk = fk;
        this.hospitalizationName = hospitalizationName;
        this.complication = complication;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getFk() {
        return fk;
    }

    public void setFk(long fk) {
        this.fk = fk;
    }

    public String getHospitalizationName() {
        return hospitalizationName;
    }

    public void setHospitalizationName(String hospitalizationName) {
        this.hospitalizationName = hospitalizationName;
    }

    public String getComplication() {
        return complication;
    }

    public void setComplication(String complication) {
        this.complication = complication;
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
