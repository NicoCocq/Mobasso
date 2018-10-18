package com.esgi.davidlinhares.mobasso.News;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esgi.davidlinhares.mobasso.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private NewsFragment fragment;
    private List<News> news = new ArrayList<News>() {
        
    };

    public NewsAdapter(NewsFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NewsViewHolder) {
            NewsViewHolder newsViewHolder = (NewsViewHolder)holder;
            News news = this.news.get(position);
            newsViewHolder.newsTitle.setText(news.getTitle());
            newsViewHolder.newsDetails.setText(news.getDetails());
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    private class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView newsImage;
        TextView newsTitle;
        TextView newsDetails;

        public NewsViewHolder(View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.news_item_image);
            newsTitle = itemView.findViewById(R.id.news_item_title);
            newsDetails = itemView.findViewById(R.id.news_item_details);
        }
    }
}
