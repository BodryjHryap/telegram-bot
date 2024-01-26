package pro.sky.telegrambot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    private Long chatId;
    private LocalDateTime notificationDate;
    private String notificationText;

    public NotificationTask() {

    }

    public NotificationTask(Long chatId, LocalDateTime notificationDate, String notificationText) {
        this.chatId = chatId;
        this.notificationDate = notificationDate;
        this.notificationText = notificationText;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public LocalDateTime getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(LocalDateTime notificationDate) {
        this.notificationDate = notificationDate;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return Objects.equals(notificationId, that.notificationId) && Objects.equals(chatId, that.chatId) && Objects.equals(notificationDate, that.notificationDate) && Objects.equals(notificationText, that.notificationText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificationId, chatId, notificationDate, notificationText);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "notificationId=" + notificationId +
                ", chatId=" + chatId +
                ", notificationDate=" + notificationDate +
                ", notificationText='" + notificationText + '\'' +
                '}';
    }
}
