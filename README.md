****WhatsApp Notification Reader****

This Android application reads WhatsApp messages from notifications and uploads them to Firebase. This can be useful for various purposes such as message backups, analytics, or integration with other services.

**Features**

1. Reads WhatsApp messages from notifications.
2. Uploads the messages to Firebase in real-time.
3. Simple and easy to use interface.
4. Lightweight and efficient.

**Prerequisites**
Before you begin, ensure you have met the following requirements:

1. You have an Android device running Android 5.0 (Lollipop) or higher.
2. You have a basic understanding of Android development.
3. You have Firebase account and project setup.

**Configure Firebase**

1. Go to the Firebase Console.
2. Create a new project (if you don’t already have one).
3. Add an Android app to your Firebase project and follow the setup instructions.
4. Download the google-services.json file and place it in the app directory of your project.

   

**Usage**
1. Grant Notification Access

When you run the app for the first time, it will prompt you to grant notification access. This is necessary for the app to read WhatsApp notifications.

2. Start the App

Launch the app on your device. It will start listening for WhatsApp notifications and upload any received messages to Firebase.

**Code Overview**

**Main Components**
1. NotificationListenerService: This service listens for incoming notifications and extracts WhatsApp messages.
2. FirebaseManager: This class handles the connection to Firebase and uploads the messages.


**Key Files**
1. NotificationListenerService.java: Contains the logic for listening to and processing notifications.
2. FirebaseManager.java: Contains methods for uploading data to Firebase.
3. MainActivity.java: The main activity which initializes the app and handles user interface.

License
This project is licensed under the MIT License - see the LICENSE file for details.

Contact
If you have any questions, feel free to reach out:

Email: suyashm002@gmail.com

***Disclaimer: This app is intended for personal use and should not be used to infringe on privacy. Always seek permission before accessing someone's messages.***
