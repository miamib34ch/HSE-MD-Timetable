package org.hse.timetableforhsefe.api;

import com.google.gson.annotations.SerializedName;

public class TimeZone {
    @SerializedName("current_time")
    private String currentTime;

    public String getCurrentTime() {
        return currentTime;
    }
}
