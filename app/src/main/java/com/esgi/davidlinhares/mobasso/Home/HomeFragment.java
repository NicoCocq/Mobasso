package com.esgi.davidlinhares.mobasso.Home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.esgi.davidlinhares.mobasso.MainActivity.STATIC_INTEGER_VALUE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
