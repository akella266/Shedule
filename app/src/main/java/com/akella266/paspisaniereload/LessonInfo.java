package com.akella266.paspisaniereload;

import java.util.UUID;

/**
 * Created by Akella266 on 06.01.2017.
 */

public class LessonInfo {

    private UUID id;
    private String lesson;
    private String prof;
    private String room;
    private String time;

    public LessonInfo(String lesson, String prof, String room, String time) {
        this.id = UUID.randomUUID();
        this.lesson = lesson;
        this.prof = prof;
        this.room = room;
        this.time = time;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
