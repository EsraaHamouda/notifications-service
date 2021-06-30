package com.notification.model;

import com.notification.model.enums.NotificationProviderEnum;
import com.notification.model.enums.NotificationStatusEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Notification {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String body;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private NotificationStatusEnum status;

    @Enumerated(EnumType.STRING)
    private NotificationProviderEnum provider;

    private LocalDate creationDate;

    private String processorJobUuid;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Notification notification = new Notification();
        notification.setProvider(this.provider);
        notification.setTitle(this.title);
        notification.setBody(this.body);
        notification.setStatus(this.status);
        return notification;
    }
}
