package com.example.cardiacrecorder;

/**
 * Here the DataModel is declared
 */
public class DataModel {
    String date, time,systolic, diastolic, heartrate, comment;


    public DataModel(String date, String time, String systolic, String diastolic, String heartrate, String comment) {
        this.date = date;
        this.time = time;
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartrate = heartrate;
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getSystolic() {
        return systolic;
    }

    public String getDiastolic() {
        return diastolic;
    }

    public String getHeartrate() {
        return heartrate;
    }

    public String getComment() {
        return comment;
    }
}
