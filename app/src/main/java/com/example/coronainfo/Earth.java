package com.example.coronainfo;

public class Earth {
 private String mcountry;
 private long mcases;
    private long mtodayCases;
    private long mdeaths;
    private long mtodayDeaths;
    private long mrecovered;
    private long mactive;
    private long mcritical;

     public  Earth( String a,long b,long c,long d,long e,long f,long g,long h){
         mcountry=a;
         mcases=b;
         mtodayCases=c;
         mdeaths=d;
         mtodayDeaths=e;
         mrecovered=f;
         mactive=g;
         mcritical=h;

    }
    public String getMcountry() {
        return mcountry;
    }

    public long getMcases() {
        return mcases;
    }
    public long getMtodayCases() {
        return mtodayCases;
    }
    public long getMdeaths() {
        return mdeaths;
    }
    public long getMtodayDeaths() {
        return mtodayDeaths;
    }
    public long getMrecovered() {
        return mrecovered;
    }
    public long getMactive() {
        return mactive;
    }
    public long getMcritical() {
        return mcritical;
    }
}
