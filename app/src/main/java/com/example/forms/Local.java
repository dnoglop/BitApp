package com.example.forms;

public class Local {

    private long _id;
    private double latitude;
    private double longitude;

    public Local(long _id, double latitude, double longitude) {
        this._id = _id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public double getLatitude() {
        return latitude;
    }


    public double getLongitude() {
        return longitude;
    }


    @Override
    public String toString() {
        return "Latitude  = " + latitude + "\n"+
               "Longitude = " + longitude +"\n";
    }
}
