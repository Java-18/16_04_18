package com.sheygam.java_18_16_04_18;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyFragment extends Fragment implements View.OnClickListener {
    private TextView resultTxt;
    private String result = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("MY_TAG", "onCreateView: ");
        return inflater.inflate(R.layout.my_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        resultTxt = view.findViewById(R.id.result_txt);
        resultTxt.setText(result);
        Button setResult = view.findViewById(R.id.set_result_btn);
        setResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.set_result_btn){
            result = "Result";
            resultTxt.setText("Result!");
        }
    }

}
