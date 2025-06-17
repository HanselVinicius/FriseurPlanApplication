package com.hansel.FriseurPlan.core.domain.appointment;

import java.time.LocalDateTime;

public class TimeRange {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    private TimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static TimeRange create(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null) {
            throw new IllegalArgumentException("Start time and end time must not be null");
        }
        if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time");
        }
        return new TimeRange(startTime, endTime);
    }

    public boolean overlaps(TimeRange other) {
        return this.startTime.isBefore(other.endTime) && this.endTime.isAfter(other.startTime);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TimeRange that = (TimeRange) obj;
        return startTime.equals(that.startTime) && endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        return startTime.hashCode() ^ endTime.hashCode();
    }
}
