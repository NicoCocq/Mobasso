package com.esgi.davidlinhares.mobasso.Home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esgi.davidlinhares.mobasso.Common.Config;
import com.esgi.davidlinhares.mobasso.MainActivity;
import com.esgi.davidlinhares.mobasso.R;
import com.esgi.davidlinhares.mobasso.login.LoginActivity;
import com.google.gson.Gson;

import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.esgi.davidlinhares.mobasso.MainActivity.STATIC_INTEGER_VALUE;

public class HomeFragment extends Fragment {

    @BindView(R.id.home_association_logo)
    ImageView logo;
    @BindView(R.id.home_association_title)
    TextView title;
    @BindView(R.id.home_association_title_edit)
    EditText titleEdit;
    @BindView(R.id.home_association_details)
    TextView details;
    @BindView(R.id.home_association_details_edit)
    EditText detailsEdit;
    @BindView(R.id.home_save) public Button saveButton;

    private short touches;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(getActivity() instanceof  MainActivity) {
            setupUi(((MainActivity) getActivity()).isSuperUserActivated());
        }

        if(Config.isIsConfigLoaded()) {
            title.setText(Config.getInstance().getAssociation_name());
            details.setText(Config.getInstance().getAssociation_detail());
            titleEdit.setText(Config.getInstance().getAssociation_name());
            detailsEdit.setText(Config.getInstance().getAssociation_detail());
        }
    }

    public void setupUi(boolean superUserActivated) {
        if(superUserActivated) {
            title.setVisibility(View.GONE);
            titleEdit.setVisibility(View.VISIBLE);
            details.setVisibility(View.GONE);
            detailsEdit.setVisibility(View.VISIBLE);
            saveButton.setVisibility(View.VISIBLE);
            return;
        }

        title.setVisibility(View.VISIBLE);
        titleEdit.setVisibility(View.GONE);
        details.setVisibility(View.VISIBLE);
        detailsEdit.setVisibility(View.GONE);
        saveButton.setVisibility(View.GONE);
        logo.setClickable(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.home_association_logo)
    void onTouchAssociationLogo() {
        final CountDownTimer timer = new CountDownTimer(3000, 1) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                timerFinished();
            }
        };

        if(touches == 0) {
           timer.start();
        }

        touches++;

        if(touches == 3) {
            if(getActivity() instanceof MainActivity) {
                timer.onFinish();
                startActivityForResult(new Intent(this.getActivity(), LoginActivity.class), STATIC_INTEGER_VALUE);
                logo.setClickable(false);
                touches = 0;
            }
        }
    }

    @OnTextChanged(value = R.id.home_association_title_edit, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onTitleChanged(Editable s) {
        Config.getInstance().setAssociation_name(s.toString());
        String json = new Gson().toJson(Config.getInstance(), Config.class);
        Config.getInstance().saveConfig(json, getContext());

        if(title.getText() != s.toString())
            title.setText(s.toString());
    }

    @OnTextChanged(value = R.id.home_association_details_edit, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void onDetailChanged(Editable s) {
        Config.getInstance().setAssociation_detail(s.toString());
        String json = new Gson().toJson(Config.getInstance(), Config.class);
        Config.getInstance().saveConfig(json, getContext());

        if(details.getText() != s.toString())
            details.setText(s.toString());
    }

    private void timerFinished() {
        touches = 0;
    }

    @OnClick(R.id.home_save)
    void onSaveClicked() {
        if(getActivity() instanceof  MainActivity) {
            MainActivity activity = ((MainActivity) getActivity());
            activity.setSuperUserActivated(false);
            setupUi(false);
        }
    }
}
