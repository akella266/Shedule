package com.akella266.paspisaniereload.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
    private static final String ARG_LESSONS_DAY = "lessons_day";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LESSON){
            //Обработка резкультата
        }
    }

    private RecyclerView rvList;
    private ArrayList<LessonInfo> lessons;
    private RecyclerAdapter mAdapter;
    private String day;
    private TextView noLessons;
    private FloatingActionButton mFab;

    public static LessonsFragment newInstance(String day){
        Bundle args = new Bundle();
        args.putString(ARG_LESSONS_DAY, day);

        LessonsFragment lessonsFragment = new LessonsFragment();
        lessonsFragment.setArguments(args);
        return lessonsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_lesson_for_fragments, container, false);

        day = (String) getArguments().getSerializable(ARG_LESSONS_DAY);//for database and for title of toolbar

        rvList = (RecyclerView)view.findViewById(R.id.rvList);
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));

        noLessons = (TextView) view.findViewById(R.id.activity_lesson_for_fragment_no_lessons);
        mFab = (FloatingActionButton) view.findViewById(R.id.activity_lesson_for_fragments_fab);
        mFab.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LessonInfo info = new LessonInfo();
                LessonSingle.get(getActivity(),day).addLesson(info);
                Intent intent = InfoPagerActivity.newIntent(getActivity(), info.getId());
                startActivity(intent);
            }
        });

        updateUI();

        return view;
    }

    private void updateUI() {

        LessonSingle lessonSingle = LessonSingle.get(getActivity(),day);
        lessons = lessonSingle.getmLessons();

        if (lessons.size() == 0){
            noLessons.setVisibility(View.VISIBLE);
            rvList.setVisibility(View.INVISIBLE);
        }
        else {
            noLessons.setVisibility(View.INVISIBLE);
            rvList.setVisibility(View.VISIBLE);
            if (mAdapter == null) {
                mAdapter = new RecyclerAdapter(lessons);
                rvList.setAdapter(mAdapter);
            } else {
                mAdapter.setCrimes(lessons);
                mAdapter.notifyDataSetChanged();
            }
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

        public void setCrimes(ArrayList<LessonInfo> lessons){
            this.lessons = lessons;
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
