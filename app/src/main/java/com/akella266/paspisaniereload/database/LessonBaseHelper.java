package com.akella266.paspisaniereload.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.akella266.paspisaniereload.Enums.Days;
import com.akella266.paspisaniereload.Enums.TimeWhen;

public class LessonBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 3;
    private static final String DATABASE_NAME = "lessonBase.db";

    public LessonBaseHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for(Days day : Days.values()) {
            sqLiteDatabase.execSQL("create table " + day.toString() + "(" +
                    "_id integer primary key autoincrement, " +
                    LessonDBSchema.LessonTable.Cols.UUID + ", " +
                    LessonDBSchema.LessonTable.Cols.LESSON + ", " +
                    LessonDBSchema.LessonTable.Cols.PROF + ", " +
                    LessonDBSchema.LessonTable.Cols.ROOM + ", " +
                    LessonDBSchema.LessonTable.Cols.TIME + ", " +
                    LessonDBSchema.LessonTable.Cols.TYPE + "); "
            //        LessonDBSchema.LessonTable.Cols.WHEN + ");"
            );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        if (i < 20 || i1 < 20) {
//            for (Days day : Days.values()) {
//                sqLiteDatabase.execSQL("ALTER TABLE " + day.toString() +
//                        " ADD COLUMN " + LessonDBSchema.LessonTable.Cols.WHEN + " CHAR;");
//                sqLiteDatabase.execSQL("insert into " + day.toString() + " ( " +
//                        LessonDBSchema.LessonTable.Cols.WHEN + " ) values " + "(\'" +
//                        TimeWhen.EveryWeek.toString() + "\')"
//                );
//            }
//        }
    }
}
