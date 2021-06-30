package com.notification.service.notification.engine;

import com.notification.dao.GroupRepository;
import com.notification.dao.NotificationRepository;
import com.notification.dao.NotificationTemplateRepository;
import com.notification.dao.UserRepository;
import com.notification.dto.NotificationDto;
import com.notification.exception.CustomException;
import com.notification.exception.ErrorCode;
import com.notification.model.Notification;
import com.notification.model.NotificationGroup;
import com.notification.model.NotificationTemplate;
import com.notification.model.User;
import com.notification.model.enums.NotificationStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class NotificationHandler {


    @Autowired
    NotificationTemplateRepository templateRepository;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    NotificationRepository notificationRepository;

    private NotificationTemplate fetchNotificationTemplate(NotificationDto notificationDto) {

        Optional<NotificationTemplate> template = templateRepository.findByLanguageAndName(notificationDto.getLanguage(),
                notificationDto.getNotificationTemplate());

        if (template.isPresent()) return template.get();

        throw new CustomException(ErrorCode.NotTemplateFound);

    }

    private Notification constructMessage(NotificationTemplate template, NotificationDto notificationDto) {

        String templateBody = template.getBody();
        if (notificationDto.getTemplatePlaceHolders() != null)
            for (Map.Entry<String, String> entry : notificationDto.getTemplatePlaceHolders().entrySet()) {
                templateBody = templateBody.replaceAll(entry.getKey(), entry.getValue());
            }

        Notification notification = new Notification();
        notification.setTitle(template.getTitle());
        notification.setBody(templateBody);
        notification.setProvider(notificationDto.getProvider());
        notification.setCreationDate(LocalDate.now());
        notification.setStatus(NotificationStatusEnum.Pending);

        return notification;
    }

    List<User> getNotificationReceivers(NotificationDto notificationDto) {
        List<User> result = new ArrayList<>();
        switch (notificationDto.getNotificationType()) {
            case Individual:
                if (notificationDto.getUserId() == null) throw new CustomException(ErrorCode.UserIdNotProvided);

                Optional<User> user = userRepository.findById(notificationDto.getUserId());
                if (user.isPresent())
                    result.add(user.get());

                else
                    throw new CustomException(ErrorCode.UserNotFound);
                break;
            case Group:
                if (notificationDto.getGroupId() == null) throw new CustomException(ErrorCode.GroupIdNotProvided);

                Optional<NotificationGroup> notificationGroup = groupRepository.findById(notificationDto.getGroupId());
                if (notificationGroup.isPresent()) {
                    return notificationGroup.get().getUsers();
                } else
                    throw new CustomException(ErrorCode.GroupNotFound);
        }

        return result;

    }

    List<Notification> saveNotification(List<User> receivers, Notification notificationInstance) {


        List<Notification> notificationList = new ArrayList<>();
        receivers.forEach(user -> {

            try {
                Notification tmpNotification = (Notification) notificationInstance.clone();
                tmpNotification.setUser(user);
                notificationList.add(tmpNotification);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        notificationRepository.saveAll(notificationList);

        return notificationList;
    }


    public final List<Notification> processNotification(NotificationDto notificationDto) {


        NotificationTemplate template = fetchNotificationTemplate(notificationDto);

        Notification notificationInstance = constructMessage(template, notificationDto);

        List<User> receivers = getNotificationReceivers(notificationDto);

        return saveNotification(receivers, notificationInstance);


    }
}
