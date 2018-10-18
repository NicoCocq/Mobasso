package com.esgi.davidlinhares.mobasso.News;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.esgi.davidlinhares.mobasso.MainActivity;
import com.esgi.davidlinhares.mobasso.R;

import butterknife.BindFloat;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsFragment extends Fragment {

    @BindView(R.id.rcv_news)
    RecyclerView rcvNews;
    private NewsAdapter newsAdapter;
    @BindView(R.id.news_save)
    Button save;
    @BindView(R.id.add_news_icon)
    ImageView addNews;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_news, container, false);

        ButterKnife.bind(this, view);

        rcvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsAdapter = new NewsAdapter(this);
        rcvNews.setAdapter(newsAdapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(getActivity() instanceof MainActivity) {
            setupUi(((MainActivity) getActivity()).isSuperUserActivated());
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void setupUi(boolean superUserActivated) {
        if(superUserActivated) {
            save.setVisibility(View.VISIBLE);
            addNews.setVisibility(View.VISIBLE);
            return;
        }

        save.setVisibility(View.GONE);
        addNews.setVisibility(View.GONE);
    }

    @OnClick(R.id.news_save)
    void onSaveClicked() {
        if(getActivity() instanceof  MainActivity) {
            MainActivity activity = ((MainActivity) getActivity());
            activity.setSuperUserActivated(false);
            setupUi(false);
        }
    }

    @OnClick(R.id.add_news_icon)
    void onAddNewsClicked() {
        startActivity(new Intent(getActivity(), NewsCreationActivity.class));
    }
}
