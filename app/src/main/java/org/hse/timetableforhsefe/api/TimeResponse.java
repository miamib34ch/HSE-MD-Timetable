package org.hse.timetableforhsefe.api;

import com.google.gson.annotations.SerializedName;

public class TimeResponse {
    @SerializedName("time_zone")
    private TimeZone timeZone;

    public TimeZone getTimeZone() {
        return timeZone;
    }
}
