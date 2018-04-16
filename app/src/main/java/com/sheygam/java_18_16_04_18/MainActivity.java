package com.sheygam.java_18_16_04_18;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView resultTxt;
    private Button setResultBtn, startNextBtn, startTimerBtn;
    private MyTimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTxt = findViewById(R.id.result_txt);
        setResultBtn = findViewById(R.id.set_result_btn);
        startNextBtn = findViewById(R.id.start_next_btn);
        startTimerBtn = findViewById(R.id.start_timer_btn);
        startTimerBtn.setOnClickListener(this);
        startNextBtn.setOnClickListener(this);
        setResultBtn.setOnClickListener(this);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,new MyFragment())
                .commit();
        Object obj = getLastCustomNonConfigurationInstance();
        if(obj!=null){
            task = (MyTimerTask) obj;
            task.attachActivity(this);
        }
//        if(savedInstanceState!=null){
//            resultTxt.setText(savedInstanceState.getString("RESULT"));
//        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.set_result_btn){
            resultTxt.setText("My result!");
        }else if(v.getId() == R.id.start_next_btn){
            startActivity(new Intent(this,SecondActivity.class));
        }else if(v.getId() == R.id.start_timer_btn){
            task = new MyTimerTask();
            task.attachActivity(this);
            task.execute();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("MY_TAG", "onSaveInstanceState: ");
        outState.putString("RESULT",resultTxt.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("MY_TAG", "onRestoreInstanceState: ");
        resultTxt.setText(savedInstanceState.getString("RESULT"));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        if(task != null){
            return task;
        }
        return super.onRetainCustomNonConfigurationInstance();
    }

    @Override
    protected void onDestroy() {
        if(!isFinishing() && task!=null){
            task.detachActivity();
        }
        super.onDestroy();
    }

    class MyTimerTask extends AsyncTask<Void,Integer,Void>{
        private MainActivity activity;

        public void attachActivity(MainActivity activity){
            this.activity = activity;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if(activity!=null){
                activity.resultTxt.setText(String.valueOf(values[0]));
            }

        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i < 10; i++) {
                Log.d("MY_TAG", "doInBackground: " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return null;
        }

        public void detachActivity(){
            activity = null;
        }
    }
}
