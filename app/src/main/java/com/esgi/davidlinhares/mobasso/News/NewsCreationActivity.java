package com.esgi.davidlinhares.mobasso.News;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.esgi.davidlinhares.mobasso.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsCreationActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 12;
    @BindView(R.id.article_details_create_edit)
    EditText details;
    @BindView(R.id.article_title_create_edit)
    EditText title;
    @BindView(R.id.article_img_download)
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_creation);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.article_send)
    void onSendClicked() {
        String title = this.title.getText().toString();
        String details = this.details.getText().toString();

        if(title.isEmpty() || details.isEmpty()) {
            Toast.makeText(this, R.string.missing_fields, Toast.LENGTH_SHORT).show();
        }
        //TODO send to api
    }

    @OnClick(R.id.article_cancel)
    void onCancelClicked() {
        finish();
    }
    
    @OnClick(R.id.article_img_download)
    void onImageClicked() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            if (data == null) return;
            /*Glide.with(this)
                    .load(data.getData())
                    .apply(RequestOptions.bitmapTransform(new CircleTransform(getContext())))
                    .into(profileImageView);*/
        }
    }

}