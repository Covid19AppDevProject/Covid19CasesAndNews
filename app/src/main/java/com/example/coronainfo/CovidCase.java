package com.example.coronainfo;


public class CovidCase {



    private String m_id;
    private String mCountryStatename;
    private String mTotalCase;
    private String mActivecase;
    private String mRecoveredcase;
    private String mDeath;
    private String m__v;
    private String  mpostdescription  ,  mpostimagelink ,  mpostlink,   mtime;
    public CovidCase(String id, String  countrystate, String  TotalCase , String  Activecase, String  Recoveredcase, String  Death, String  __v) {

         m_id=id;
          mCountryStatename=countrystate;
        mTotalCase=TotalCase;
        mActivecase=Activecase;
          mRecoveredcase=Recoveredcase;
          mDeath=Death;
        m__v=__v;

    }

    public CovidCase(String id, String  countrystate, String  postdescription, String  postimagelink, String  postlink, String  time ) {
        m_id=id;
        mCountryStatename=countrystate;
        mpostdescription=postdescription;
        mpostimagelink=postimagelink;
        mpostlink=postlink;
        mtime=time;

    }

    public String getMpostdescription() {
        return mpostdescription;
    }

    public String getMpostimagelink() {
        return mpostimagelink;
    }

    public String getMpostlink() {
        return mpostlink;
    }

    public String getMtime() {
        return mtime;
    }

    public String getM_id() {
        return m_id;
    }

    public String getmCountryStatename() {
        return mCountryStatename;
    }

    public String getmActivecase() {
        return mActivecase;
    }

    public String getmRecoveredcase() {
        return mRecoveredcase;
    }

    public String getmDeath() {
        return mDeath;
    }

    public String getmTotalCase() {
        return mTotalCase;
    }

    public String getM__v() {
        return m__v;
    }
}
