package com.akella266.paspisaniereload.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.akella266.paspisaniereload.LessonInfo;
import com.akella266.paspisaniereload.R;

import java.util.ArrayList;
import java.util.UUID;


public class InfoPagerActivity extends FragmentActivity {

    private static final String EXTRA_LESSON_ID = "com.akella266.android.lessonintent.lesson_id";
    private ViewPager mViewPager;
    private ArrayList<LessonInfo> lessons;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_pager);

        UUID lessonId = (UUID) getIntent().getSerializableExtra(EXTRA_LESSON_ID);
        mViewPager = (ViewPager) findViewById(R.id.activity_lesson_pager_view_pager);
        lessons = LessonSingle.get(this).getmLessons();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                LessonInfo info = lessons.get(position);
                return InfoFragment.newInstance(info.getId());
            }

            @Override
            public int getCount() {
                return lessons.size();
            }
        });

        for(int i = 0; i < lessons.size(); i++){
            if (lessons.get(i).getId().equals(lessonId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }

    public static Intent newIntent(Context context, UUID uuid){
        Intent intent = new Intent(context, InfoPagerActivity.class);
        intent.putExtra(EXTRA_LESSON_ID, uuid);
        return intent;
    }
}
