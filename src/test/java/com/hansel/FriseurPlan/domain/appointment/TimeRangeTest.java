package com.hansel.FriseurPlan.domain.appointment;

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
        LocalDateTime createdStartTime = LocalDateTime.of(2023, 10, 1, 10, 0);
        LocalDateTime createdEndTime = LocalDateTime.of(2023, 10, 1, 11, 0);
        TimeRange createdTimeRange = TimeRange.create(createdStartTime, createdEndTime);

        LocalDateTime startTime = LocalDateTime.of(2023, 10, 1, 11, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 10, 1, 12, 0);
        TimeRange timeRange = TimeRange.create(startTime, endTime);

        assert !createdTimeRange.overlaps(timeRange);
    }

    @Test
    void shouldReturnTrueToOverlappingTimeRanges(){
        LocalDateTime createdStartTime = LocalDateTime.of(2023, 10, 1, 10, 0);
        LocalDateTime createdEndTime = LocalDateTime.of(2023, 10, 1, 11, 0);
        TimeRange createdTimeRange = TimeRange.create(createdStartTime, createdEndTime);

        LocalDateTime startTime = LocalDateTime.of(2023, 10, 1, 10, 10);
        LocalDateTime endTime = LocalDateTime.of(2023, 10, 1, 10, 50);
        TimeRange timeRange = TimeRange.create(startTime, endTime);

        assert createdTimeRange.overlaps(timeRange);
    }

}