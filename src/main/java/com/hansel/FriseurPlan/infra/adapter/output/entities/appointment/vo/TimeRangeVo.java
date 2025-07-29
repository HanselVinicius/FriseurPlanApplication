package com.hansel.FriseurPlan.infra.adapter.output.entities.appointment.vo;

import com.hansel.FriseurPlan.core.domain.appointment.TimeRange;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TimeRangeVo {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public static TimeRangeVo fromTimeRangeDomain(TimeRange timeRange) {
        return new TimeRangeVo(
            timeRange.getStartTime(),
            timeRange.getEndTime()
        );
    }

    public TimeRange toTimeRangeDomain() {
        return TimeRange.create(startTime, endTime);
    }
}
