package com.sheygam.java_18_16_04_18.presentation.timer.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ITimerView extends MvpView {
    void updateResult(String result);
    void disableStartBtn();
    void enableStartBtn();
}
