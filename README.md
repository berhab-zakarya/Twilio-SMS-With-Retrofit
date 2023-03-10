# Adding Twilio SMS functionality with Retrofit in Android

## Problem

If you are using Twilio SMS functionality in your Android app and encounter an error message that says "No static field INSTANCE of type Lorg/apache/http/conn/ssl/AllowAllHostnameVerifier," then you have come to the right place. 

## Solution

To fix this error, you can follow these steps:

1. Add the following dependencies to your app's build.gradle file:
    ```
    implementation 'com.squareup.okhttp3:okhttp:4.9.1'
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'com.github.hihi-dev:twiliosms:0.1.1'
    ```

2. Create a `TwilioService` class and add the code


Replace `"your_twilio_account_sid"`, `"your_twilio_auth_token"`, and `"your_twilio_phone_number"` with your actual Twilio account

###Prerequisites
Before you begin, you will need:

A Twilio account. You can sign up for a free account [here](https://www.twilio.com/try-twilio)
.
