package pro.sky.telegrambot.scheduler;

import ch.qos.logback.core.util.TimeUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.sky.telegrambot.service.NotificationTaskService;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.chrono.ChronoPeriod;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;

@Component
public class Scheduler {
    private final NotificationTaskService notificationTaskService;

    public Scheduler(NotificationTaskService notificationTaskService) {
        this.notificationTaskService = notificationTaskService;
    }

    @Scheduled(cron = "0 0/1 * * * *")
    public void test() {
        notificationTaskService.sendNotifications(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
    }
}
