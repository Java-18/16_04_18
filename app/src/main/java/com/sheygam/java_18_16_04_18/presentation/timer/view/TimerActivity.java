package com.sheygam.java_18_16_04_18.presentation.timer.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.sheygam.java_18_16_04_18.R;
import com.sheygam.java_18_16_04_18.presentation.timer.presenter.TimerPresenter;

public class TimerActivity extends MvpAppCompatActivity implements ITimerView, View.OnClickListener {

    @InjectPresenter
    TimerPresenter presenter;

    private TextView resultTxt;
    private Button startBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        resultTxt = findViewById(R.id.result_txt);
        startBtn = findViewById(R.id.start_btn);
        startBtn.setOnClickListener(this);
    }

    @Override
    public void updateResult(String result) {
        resultTxt.setText(result);
    }

    @Override
    public void disableStartBtn() {
        startBtn.setEnabled(false);
    }

    @Override
    public void enableStartBtn() {
        startBtn.setEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.start_btn){
            presenter.onStart();
        }
    }
}
