package com.notification.dto;

import com.notification.model.enums.NotificationProviderEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationLiteDto {

    private Integer id;
    private NotificationProviderEnum provider; // SMS, Push
    private Integer userId;
    private String title;
    private String body;


}
