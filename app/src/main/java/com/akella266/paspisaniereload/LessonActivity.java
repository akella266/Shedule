package com.akella266.paspisaniereload;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

/**
 * Created by Akella266 on 06.01.2017.
 */

public class LessonActivity extends Activity {

    Toolbar toolbar;
    RecyclerView rvList;
    ArrayList<LessonInfo> lessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);

        initToolBar();
        initRecycler();
    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Lesson");
        toolbar.setTitleTextColor(getResources().getColor(R.color.toolBar_titleColor));
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
        toolbar.inflateMenu(R.menu.menu);
    }

    private void initRecycler(){
        rvList = (RecyclerView)findViewById(R.id.rvLList);
        rvList.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvList.setLayoutManager(layoutManager);
        lessons = new ArrayList<>();
        lessons.add(new LessonInfo("Мат. анализ", "Задорожнюк М.В.", "2-413", "8.00-9.30"));
        lessons.add(new LessonInfo("Программирование", "Косинов Г.П.", "2-223", "9.45-11.15"));

        RecyclerView.Adapter adapter = new RecyclerAdapter(lessons);
        rvList.setAdapter(adapter);
    }

}
