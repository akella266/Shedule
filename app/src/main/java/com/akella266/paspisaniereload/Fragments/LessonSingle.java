package com.akella266.paspisaniereload.Fragments;

import android.content.Context;

import com.akella266.paspisaniereload.LessonInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Akella266 on 12.01.2017.
 */

public class LessonSingle {

    private static LessonSingle sLessonSingle;
    private ArrayList<LessonInfo> mLessons;

    private LessonSingle(Context context){
        mLessons = new ArrayList<>();
        mLessons.add(new LessonInfo("Мат. анализ", "Задорожнюк М.В.", "2-413", "8.00-9.30"));
        mLessons.add(new LessonInfo("Программирование", "Косинов Г.П.", "2-223", "9.45-11.15"));
        mLessons.add(new LessonInfo("Мат. анализ", "Задорожнюк М.В.", "2-413", "8.00-9.30"));
        mLessons.add(new LessonInfo("Программирование", "Косинов Г.П.", "2-223", "9.45-11.15"));

    }

    public static LessonSingle get(Context context){
        if(sLessonSingle == null){
            sLessonSingle = new LessonSingle(context);
        }
        return sLessonSingle;
    }

    public ArrayList<LessonInfo> getmLessons(){
        return mLessons;
    }

    public LessonInfo getLesson(UUID _id){
        for(LessonInfo lessonInfo : mLessons){
            if (lessonInfo.getId().equals(_id)) return lessonInfo;
        }
        return null;
    }
}
