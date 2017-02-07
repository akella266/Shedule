package com.akella266.paspisaniereload.Fragments;

import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.akella266.paspisaniereload.R;

/**
 * Created by Akella266 on 08.01.2017.
 */

public class MainActivityWeek extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {

        return new WeekFragment();
    }
}
