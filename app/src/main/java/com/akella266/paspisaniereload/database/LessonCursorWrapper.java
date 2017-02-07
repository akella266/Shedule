package com.akella266.paspisaniereload.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.akella266.paspisaniereload.LessonInfo;

import java.util.UUID;

import static com.akella266.paspisaniereload.database.LessonDBSchema.*;

/**
 * Created by Akella266 on 07.02.2017.
 */

public class LessonCursorWrapper extends CursorWrapper {

    public LessonCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public LessonInfo getLesson(){
        String uuid = getString(getColumnIndex(LessonTable.Cols.UUID));
        String lesson = getString(getColumnIndex(LessonTable.Cols.LESSON));
        String prof = getString(getColumnIndex(LessonTable.Cols.PROF));
        String room = getString(getColumnIndex(LessonTable.Cols.ROOM));
        String time = getString(getColumnIndex(LessonTable.Cols.TIME));


        LessonInfo info = new LessonInfo(UUID.fromString(uuid));
        info.setLesson(lesson);
        info.setProf(prof);
        info.setRoom(room);
        info.setTime(time);

        return info;
    }
}
