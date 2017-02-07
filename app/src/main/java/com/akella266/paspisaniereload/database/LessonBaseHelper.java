package com.akella266.paspisaniereload.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Akella266 on 06.02.2017.
 */

public class LessonBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "lessonBase.db";

    public LessonBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + LessonDBSchema.LessonTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                LessonDBSchema.LessonTable.Cols.UUID + ", " +
                LessonDBSchema.LessonTable.Cols.LESSON + ", " +
                LessonDBSchema.LessonTable.Cols.PROF + ", " +
                LessonDBSchema.LessonTable.Cols.ROOM + ", " +
                LessonDBSchema.LessonTable.Cols.TIME + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
