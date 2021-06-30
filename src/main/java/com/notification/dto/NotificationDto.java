package com.notification.dto;

import com.notification.model.enums.NotificationProviderEnum;
import com.notification.model.enums.NotificationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {



    private String notificationTemplate;
    private NotificationTypeEnum notificationType; // group , individual
    private NotificationProviderEnum provider; // SMS, Push
    private Integer groupId;
    private Integer userId;
    private Map<String , String> templatePlaceHolders;
    private String language;



}
