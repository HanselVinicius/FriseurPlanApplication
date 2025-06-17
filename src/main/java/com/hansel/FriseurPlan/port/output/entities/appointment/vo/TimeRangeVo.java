package com.hansel.FriseurPlan.port.output.entities.appointment.vo;

import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class TimeRangeVo {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public static TimeRangeVo fromTimeRangeDomain(TimeRange timeRange) {
        return new TimeRangeVo(
            timeRange.getStartTime(),
            timeRange.getEndTime()
        );
    }
}
