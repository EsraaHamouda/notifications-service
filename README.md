# notification-service



### System workflow:


![Archticture Workflow](https://github.com/EsraaHamouda/notifications-service/blob/master/src/main/resources/archticure_diagram/archticture.png)


### Implementation Details

(**Assumption**: An administrator should create a notification template first before using the send notification API in order to handle multiple notification languages with dynamic data)

**Notification service consists of 3 major components**
1. **Notification management system (setup phase):**

- Facilitate the process of creating a dynamic notification with multiple languages.
  An administrator can create a certain notification template using the following criteria:
    * Add a template name
    * Add the notification text body and title with many languages(ar, en, fr, ...)
    * if the notification text contains any dynamic information, they should be handled using placeholders which will be
      replaced with actual data every time administator uses this service with different values

  For example : Dear Customer, you got a discount up to discount_percentage for the next num_rides rides till end_date.
  (discount_percentage, num_rides, end_date, ...) are placeholders.

    * Create users.
    * Create groups and add user to them.
    * Get template metadata API by name.

2. **Notification System:**

- The administartor can send individual or group notification by passing a notification object to call notification API.
    * Choose the notification provider either SMS or Push notification.
    * Specifiy the template name.
    * Pass a map of key value pairs of the placeholders in certain template with its actual values.
    * Send the group or user Id based on the type specified.



3. **Notification Processor Job:**

    - As required that the notification provider can **handle limited number of notifications per minute**
      (this limit is configured as a variable). The notification handler component unifies the notifications
      type based on the receiver, in other word if there is a group notification it will be transformed to individual
      notification by fetching the user list per user group and saves these notification instances to database to facilitate the notification job processor to handle the limited capacity. 

    - (Assumption: Since it is not required not to integrate with real notification APIs like  using a publish /subscribe methodology
      to certain topic or websockets to register users to it to broadcast messages. If there is a group notification where
      the group contains a large num of users, that might exceed the limited provider capacity and this will make the service down).
       
    
      So, after saving the notification instances to DB, the job processor will fetch
      a limited amount of notifications and send them to users(Assumption: the provider
      is logging the notification info instead of the actual implementation of sending messages)

4. **Used design patterns**

    - Factory method design pattern.
    - Template design pattern.
    - Strategy design pattern.

**Notification Management APIs**
- API documentation: http://localhost:8080/swagger-ui.html

1- Create user
- Method: POST
- Url: http://localhost:8080/user
- Body:
  {
  "name": "Nour",
  "phone": "0111111000"
  }

2- Create user group
- Method: POST
- Url: http://localhost:8080/group
- Body:
  {
  "name": "group_1",
  "users": [2, 3]
  }


3- Create notification template
- Method: POST
- Url: http://localhost:8080/template
- Body:
    -   {
        "body": "Dear Customer, you got a discount up to discount_percentage for the next num_rides ride till end_date ",
        "language": "en",
        "name": "PROMO",
        "title": "Congrats :D you got a promo "
        }

    - OR
      {
      "body": "عملينا العزيز , لقد حصلت على تخفيض بنسبة discount_percentage  على ال num_rides الرحلات القادمة الى تاريخ end_date ",
      "language": "ar",
      "name": "PROMO",
      "title": "مبرووووووووك "
      }

4- Get template metadata

- Method: GET
- Url:  http://localhost:8080/template/{template_name}/{language}

**Notification  APIs**
- How another microservice would contact this service to send a notification.

1- Send   Notification:

     - Group notification
    
        
            - For group notification: notification type is set to Group and group id should contain value
            - Exceptions are handled for any missing info(notification type, group id , template)
            - Method: POST
            - Url : http://localhost:8080/send-notification
            - Body:
            {
            "notificationTemplate": "PROMO",
            "notificationType": "Group",
            "language": "en",
            "provider": "Push",
            "templatePlaceHolders": {
            "discount_percentage": "10",
            "num_rides": "2",
            "end_date": "2021-06-29 15:50:00"
            },
            "groupId": 1
            }
    
     - Individual Notification
    
             - For Individual notification: notification type is set to Individual and user id should contain value
             - Exceptions are handled for any missing info(notification type, user id , template)
             - Method: POST
             - Url : http://localhost:8080/send-notification
             - Body:
               {
               "notificationTemplate": "PROMO",
               "notificationType": "Individual",
               "language": "en",
               "provider": "Push",
               "templatePlaceHolders": {
               "discount_percentage": "10",
               "num_rides": "2",
               "end_date": "2021-06-29 15:50:00"
               },
               "userId": 1
               }

**Used technologies**
1. Spring boot
2. Lombok
3. Mapstruct
4. Docker
5. Swagger
6. Mysql database


**How to run**
- mvn clean spring-boot:run
- docker-compose up
  
**Future improvements**
1. Integrate Twilio for SMS
2. Setup websockets for realtime notifications
3. Improving dynamic message binding.
4. Handle non-template notification.
