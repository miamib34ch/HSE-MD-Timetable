package org.hse.timetableforhsefe.schedule;

import java.util.Date;
import java.util.Locale;

public class ScheduleItem implements Comparable<ScheduleItem> {
    private String start;
    private String end;
    private String type;
    private String name;
    private String place;
    private String teacher;
    private Date date;

    public String getLessonStart() {
        return start;
    }

    public void setStart(String lessonStart) {
        this.start = lessonStart;
    }

    public String getLessonEnd() {
        return end;
    }

    public void setEnd(String lessonEnd) {
        this.end = lessonEnd;
    }

    public String getLessonType() {
        return type;
    }

    public void setType(String lessonType) {
        lessonType = lessonType.toUpperCase(Locale.ROOT);
        this.type = lessonType;
    }

    public String getLessonName() {
        return name;
    }

    public void setName(String lessonName) {
        this.name = lessonName;
    }

    public String getLessonPlace() {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int compareTo(ScheduleItem o) {
        return getDate().compareTo(o.getDate());
    }
}
