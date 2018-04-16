package com.sheygam.java_18_16_04_18.presentation.timer.presenter;

import android.os.AsyncTask;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.sheygam.java_18_16_04_18.presentation.timer.view.ITimerView;

@InjectViewState
public class TimerPresenter extends MvpPresenter<ITimerView> {

    public void onStart(){

        new AsyncTask<Void,Integer,Void>(){

            @Override
            protected void onPreExecute() {
                getViewState().disableStartBtn();
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                getViewState().updateResult(String.valueOf(values[0]));

            }

            @Override
            protected Void doInBackground(Void... voids) {
                for (int i = 0; i < 10; i++) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    publishProgress(i);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                getViewState().enableStartBtn();
            }
        }.execute();
    }
}
