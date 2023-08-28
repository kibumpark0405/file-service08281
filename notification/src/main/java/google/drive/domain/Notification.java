package google.drive.domain;

import google.drive.NotificationApplication;
import google.drive.domain.NotificationSent;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Notification_table")
@Data
//<<< DDD / Aggregate Root
public class Notification {

    @Id
    private String notificationType;

    private notificationTypeType notificationTypeType;

    private String user;

    @PostPersist
    public void onPostPersist() {
        NotificationSent notificationSent = new NotificationSent(this);
        notificationSent.publishAfterCommit();
    }

    @PrePersist
    public void onPrePersist() {}

    public static NotificationRepository repository() {
        NotificationRepository notificationRepository = NotificationApplication.applicationContext.getBean(
            NotificationRepository.class
        );
        return notificationRepository;
    }
}
//>>> DDD / Aggregate Root
