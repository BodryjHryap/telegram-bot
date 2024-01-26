package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.Callback;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.System.out;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private NotificationTaskRepository notificationTaskRepository;

    public TelegramBotUpdatesListener(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            String messageText = "Привет! Хочешь поставить напоминание!? Жду от тебя время и дату и о чем ты хочешь себе напомнить.";
            String messageFromChat = update.message().text();
            Long chatId = update.message().chat().id();

            if (messageFromChat.equals("/start")) {
                SendMessage message = new SendMessage(chatId, messageText);
                telegramBot.execute(message);
            }

            Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");
            Matcher matcher = pattern.matcher(messageFromChat);
            String date = null;
            String item = null;

            if (matcher.matches()) {
                date = matcher.group(1);
                item = matcher.group(3);
                System.out.println(date);
                System.out.println(item);
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            if (date != null) {
                LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
                notificationTaskRepository.save(new NotificationTask(chatId, dateTime, item));
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
