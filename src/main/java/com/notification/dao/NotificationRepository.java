package com.notification.dao;

import com.notification.model.Notification;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Integer> {

    @Query(value = " SELECT updateNotificationBatch(:jobUuid, :batchLimit);"
             , nativeQuery = true)
    void updateNotificationPerJob(@Param("jobUuid") String jobUuid, @Param("batchLimit") int batchLimit);

    @Query(value = "select * " +
            "from notification  " +
            "where processor_job_uuid = :jobUuid ", nativeQuery = true)
    List<Notification> fetchNotification(@Param("jobUuid") String jobUuid);

    @Modifying
    @Query(value = "delete from  notification where processor_job_uuid = :jobUuid ", nativeQuery = true)
    void deletedProcessedNotification(@Param("jobUuid") String jobUuid);

    @Modifying
    @Query(value = "update notification set status = 'Processed' where processor_job_uuid = :jobUuid ;", nativeQuery = true)
    void updateProcessedNotificationStatus(@Param("jobUuid") String jobUuid);
}