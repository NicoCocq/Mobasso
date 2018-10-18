package com.esgi.davidlinhares.mobasso.News;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.esgi.davidlinhares.mobasso.R;
import com.esgi.davidlinhares.mobasso.api.ApiManager;
import com.esgi.davidlinhares.mobasso.api.ContainerService;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsDetailsActivity extends AppCompatActivity {

    @BindView(R.id.news_detail_item_image)
    ImageView imageView;
    @BindView(R.id.news_detail_item_title)
    TextView title;
    @BindView(R.id.news_detail_item_details)
    TextView details;
    private News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        ButterKnife.bind(this);

        String data = getIntent().getStringExtra(getString(R.string.NEWS_SHARE));
        news = new Gson().fromJson(data, News.class);

        setupUi();
    }

    private void setupUi() {
        setupImage();
        title.setText(news.getTitle());
        details.setText(news.getDetails());
    }

    private void setupImage() {
        if(!news.getImage().isEmpty()) {
            ApiManager.getInstance().getRetrofit().create(ContainerService.class)
                    .download(getString(R.string.user_id), news.getImage())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                            imageView.setImageBitmap(bmp);
                            try {
                                String res = new String(response.body().bytes());
                            } catch (Exception e) {}
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
        } else {
            imageView.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.news_detail_back)
    void onDetailBackPressed() {
        finish();
    }
}
