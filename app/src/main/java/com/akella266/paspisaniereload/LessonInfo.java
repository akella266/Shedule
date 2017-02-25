package com.akella266.paspisaniereload;

import com.akella266.paspisaniereload.Enums.TimeWhen;

import java.util.UUID;

public class LessonInfo {

    private UUID id;
    private String lesson;
    private String prof;
    private String room;
    private String time;
    private String type;
    private String when;

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LessonInfo(){
        this(UUID.randomUUID());
    }

    public LessonInfo(UUID id) {
        this.id = id;
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
