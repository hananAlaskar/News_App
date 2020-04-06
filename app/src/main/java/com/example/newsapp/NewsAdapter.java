package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.model.News;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NewsAdapter extends ArrayAdapter<News> {


    private Context mContext;


    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
        this.mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_item, parent, false);
        }


        News currentNews = getItem(position);

        setDataOnView(currentNews,listItemView);
        addOnClickListenerOnNewsItem(currentNews,listItemView);


        return listItemView;
    }

    private void addOnClickListenerOnNewsItem(final News currentNews, View listItemView) {

        listItemView.findViewById(R.id.news_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Uri newsUri = Uri.parse(currentNews.getmUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);

                mContext.startActivity(websiteIntent);
            }
        });

    }

    private void setDataOnView(News currentNews, View listItemView) {

        ((TextView)(listItemView.findViewById(R.id.news_date))).setText(currentNews.getmDate());
        ((TextView)(listItemView.findViewById(R.id.news_title))).setText(currentNews.getmTitle());
        ((TextView)(listItemView.findViewById(R.id.news_type))).setText(currentNews.getmType());

    }
}
