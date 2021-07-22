package com.nineleaps.banking.job;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ReportService {

    @Scheduled(cron = "${cron.schedule.report}")
    @SchedulerLock(name = "exportReportTask", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    public void executeTask() {
        // To assert that the lock is held (prevents misconfiguration errors)
        LockAssert.assertLocked();
        Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println(
                "Scheduler run at: " + localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
