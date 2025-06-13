package com.hansel.FriseurPlan.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class TimeRangeTest {

    @Test
    void testValidCreation(){
        LocalDateTime startTime = LocalDateTime.of(2023, 10, 1, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 10, 1, 11, 0);

        TimeRange timeRange = TimeRange.create(startTime, endTime);
        assertNotNull(timeRange);
    }

    @Test
    void testCreationWithNullStartTime() {
        LocalDateTime endTime = LocalDateTime.of(2023, 10, 1, 11, 0);
        assertThrows(IllegalArgumentException.class, () -> TimeRange.create(null, endTime));
    }

    @Test
    void shouldReturnFalseToNotOverlappingTimeRanges(){
        LocalDateTime startTime1 = LocalDateTime.of(2023, 10, 1, 10, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2023, 10, 1, 11, 0);
        TimeRange timeRange1 = TimeRange.create(startTime1, endTime1);

        LocalDateTime startTime2 = LocalDateTime.of(2023, 10, 1, 11, 0);
        LocalDateTime endTime2 = LocalDateTime.of(2023, 10, 1, 12, 0);
        TimeRange timeRange2 = TimeRange.create(startTime2, endTime2);

        assert !timeRange1.overlaps(timeRange2);
    }

    @Test
    void shouldReturnTrueToOverlappingTimeRanges(){
        LocalDateTime startTime1 = LocalDateTime.of(2023, 10, 1, 10, 0);
        LocalDateTime endTime1 = LocalDateTime.of(2023, 10, 1, 11, 0);
        TimeRange timeRange1 = TimeRange.create(startTime1, endTime1);

        LocalDateTime startTime2 = LocalDateTime.of(2023, 10, 1, 10, 10);
        LocalDateTime endTime2 = LocalDateTime.of(2023, 10, 1, 10, 50);
        TimeRange timeRange2 = TimeRange.create(startTime2, endTime2);

        assert timeRange1.overlaps(timeRange2);
    }

}