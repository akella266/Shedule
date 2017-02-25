package com.akella266.paspisaniereload.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.akella266.paspisaniereload.Fragments.LessonsFragment;


public class LessonsActivity extends SingleFragmentActivity {

    private static final String EXTRA_LESSONS_DAY =
            "com.akella266.android.lessonintent.lessons_day";
    public static final String EXTRA_TYPE_WEEK =
            "com.akella266.android.lessonintent.type_week";

    @Override
    protected Fragment createFragment() {
        String day = (String) getIntent().getSerializableExtra(EXTRA_LESSONS_DAY);
        String week = (String) getIntent().getSerializableExtra(EXTRA_TYPE_WEEK);
        getSupportActionBar().setTitle(day);

        return LessonsFragment.newInstance(day, week);
    }

    public static Intent newIntent(Context context, String day, String week){
        Intent intent = new Intent(context, LessonsActivity.class);
        intent.putExtra(EXTRA_LESSONS_DAY, day);
        intent.putExtra(EXTRA_TYPE_WEEK, week);
        return intent;
    }
}
