package com.notification.service;

import com.notification.dao.GroupRepository;
import com.notification.dao.NotificationRepository;
import com.notification.dao.NotificationTemplateRepository;
import com.notification.dao.UserRepository;
import com.notification.dto.NotificationDto;
import com.notification.dto.NotificationLiteDto;
import com.notification.exception.CustomException;
import com.notification.exception.ErrorCode;
import com.notification.model.Notification;
import com.notification.model.NotificationGroup;
import com.notification.model.NotificationTemplate;
import com.notification.model.User;
import com.notification.model.enums.NotificationProviderEnum;
import com.notification.model.enums.NotificationStatusEnum;
import com.notification.model.enums.NotificationTypeEnum;
import com.notification.service.notification.engine.NotificationHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NotificationServiceApplicationTests  extends AbstractTest {

	@Mock
	NotificationRepository notificationRepository;

	@Mock
	NotificationTemplateRepository templateRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	GroupRepository groupRepository;

	@Autowired
	NotificationService notificationService;

	@Mock
	private NotificationHandler notificationHandler;


	private Notification individualNotification = null;

	private Notification individualIngroupNotification = null;
	private List<Notification> individualNotificationList = new ArrayList<>();
	private List<Notification> groupNotificationList = new ArrayList<>();
	private NotificationDto individualNotificationDto = null;
	private NotificationDto groupNotificationDto = null;
	private NotificationLiteDto notificationLiteDto = null;
	private NotificationTemplate notificationTemplate = null;
	private User user1 = null;
	private User user2 = null;
	private User user3 = null;
	private NotificationGroup notificationGroup1 = null;
	private NotificationGroup notificationGroup2 = null;
	private NotificationGroup notificationGroup3 = null;

	private List<User> userListGroup1 = new ArrayList<>();
	private List<User> userListGroup2 = new ArrayList<>();
	private List<User> users = new ArrayList<>();

	private Map<String, String> notificationPlaceHolders_individualNotification = new HashMap<>();

	private Map<String, String> notificationPlaceHolders_groupNotification = new HashMap<>();
	private NotificationDto wrongGroupNotificationDto = null;
	private NotificationDto wrongUserNotificationDto = null;

	@BeforeEach
	public void setup() {
		Throwable exceptionUserIdNotProvided = assertThrows(CustomException.class, () -> {
			throw new CustomException(ErrorCode.UserIdNotProvided);
		});

		Throwable exceptionNotTemplateFound = assertThrows(CustomException.class, () -> {
			throw new CustomException(ErrorCode.NotTemplateFound);
		});
		Throwable exceptionUserNotFound = assertThrows(CustomException.class, () -> {
			throw new CustomException(ErrorCode.UserNotFound);
		});
		Throwable exceptionGroupNotFound = assertThrows(CustomException.class, () -> {
			throw new CustomException(ErrorCode.GroupNotFound);
		});


		Throwable exceptionGroupIdNotProvided = assertThrows(CustomException.class, () -> {
			throw new CustomException(ErrorCode.GroupIdNotProvided);
		});
		Throwable exceptionProviderNotSupported = assertThrows(CustomException.class, () -> {
			throw new CustomException(ErrorCode.ProviderNotSupported);
		});


		notificationPlaceHolders_individualNotification.put("discount_value", "20");
		notificationPlaceHolders_individualNotification.put("num_rides", "4");

		notificationPlaceHolders_groupNotification.put("discount_value", "40");
		notificationPlaceHolders_groupNotification.put("num_rides", "5");
		notificationTemplate = new NotificationTemplate(1, "NEW_PROMO", "Congrats, you got new promo",
				"You got a discount of value discount_value % for num_rides rides",
				"en");

		user1 = new User(1, "Ahmed", "01111111100", new ArrayList<>());
		user2 = new User(2, "Ali", "01111111100", new ArrayList<>());
		user3 = new User(3, "Karim", "01111111100", new ArrayList<>());
		userListGroup1.add(user1);
		userListGroup1.add(user2);
		userListGroup2.add(user3);

		notificationGroup1 = new NotificationGroup(1, "Group_1", userListGroup1);
		notificationGroup2 = new NotificationGroup(2, "Group_2", userListGroup2);
		notificationGroup3 = new NotificationGroup(3, "Group_3", new ArrayList<>());

		individualNotification = new Notification(1, "Congrats, you got new promo", "You got a discount of value 20 %  for 4 rides", user1,
				NotificationStatusEnum.Pending, NotificationProviderEnum.SMS,
				LocalDate.now(), null);

		individualNotificationList.add(individualNotification);

		individualIngroupNotification = new Notification(2, "Congrats, you got new promo", "You got a discount of value 40 %  for 5 rides", user1,
				NotificationStatusEnum.Pending, NotificationProviderEnum.SMS,
				LocalDate.now(), null);

		individualNotificationDto = new NotificationDto("NEW_PROMO", NotificationTypeEnum.Individual, NotificationProviderEnum.SMS,
				null, 1, notificationPlaceHolders_individualNotification, "en");


		groupNotificationDto = new NotificationDto("NEW_PROMO", NotificationTypeEnum.Group, NotificationProviderEnum.SMS,
				1, null, notificationPlaceHolders_individualNotification, "en");

		wrongGroupNotificationDto = new NotificationDto("NEW_PROMO", NotificationTypeEnum.Group, NotificationProviderEnum.SMS,
				null, 1, notificationPlaceHolders_individualNotification, "en");


		wrongUserNotificationDto = new NotificationDto("NEW_PROMO", NotificationTypeEnum.Individual, NotificationProviderEnum.SMS,
				2, null, notificationPlaceHolders_individualNotification, "en");




	}

	@Test
	public void testRightIndividualNotification() throws Exception {

		Mockito.when(templateRepository.findByLanguageAndName("NEW_PROMO", "en")).thenReturn(Optional.of(notificationTemplate));
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user1));
		Mockito.when(notificationRepository.saveAll(individualNotificationList)).thenReturn(individualNotificationList);
		Mockito.when(templateRepository.findByLanguageAndName("NEW_PROMO", "en")).thenReturn(Optional.of(notificationTemplate));


		List<Notification> result = notificationService.sendNotification(individualNotificationDto);

		assertArrayEquals(result.toArray(), individualNotificationList.toArray());


	}
	@Test
	public void testWrongIndividualNotification() throws Exception {

		Mockito.when(templateRepository.findByLanguageAndName("NEW_PROMO", "en")).thenReturn(Optional.of(notificationTemplate));
		Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user1));
		Mockito.when(notificationRepository.saveAll(individualNotificationList)).thenReturn(individualNotificationList);
		Mockito.when(templateRepository.findByLanguageAndName("NEW_PROMO", "en")).thenReturn(Optional.of(notificationTemplate));


		try
		{
			notificationService.sendNotification(wrongUserNotificationDto);
		}
		catch( CustomException e )
		{
			final String msg = "User id not provided";
			assertEquals(msg, e.getMessage());
		}


	}

	@Test
	public void testWrongGroupNotification() throws Exception {
		Mockito.when(templateRepository.findByLanguageAndName("NEW_PROMO", "en")).thenReturn(Optional.of(notificationTemplate));
		Mockito.when(groupRepository.findById(1)).thenReturn(Optional.of(notificationGroup1));
		Mockito.when(notificationRepository.saveAll(groupNotificationList)).thenReturn(groupNotificationList);
		Mockito.when(templateRepository.findByLanguageAndName("NEW_PROMO", "en")).thenReturn(Optional.of(notificationTemplate));
		try
		{
			notificationService.sendNotification(wrongGroupNotificationDto);
		}
		catch( CustomException e )
		{
			final String msg = "Group is not provided";
			assertEquals(msg, e.getMessage());
		}

	}

	@Test
	public void testNonExistingTemplateNotification() throws Exception {
		Mockito.when(templateRepository.findByLanguageAndName("NEW_PROMO2", "en")).thenReturn(Optional.ofNullable(null));
		Mockito.when(groupRepository.findById(1)).thenReturn(Optional.of(notificationGroup1));
		Mockito.when(notificationRepository.saveAll(groupNotificationList)).thenReturn(groupNotificationList);
 		try
		{
			notificationService.sendNotification(groupNotificationDto);
		}
		catch( CustomException e )
		{
			final String msg = "wrong template type is sent";
			assertEquals(msg, e.getMessage());
		}
	}



}