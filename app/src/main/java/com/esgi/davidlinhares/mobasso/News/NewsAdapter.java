package com.esgi.davidlinhares.mobasso.News;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esgi.davidlinhares.mobasso.R;
import com.esgi.davidlinhares.mobasso.api.AccountService;
import com.esgi.davidlinhares.mobasso.api.ApiManager;
import com.esgi.davidlinhares.mobasso.api.ContainerService;
import com.google.gson.Gson;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private NewsFragment fragment;
    private List<News> news = new ArrayList<>();

    public NewsAdapter(NewsFragment fragment) {
        this.fragment = fragment;
        retrieveNews();
    }

    private void retrieveNews() {
        ApiManager.getInstance().getRetrofit().create(AccountService.class)
                .getNews(fragment.getString(R.string.user_id))
                .enqueue(new Callback<List<News>>() {
                    @Override
                    public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                        news = response.body();
                        Collections.reverse(news);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<News>> call, Throwable t) {

                    }
                });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof NewsViewHolder) {
            final NewsViewHolder newsViewHolder = (NewsViewHolder)holder;
            final News news = this.news.get(position);
            newsViewHolder.newsTitle.setText(news.getTitle());
            newsViewHolder.newsDetails.setText(news.getDetails());
            if(!news.getImage().isEmpty()) {
                ApiManager.getInstance().getRetrofit().create(ContainerService.class)
                        .download(fragment.getString(R.string.user_id), news.getImage())
                        .enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                                newsViewHolder.setBitmap(bmp);
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
            } else {
                newsViewHolder.newsImage.setVisibility(View.GONE);
            }
            newsViewHolder.newsTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String json = new Gson().toJson(news, News.class);
                    Intent intent = new Intent(fragment.getActivity(), NewsDetailsActivity.class);
                    intent.putExtra(fragment.getString(R.string.NEWS_SHARE), json);
                    fragment.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView newsImage;
        TextView newsTitle;
        TextView newsDetails;
        private Bitmap bitmap;

        public NewsViewHolder(View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.news_item_image);
            newsTitle = itemView.findViewById(R.id.news_item_title);
            newsDetails = itemView.findViewById(R.id.news_item_details);
        }

        public void setBitmap(Bitmap bitmap) {
            this.bitmap = bitmap;
            newsImage.setVisibility(View.VISIBLE);
            newsImage.setImageBitmap(bitmap);
        }
    }
}
