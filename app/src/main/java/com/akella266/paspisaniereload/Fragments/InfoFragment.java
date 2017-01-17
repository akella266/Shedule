package com.akella266.paspisaniereload.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.akella266.paspisaniereload.LessonInfo;
import com.akella266.paspisaniereload.R;

import java.util.UUID;

public class InfoFragment extends Fragment {

    private static final String ARG_LESSON_ID ="lesson_id";

    LessonInfo lessonInfo;
    EditText etLesson;
    EditText etProf;
    EditText etRoom;
    Button btnTime;

    public static InfoFragment newInstance(UUID id){
        Bundle args = new Bundle();
        args.putSerializable(ARG_LESSON_ID, id);

        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lesson_fragment, container, false );

        UUID lessonId = (UUID)getArguments().getSerializable(ARG_LESSON_ID);
        lessonInfo = LessonSingle.get(getActivity()).getLesson(lessonId);

        etLesson = (EditText) view.findViewById(R.id.lesson_fragment_et_Lesson);
        etLesson.setText(lessonInfo.getLesson());
        etProf = (EditText) view.findViewById(R.id.lesson_fragment_et_Prof);
        etProf.setText(lessonInfo.getProf());
        etRoom = (EditText) view.findViewById(R.id.lesson_fragment_et_Room);
        etRoom.setText(lessonInfo.getRoom());
        btnTime = (Button) view.findViewById(R.id.lesson_fragment_btn_time);
        btnTime.setText(lessonInfo.getTime());
        btnTime.setEnabled(false);

        return view;
    }

    public void returnResult(){
        getActivity().setResult(Activity.RESULT_OK, null);
    }
}
