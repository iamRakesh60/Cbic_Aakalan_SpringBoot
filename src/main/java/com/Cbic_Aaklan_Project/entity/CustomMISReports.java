package com.Cbic_Aaklan_Project.entity;

public class CustomMISReports {
    private String zone_name;
    private String zone_code;
    private Double weighted_average;
    private Double national_average;
    private String date;
    private String cusname;

    public String getZone_name() {
        return zone_name;
    }

    public void setZone_name(String zone_name) {
        this.zone_name = zone_name;
    }

    public String getZone_code() {
        return zone_code;
    }

    public void setZone_code(String zone_code) {
        this.zone_code = zone_code;
    }

    public Double getWeighted_average() {
        return weighted_average;
    }

    public void setWeighted_average(Double weighted_average) {
        this.weighted_average = weighted_average;
    }

    public Double getNational_average() {
        return national_average;
    }

    public void setNational_average(Double national_average) {
        this.national_average = national_average;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCusname() {
        return cusname;
    }

    public void setCusname(String cusname) {
        this.cusname = cusname;
    }

    public CustomMISReports(String zone_name, String zone_code, Double weighted_average, Double national_average, String date, String cusname) {
        this.zone_name = zone_name;
        this.zone_code = zone_code;
        this.weighted_average = weighted_average;
        this.national_average = national_average;
        this.date = date;
        this.cusname = cusname;
    }
}
