package com.akella266.paspisaniereload.Activities;

import android.support.v4.app.Fragment;

import com.akella266.paspisaniereload.Fragments.WeekFragment;

public class MainActivityWeek extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {

        return new WeekFragment();
    }
}
