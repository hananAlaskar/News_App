package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newsapp.model.News;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private  NewsAdapter mAdapter;


    private static final String THE_GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search?q=debates&api-key=f90e137e-8881-4edd-ba02-3e6dd566cbb1";

    private static final int NEWS_LOADER_ID = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getSupportLoaderManager().initLoader(NEWS_LOADER_ID, null, this).forceLoad();



    }

    private void displayListViewSectionOne(List<News> newsList) {

        ListView newsSectionOne_lv = findViewById(R.id.section_one);

        mAdapter = new NewsAdapter(this, R.id.section_one,newsList);
        newsSectionOne_lv.setAdapter(mAdapter);

        ((TextView)(findViewById(R.id.section_one_title))).setText("Politics");


    }

    private void displayListViewSectionThree(List<News> newsList) {

        ListView newsSectionOne_lv = findViewById(R.id.section_two);

        mAdapter = new NewsAdapter(this, R.id.section_one,newsList);
        newsSectionOne_lv.setAdapter(mAdapter);

        ((TextView)(findViewById(R.id.section_two_title))).setText("US news");

    }

    private void displayListViewSectionTwo(List<News> newsList) {

        ListView newsSectionOne_lv = findViewById(R.id.section_three);
        mAdapter = new NewsAdapter(this, R.id.section_one,newsList);
        newsSectionOne_lv.setAdapter(mAdapter);

        ((TextView)(findViewById(R.id.section_three_title))).setText("Football");

    }



    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {


        return new NewsLoader(this, THE_GUARDIAN_REQUEST_URL);
    }


    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsList) {

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        if (newsList != null && !newsList.isEmpty()) {
            updateUi(newsList);
        }
    }

    private void updateUi(List<News> newsList) {

        displayListViewSectionOne(newsList);
        displayListViewSectionTwo(newsList);
        displayListViewSectionThree(newsList);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {

    }
}
