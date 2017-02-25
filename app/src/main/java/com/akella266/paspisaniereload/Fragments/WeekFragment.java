package com.akella266.paspisaniereload.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.text.format.Time;

import com.akella266.paspisaniereload.Activities.LessonsActivity;
import com.akella266.paspisaniereload.Enums.TimeWhen;
import com.akella266.paspisaniereload.Intefaces.onBackPressedListener;
import com.akella266.paspisaniereload.R;

public class WeekFragment extends Fragment implements AdapterView.OnItemClickListener, onBackPressedListener {

    private static final String STATE_WEEK = "state_week";
    private static final String NUM_LAST_WEEK = "num_last_week";

    private String[] week = {"Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
    private GridView gvMain;
    private ArrayAdapter<String> adapter;
    private TimeWhen typeWeek;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.week_fragment, container, false);

//        SharedPreferences mPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor ed = mPref.edit();
//        Time time = new Time(Time.getCurrentTimezone());
//        String numWeek = Integer.valueOf(time.getWeekNumber()).toString();
//
//        ed.putString(STATE_WEEK, TimeWhen.OverLine.toString());
//        ed.putString(NUM_LAST_WEEK, numWeek);
//        ed.commit();

        loadStateWeek();
        initGridWithButtons(v);

        return v;
    }

    private void initGridWithButtons(View v) {
        adapter = new ArrayAdapter<String>(getContext(), R.layout.button_week, R.id.tvText, week);
        gvMain = (GridView) v.findViewById(R.id.gridView);
        gvMain.setAdapter(adapter);
        gvMain.setColumnWidth(400);
        gvMain.setVerticalSpacing(32);
        gvMain.setHorizontalSpacing(32);
        gvMain.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent = LessonsActivity.newIntent(getContext(), week[i], typeWeek.toString());
        startActivity(intent);
    }

    private void loadStateWeek() {
        SharedPreferences mPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String state = mPref.getString(STATE_WEEK, "");
        typeWeek = TimeWhen.valueOf(state);
    }

    @Override
    public void onBackPressed() {
        SharedPreferences mPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor ed = mPref.edit();
        Time time = new Time(Time.getCurrentTimezone());
        String numWeek = mPref.getString(NUM_LAST_WEEK, "");

        if ((time.getWeekNumber() == Integer.parseInt(numWeek) && time.weekDay == Time.SUNDAY) ||
                time.getWeekNumber() == Integer.parseInt(numWeek)) {                                // if current day is Sunday,
            if (typeWeek == TimeWhen.OverLine)                                                      // I change type of week and rewrite new number week
                ed.putString(STATE_WEEK, TimeWhen.UnderLine.toString());                            // I did it for we can see lessons of next week
            else
                ed.putString(STATE_WEEK, TimeWhen.OverLine.toString());
            ed.putString(NUM_LAST_WEEK, numWeek);
            ed.commit();
        }
    }
}


