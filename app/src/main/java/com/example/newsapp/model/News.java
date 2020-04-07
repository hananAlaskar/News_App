package com.example.newsapp.model;

import java.util.List;

public class News {

    private String mDate;
    private String mTitle;
    private String mUrl;
    private String mType;
    private String mSectionType;
    private List<String> mAuthors;


    public News(String mDate, String mTitle, String mUrl, String mType, String mSectionType) {
        this.mDate = mDate;
        this.mTitle = mTitle;
        this.mUrl = mUrl;
        this.mType = mType;
        this.mSectionType = mSectionType;
    }

    public News(String mDate, String mTitle, String mUrl, String mType, String mSectionType, List<String> mAuthors) {
        this.mDate = mDate;
        this.mTitle = mTitle;
        this.mUrl = mUrl;
        this.mType = mType;
        this.mSectionType = mSectionType;
        this.mAuthors = mAuthors;
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

    public String getmSectionType() {
        return mSectionType;
    }

    public List<String> getmAuthors() {
        return mAuthors;
    }
}
