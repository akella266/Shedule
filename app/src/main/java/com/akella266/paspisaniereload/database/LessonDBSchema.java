package com.akella266.paspisaniereload.database;

public class LessonDBSchema {

    public static final class LessonTable {
        public static final String NAME = "lessons";


        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String LESSON = "lesson";
            public static final String PROF = "prof";
            public static final String ROOM = "room";
            public static final String TIME = "time";

        }
    }
}
