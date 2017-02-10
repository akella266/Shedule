package com.akella266.paspisaniereload.Fragments;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaDataSource;

import com.akella266.paspisaniereload.LessonInfo;
import com.akella266.paspisaniereload.database.LessonBaseHelper;
import com.akella266.paspisaniereload.database.LessonCursorWrapper;
import com.akella266.paspisaniereload.database.LessonDBSchema;

import java.util.ArrayList;
import java.util.UUID;

import static com.akella266.paspisaniereload.database.LessonDBSchema.LessonTable.getNAME;
import static com.akella266.paspisaniereload.database.LessonDBSchema.LessonTable.setName;

public class LessonSingle {

    private static LessonSingle sLessonSingle;

    private static Context mContext;
    private static SQLiteDatabase mDataBase;
    private static String day;

    private LessonSingle(Context context, String _day){
        mContext = context.getApplicationContext();
        setDatabase(_day);
    }

    public static  LessonSingle get(Context context){
        return get(context, day);
    }

    public static LessonSingle get(Context context, String _day){
        if(sLessonSingle == null){
            sLessonSingle = new LessonSingle(context, _day);
        }
        else if (!checkDay(_day)) {
            setDatabase(_day);
        }
        return sLessonSingle;
    }

    private static void setDatabase(String _day){
        day = _day;
        setName(day);
        if (mDataBase != null)
            mDataBase.close();
        mDataBase = new LessonBaseHelper(mContext).getWritableDatabase();
    }

    private static boolean checkDay(String _day){
        return day.equals(_day);
    }

    public void addLesson(LessonInfo lesson){
        ContentValues values = getContentValues(lesson);

        mDataBase.insert(getNAME(), null, values);
    }

    public void deleteLesson(LessonInfo info){
        mDataBase.delete(getNAME(), LessonDBSchema.LessonTable.Cols.UUID + " = ?",new String[]{info.getId().toString()});
    }

    public ArrayList<LessonInfo> getmLessons(){

        ArrayList<LessonInfo> lessons = new ArrayList<>();
        LessonCursorWrapper cursorWrapper = queryLesson(null,null);

        try{
            cursorWrapper.moveToFirst();
            while(!cursorWrapper.isAfterLast()){
                lessons.add(cursorWrapper.getLesson());
                cursorWrapper.moveToNext();
            }
        }
        finally{
            cursorWrapper.close();
        }

        return lessons;
    }

    public LessonInfo getLesson(UUID _id){
        LessonCursorWrapper cursor = queryLesson(LessonDBSchema.LessonTable.Cols.UUID + " = ?", new String[]{_id.toString()});

        try{
            if (cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getLesson();
        }
        finally{
            cursor.close();
        }
    }

    public void updateLesson(LessonInfo info){
        String uuid = info.getId().toString();
        ContentValues values = getContentValues(info);

        mDataBase.update(getNAME(), values, LessonDBSchema.LessonTable.Cols.UUID + " = ?", new String[]{uuid});
    }

    private static ContentValues getContentValues(LessonInfo info){
        ContentValues values = new ContentValues();
        values.put(LessonDBSchema.LessonTable.Cols.UUID, info.getId().toString());
        values.put(LessonDBSchema.LessonTable.Cols.LESSON, info.getLesson());
        values.put(LessonDBSchema.LessonTable.Cols.PROF, info.getProf());
        values.put(LessonDBSchema.LessonTable.Cols.ROOM, info.getRoom());
        values.put(LessonDBSchema.LessonTable.Cols.TIME, info.getTime());

        return values;
    }

    private LessonCursorWrapper queryLesson(String whereClause, String[] whereArgs){
        Cursor cursor = mDataBase.query(
                getNAME(),
                null,//all cols
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new LessonCursorWrapper(cursor);
    }
}
