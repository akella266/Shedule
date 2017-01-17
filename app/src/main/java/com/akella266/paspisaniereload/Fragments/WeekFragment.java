package com.akella266.paspisaniereload.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.akella266.paspisaniereload.R;

public class WeekFragment extends Fragment implements AdapterView.OnItemClickListener{

    String[] week = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
    Toolbar toolbar;
    GridView gvMain;
    ArrayAdapter<String> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.week_fragment, container, false);

        initToolBar(v);
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

    private void initToolBar(View v){
        toolbar = (Toolbar)v.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolBar_titleColor));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        startActivity(new Intent(getContext(),LessonsActivity.class));
    }
}
