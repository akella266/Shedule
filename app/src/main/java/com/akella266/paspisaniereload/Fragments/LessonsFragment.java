package com.akella266.paspisaniereload.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akella266.paspisaniereload.LessonInfo;
import com.akella266.paspisaniereload.R;

import java.util.ArrayList;

/**
 * Created by Akella266 on 09.01.2017.
 */

public class LessonsFragment extends Fragment {

    private static final int REQUEST_LESSON = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LESSON){
            //Обработка резкультата
        }
    }

    private Toolbar toolbar;
    private RecyclerView rvList;
    private ArrayList<LessonInfo> lessons;
    private RecyclerAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lesson_for_fragments, container, false);

        rvList = (RecyclerView)view.findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));

        initToolBar(view);
        updateUI();

        return view;
    }

    private void initToolBar(View v) {
        toolbar = (Toolbar) v.findViewById(R.id.toolbar);
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

    private void updateUI() {

        LessonSingle lessonSingle = LessonSingle.get(getActivity());
        lessons = lessonSingle.getmLessons();

        if (mAdapter == null){
            mAdapter = new RecyclerAdapter(lessons);
            rvList.setAdapter(mAdapter);
        }
        else{
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {

        private ArrayList<LessonInfo> lessons;

        public RecyclerAdapter(ArrayList<LessonInfo> lessons) {
            this.lessons = lessons;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_lesson, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bindLesson(lessons.get(position));
        }

        @Override
        public int getItemCount() {
            return lessons.size();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LessonInfo mLessonInfo;
        private TextView tvLesson;
        private TextView tvProf;
        private TextView tvRoom;
        private TextView tvTime;

        public void bindLesson(LessonInfo lesson){
            mLessonInfo = lesson;
            tvLesson.setText(lesson.getLesson());
            tvProf.setText(lesson.getProf());
            tvRoom.setText(lesson.getRoom());
            tvTime.setText(lesson.getTime());
        }

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            tvLesson = (TextView)itemView.findViewById(R.id.tvNameLesson);
            tvProf = (TextView)itemView.findViewById(R.id.tvProf);
            tvRoom = (TextView)itemView.findViewById(R.id.tvRoom);
            tvTime = (TextView)itemView.findViewById(R.id.tvTime);
        }

        @Override
        public void onClick(View view) {
            Intent intent = InfoPagerActivity.newIntent(getActivity(), mLessonInfo.getId());
            startActivityForResult(intent, REQUEST_LESSON);
        }
    }

}
