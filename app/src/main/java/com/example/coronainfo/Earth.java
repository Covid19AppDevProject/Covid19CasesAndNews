package com.example.coronainfo;

public class Earth {



    private String _id;
    private String CountryStatename;
    private String TotalCase;
    private String Activecase;
    private String Recoveredcase;
    private String Death;
    private String __v;
    private String PostDescription;
    private String time;
    private String postlink;
    private String postimageslink;

    public String getCountryStatename() {
        return CountryStatename;
    }

    public String getPostDescription() {
        return PostDescription;
    }

    public String getPostimageslink() {
        return postimageslink;
    }

    public String getTime() {
        return time;
    }

    public String getPostlink() {
        return postlink;
    }


    public String getRecoveredcase() {
        return Recoveredcase;
    }

    public String getTotalCase() {
        return TotalCase;
    }

    public String getDeath() {
        return Death;
    }

    public String getActivecase() {
        return Activecase;
    }

    public String get_id() {
        return _id;
    }

    public String get__v() {
        return __v;
    }
}
