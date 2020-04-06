package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.newsapp.model.News;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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


        String date = formatDate(fromString(currentNews.getmDate()));
        if (date != null)
            ((TextView)(listItemView.findViewById(R.id.news_date))).setText(date);
        ((TextView)(listItemView.findViewById(R.id.news_title))).setText(currentNews.getmTitle());
        ((TextView)(listItemView.findViewById(R.id.news_type))).setText(currentNews.getmType());

    }


    private String formatDate(Date dateObject) {
        if (dateObject == null)
            return null;

        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    private Date fromString(String dtStart) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = null;
        try {
            date = format.parse(dtStart);

            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
