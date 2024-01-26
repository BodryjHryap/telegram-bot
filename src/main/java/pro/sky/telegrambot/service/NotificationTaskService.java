package pro.sky.telegrambot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationTaskService {

    private final NotificationTaskRepository notificationRepository;
    private final TelegramBot telegramBot;

    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository, TelegramBot telegramBot) {
        this.notificationRepository = notificationTaskRepository;
        this.telegramBot = telegramBot;
    }

    public void sendNotifications(LocalDateTime dateTime) {
        List<NotificationTask> tasks = notificationRepository.findAllByNotificationDate(dateTime);
        tasks.forEach(t -> {
            telegramBot.execute(new SendMessage(t.getChatId(), t.getNotificationText()));
        });
    }
}
