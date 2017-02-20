package com.akella266.paspisaniereload.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.akella266.paspisaniereload.Activities.LessonsActivity;
import com.akella266.paspisaniereload.R;

public class WeekFragment extends Fragment implements AdapterView.OnItemClickListener{

    String[] week = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
    GridView gvMain;
    ArrayAdapter<String> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.week_fragment, container, false);



        initGridWithButtons(v);

        return v;
    }

    private void initGridWithButtons(View v) {
        adapter = new ArrayAdapter<String>(getContext(), R.layout.button_week,R.id.tvText, week);
        gvMain = (GridView)v.findViewById(R.id.gridView);
        gvMain.setAdapter(adapter);
        gvMain.setColumnWidth(400);
        gvMain.setVerticalSpacing(32);
        gvMain.setHorizontalSpacing(32);
        gvMain.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = LessonsActivity.newIntent(getContext(), week[i]);
        startActivity(intent);
    }
}
