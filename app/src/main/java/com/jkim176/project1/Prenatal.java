package com.jkim176.project1;

public class Prenatal {

    private long fk;
    private String weight;
    private String week;

    public Prenatal() {}

    public Prenatal(long fk, String weight, String week) {
        this.fk = fk;
        this.weight = weight;
        this.week = week;
    }

    public long getFk() {
        return fk;
    }

    public void setFk(long fk) {
        this.fk = fk;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
