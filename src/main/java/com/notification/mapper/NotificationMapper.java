package com.notification.mapper;

import com.notification.dto.NotificationLiteDto;
import com.notification.model.Notification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(    componentModel = "spring"
)
public interface NotificationMapper {
    @Mapping(source="user.id", target="userId")

    NotificationLiteDto map(Notification entity);
    List<NotificationLiteDto> mapList(List<Notification> entity);

}