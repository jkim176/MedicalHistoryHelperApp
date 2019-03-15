package com.jkim176.project1;

public class Symptom {

    private long fk;
    private String symptomName;
    private String area;
    private String duration;
    private String startDate;
    private String endDate;

    public Symptom() {}

    public Symptom(long fk, String symptomName, String area, String duration, String startDate, String endDate) {
        this.fk = fk;
        this.symptomName = symptomName;
        this.area = area;
        this.duration = duration;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getFk() {
        return fk;
    }

    public void setFk(long fk) {
        this.fk = fk;
    }

    public String getSymptomName() {
        return symptomName;
    }

    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
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
