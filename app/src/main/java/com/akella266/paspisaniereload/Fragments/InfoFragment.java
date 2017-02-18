package com.akella266.paspisaniereload.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


import com.akella266.paspisaniereload.Intefaces.onBackPressedListener;
import com.akella266.paspisaniereload.LessonInfo;
import com.akella266.paspisaniereload.R;
import com.akella266.paspisaniereload.Enums.Types;

import java.util.ArrayList;
import java.util.UUID;

public class InfoFragment extends Fragment implements onBackPressedListener {

    private static final String ARG_LESSON_ID ="lesson_id";
    private ArrayList<String> times;
    ArrayAdapter<String> timesAdapter;
    ArrayAdapter<Types> typesAdapter;
    LessonInfo lessonInfo;
    EditText etLesson;
    EditText etProf;
    EditText etRoom;
    Spinner sprTime;
    Spinner sprType;
    Button btnOK;
    Button btnCancel;
    boolean isNew;

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

        initFields(view);
        setListeners();

        return view;
    }


    private void initFields(View view) {
        UUID lessonId = (UUID)getArguments().getSerializable(ARG_LESSON_ID);
        lessonInfo = LessonSingle.get(getActivity()).getLesson(lessonId);

        if (lessonInfo.getLesson() == null &&
                lessonInfo.getProf() == null &&
                lessonInfo.getRoom() == null) isNew = true;

        etLesson = (EditText) view.findViewById(R.id.lesson_fragment_et_Lesson);
        etLesson.setText(lessonInfo.getLesson());
        etProf = (EditText) view.findViewById(R.id.lesson_fragment_et_Prof);
        etProf.setText(lessonInfo.getProf());
        etRoom = (EditText) view.findViewById(R.id.lesson_fragment_et_Room);
        etRoom.setText(lessonInfo.getRoom());
        btnOK = (Button)view.findViewById(R.id.lesson_fragment_btn_ok);
        btnCancel = (Button)view.findViewById(R.id.lesson_fragment_btn_cancel);

        sprTime = (Spinner) view.findViewById(R.id.lesson_fragment_spinner_time);

        times = new ArrayList<>();
        times.add("8.00-9.30");
        times.add("9.45-11.15");
        times.add("11.25-12.55");
        times.add("13.25-14.55");
        times.add("15.05-16.35");
        times.add("16.50-18.20");

        sprType = (Spinner) view.findViewById(R.id.lesson_fragment_spinner_type);
    }

    private void setListeners() {
        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etLesson.getText().toString().length() == 0) {
                    Snackbar.make(view, R.string.no_name_lesson, Snackbar.LENGTH_SHORT).show();
                } else {
                    lessonInfo.setLesson(etLesson.getText().toString());
                    lessonInfo.setProf(etProf.getText().toString());
                    lessonInfo.setRoom(etRoom.getText().toString());
                    lessonInfo.setTime(sprTime.getSelectedItem().toString());
                    lessonInfo.setType(sprType.getSelectedItem().toString());
                    getActivity().finish();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkIsNew();
                getActivity().finish();
            }
        });

        timesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, times);
        timesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sprTime.setAdapter(timesAdapter);
        if (isNew) {
            int indexOfTime = LessonSingle.get(getActivity()).getmLessons().size();
            if (indexOfTime > 6)
                indexOfTime = 6;
            sprTime.setSelection(indexOfTime - 1);
        }
        else{
            sprTime.setSelection(times.indexOf(lessonInfo.getTime()));
        }

        sprTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sprTime.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        typesAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, Types.values());
        typesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sprType.setAdapter(typesAdapter);
        sprType.setSelection(3);

        if (isNew){
            sprType.setSelection(0);
        }
        else{
            int pos = Types.valueOf(lessonInfo.getType()).ordinal();
            sprType.setSelection(pos);
        }
        sprType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sprType.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void checkIsNew(){
        if (isNew) {
            LessonSingle.get(getActivity()).deleteLesson(lessonInfo);
            isNew = false;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.lesson_fragment_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_item_new_lesson:
                LessonSingle.get(getActivity()).deleteLesson(lessonInfo);
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



    public void returnResult(){
        getActivity().setResult(Activity.RESULT_OK, null);
    }

    @Override
    public void onBackPressed() {
        checkIsNew();
        getActivity().finish();
    }
}
