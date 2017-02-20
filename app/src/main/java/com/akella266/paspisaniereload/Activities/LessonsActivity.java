package com.akella266.paspisaniereload.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import com.akella266.paspisaniereload.Fragments.LessonsFragment;


public class LessonsActivity extends SingleFragmentActivity {

    private static final String EXTRA_LESSONS_DAY =
            "com.akella266.android.lessonintent.lessons_day";

    @Override
    protected Fragment createFragment() {
        String day = (String) getIntent().getSerializableExtra(EXTRA_LESSONS_DAY);
        getSupportActionBar().setTitle(day);

        return LessonsFragment.newInstance(day);
    }

    public static Intent newIntent(Context context, String day){
        Intent intent = new Intent(context, LessonsActivity.class);
        intent.putExtra(EXTRA_LESSONS_DAY, day);
        return intent;
    }
}
