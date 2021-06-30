DELIMITER $$
CREATE DEFINER=`root`@`localhost` FUNCTION `updateNotificationBatch`(jobUuid VARCHAR(100), batch_size INT) RETURNS int
BEGIN
update notification
             set processor_job_uuid = jobUuid
            where  processor_job_uuid is null AND  status = 'Pending'
            order by creation_date desc limit batch_size;


RETURN 1;
END$$
DELIMITER ;
