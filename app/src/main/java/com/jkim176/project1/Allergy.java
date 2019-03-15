package com.jkim176.project1;

public class Allergy {

    private long fk;
    private String allergyName;
    private String area;
    private String severity;
    private String startDate;
    private String endDate;

    public Allergy() {}

    public Allergy(long fk, String allergyName, String area, String severity, String startDate, String endDate) {
        this.fk = fk;
        this.allergyName = allergyName;
        this.area = area;
        this.severity = severity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getFk() {
        return fk;
    }

    public void setFk(long fk) {
        this.fk = fk;
    }

    public String getAllergyName() {
        return allergyName;
    }

    public void setAllergyName(String allergyName) {
        this.allergyName = allergyName;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
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
