package com.notification.service.notification.engine;

import com.notification.dao.NotificationRepository;
import com.notification.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@EnableScheduling
@Component
public class NotificationJobProcessing {

    Logger logger = Logger.getLogger(PushNotification.class.getName());

    @Autowired
    private NotificationRepository notificationRepository;

    @Value("${batch.limit}")
    private Integer batchLimit;

    @Scheduled(fixedRate = 5* 60 * 1000)
    @Transactional
    public void processNotification() {


        String jobUuid = UUID.randomUUID().toString();
        logger.info("Notification processor engine started: " + jobUuid);

        notificationRepository.updateNotificationPerJob(jobUuid, batchLimit);

        List<Notification> notificationList = notificationRepository.fetchNotification(jobUuid);

        notificationList.forEach(notification -> {
            NotificationProvider notificationProvider = NotificationFactory.getProviderInstance(notification.getProvider());
            notificationProvider.sendToUser(notification);
        });

        notificationRepository.updateProcessedNotificationStatus(jobUuid);

        //notificationRepository.deletedProcessedNotification(jobUuid);

        logger.info("Notification processor engine ended: " + jobUuid);
    }


}