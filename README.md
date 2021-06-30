# notification-service



### System workflow:


![Archticture Workflow](https://github.com/EsraaHamouda/notification-svc/blob/main/src/main/resources/archticure_diagram/archticture.png)


### Implementation Details

(**Assumption**: An administrator should create a notification template first before use send notification API in order to hanlde multiple notification languages with dynamic data)

**Notification service is consists of 3 major components**
1. **Notification management system (setup phase):**

- facilitate the process of creating dynamic notification with multiple languages.
  So, aminstrator can create a certain notification template using the following criteria:
    *   Add a template name
    * Add the notification text body and title with many languages(ar, en, fr, ...)
    * if notification text contains any dynamic information , they should be handled using placeholders which will
      placed with actual data every time administator uses this service with different values

  For example : Dear Customer, you got a discount up to discount_percentage for the next num_rides rides till end_date
  (discount_percentage, num_rides, end_date, ...) -> placeholders

    * Create users
    * Create groups and add user to them
    * Get template metadata API by name

2. **Notification System:**

- Administartor can send Individual or group notification by passing a notification object to send notification API
    * Specify the notification provider SMS or Push notification
    * Specifiy the template name
    * Pass a map (key value pairs) of the placeholders in certain template with its actual values.
    * Send the group or user Id based on the type specified



3. **Notification processor job:**

    - As required that the notification provider can **handle limited number of notification per minute**
      (this limit is configured as a variable). Notification Handler component unified the notifications
      type based on the receiver,  in other word if there is a group notification it will be transformed to individual
      notification by fetching the user list per user group and save these notification instances to database.

    - in order to facilitate the notification job processor to handle the limited capacity(Assumption: as per it is not
      required not to integrate with real notification APIs like  using a publish /subscribe methodology
      to certain topic or websockets to register users to it to broadcast messages.
    -  If there is a group notification where the group contains large num of users that may exceed
       the limited provider capacity and this will make the service down).
    - So, After saving the notification instances to DB job processor will fetch
      limited amount of notification and send them to users(Assumption: provider
      just logging the notification info instead of the actual implementation of sending messages)

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
