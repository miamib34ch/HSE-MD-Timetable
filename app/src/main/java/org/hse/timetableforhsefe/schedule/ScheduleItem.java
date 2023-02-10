package org.hse.timetableforhsefe.schedule;

import java.util.Locale;

public class ScheduleItem{

    private String start;
    private String end;
    private String type;
    private String name;
    private String place;
    private String teacher;

    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        type = type.toUpperCase(Locale.ROOT);
        this.type = type;
    }

    public String getName() {
        return name;
    }
    public void setName(String lessonName) {
        this.name = lessonName;
    }

    public String getPlace() {
        return place;
    }
    public void setPlace(String lessonPlace) {
        this.place = lessonPlace;
    }

    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
