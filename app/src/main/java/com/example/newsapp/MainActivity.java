package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private  NewsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayListViewSectionOne();
        displayListViewSectionTwo();
        displayListViewSectionThree();

    }

    private void displayListViewSectionOne() {

        ListView newsSectionOne_lv = findViewById(R.id.section_one);

        mAdapter = new NewsAdapter(this, R.id.section_one,getNewsDummyData());
        newsSectionOne_lv.setAdapter(mAdapter);

        ((TextView)(findViewById(R.id.section_one_title))).setText("Politics");


    }

    private void displayListViewSectionThree() {

        ListView newsSectionOne_lv = findViewById(R.id.section_two);

        mAdapter = new NewsAdapter(this, R.id.section_one,getNewsDummyData());
        newsSectionOne_lv.setAdapter(mAdapter);

        ((TextView)(findViewById(R.id.section_two_title))).setText("US news");

    }

    private void displayListViewSectionTwo() {

        ListView newsSectionOne_lv = findViewById(R.id.section_three);
        mAdapter = new NewsAdapter(this, R.id.section_one,getNewsDummyData());
        newsSectionOne_lv.setAdapter(mAdapter);

        ((TextView)(findViewById(R.id.section_three_title))).setText("Football");

    }



    private List<News> getNewsDummyData(){

        List<News> newsList = new ArrayList<>();

        for (int i = 0 ; i < 5 ; i ++)

           newsList.add(new News(
                   "2019-06",
                   "Democratic debates 2019: everything you need to know",
                   "https://www.theguardian.com/us-news/2019/jun/26/democratic-debate-2019-watch-2020-election-when-where-who",
                   "article"));


        return newsList;
    }


}
