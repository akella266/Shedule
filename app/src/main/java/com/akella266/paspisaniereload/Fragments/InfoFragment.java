package com.akella266.paspisaniereload.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import com.akella266.paspisaniereload.LessonInfo;
import com.akella266.paspisaniereload.R;

import java.util.ArrayList;
import java.util.UUID;
//{"8.00-9.30", "9.45-11.15", "11.25-12.55", "13.25-14.55", "15.05-16.35", "16.50-18.20"}
public class InfoFragment extends Fragment {

    private static final String ARG_LESSON_ID ="lesson_id";
    private ArrayList<String> times;
    //private String[] str = new String[]{"8.00-9.30", "9.45-11.15", "11.25-12.55", "13.25-14.55", "15.05-16.35", "16.50-18.20"};
    ArrayAdapter<String> timesAdapter;
    LessonInfo lessonInfo;
    EditText etLesson;
    EditText etProf;
    EditText etRoom;
    Spinner sprTime;
    Button btnOK;
    Button btnCancel;

    @Override
    public void onPause() {
        super.onPause();

        LessonSingle.get(getActivity()).updateLesson(lessonInfo);
    }

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
        //получить день как нибудь:)!!!!!!!!!!!!!!!!!1
        initFields(view);
        setListeners();

        return view;
    }


    private void initFields(View view) {
        UUID lessonId = (UUID)getArguments().getSerializable(ARG_LESSON_ID);
        lessonInfo = LessonSingle.get(getActivity()).getLesson(lessonId);

        etLesson = (EditText) view.findViewById(R.id.lesson_fragment_et_Lesson);
        etLesson.setText(lessonInfo.getLesson());
        etProf = (EditText) view.findViewById(R.id.lesson_fragment_et_Prof);
        etProf.setText(lessonInfo.getProf());
        etRoom = (EditText) view.findViewById(R.id.lesson_fragment_et_Room);
        etRoom.setText(lessonInfo.getRoom());
        btnOK = (Button)view.findViewById(R.id.lesson_fragment_btn_ok);
        btnCancel = (Button)view.findViewById(R.id.lesson_fragment_btn_cancel);

        sprTime = (Spinner) view.findViewById(R.id.lesson_fragment_spinner_time);

        times = new ArrayList<String>();
        times.add("8.00-9.30");
        times.add("9.45-11.15");
        times.add("11.25-12.55");
        times.add("13.25-14.55");
        times.add("15.05-16.35");
        times.add("16.50-18.20");
    }


    private void setListeners() {
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lessonInfo.setLesson(etLesson.getText().toString());
                lessonInfo.setProf(etProf.getText().toString());
                lessonInfo.setRoom(etRoom.getText().toString());
                lessonInfo.setTime(times.get(sprTime.getSelectedItemPosition()));
                getActivity().finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        timesAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, times);
        timesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sprTime.setAdapter(timesAdapter);
        sprTime.setPrompt("Время");
        sprTime.setSelection(times.indexOf(lessonInfo.getTime()));
        sprTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sprTime.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void returnResult(){
        getActivity().setResult(Activity.RESULT_OK, null);
    }
}
