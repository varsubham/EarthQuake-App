package com.example.earthquake;

public class EarthquakeClass {

    private double mag;
    private String name;
    private long time;
    private String url;

    public String getUrl(){ return url;}
    public double getMag() {
        return mag;
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    public EarthquakeClass(double mag, String name, long time, String url){
        this.mag = mag;
        this.name = name;
        this.time = time;
        this.url = url;
    }
}
