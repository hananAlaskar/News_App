package com.example.newsapp.model;

public class News {

    private String mDate;
    private String mTitle;
    private String mUrl;
    private String mType;
    private String mSectionType;


    public News(String mDate, String mTitle, String mUrl, String mType, String mSectionType) {
        this.mDate = mDate;
        this.mTitle = mTitle;
        this.mUrl = mUrl;
        this.mType = mType;
        this.mSectionType = mSectionType;
    }

    public String getmDate() {
        return mDate;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmType() {
        return mType;
    }
}
