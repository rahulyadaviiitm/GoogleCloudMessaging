# GoogleCloudMessaging
The application module stands for cloud messaging which basically 
used in sendindg a push notification to a individual moibile or a group of user.

Aapplication which are based on push notification-

1. weather broadcasting or
specific user's group which subscribed for the same topic

2. e-commerce Application to send a offer or deal message to all users. etc.

Basic idea how it works-

1.GCM server generate a unique token for each device and return it to developer.

2.Developer send the token to the application server.

3.Whenever application server get a command for send push notification along a meggasge and a token.

4.Now App server send the query to Gcm Server with message data and token of a particualr device or all.

5.Gcm server send the message to the device/s and message get displayed at the user side.
