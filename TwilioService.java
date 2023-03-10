The Problem : No static field INSTANCE of type Lorg/apache/http/conn/ssl/AllowAllHostnameVerifier
The Soulution : 
First
	implementation 'com.squareup.okhttp3:okhttp:4.9.1'
	implementation 'com.squareup.retrofit2:retrofit:2.9.0'
	implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
	implementation 'com.github.hihi-dev:twiliosms:0.1.1'
Second
package com.omar.facerecognitionapps.utils;

import android.util.Log;
import com.hihi.twiliosms.TwilioMessage;
import com.omar.facerecognitionapps.helpers.TwilioApi;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;



public class TwilioService {
	public static String ACCOUNT_SID ="", AUTH_TOKEN = "";
    public static void sendSms(String phoneNumber,String message){
        OkHttpClient client = new OkHttpClient.Builder().build();
        client = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Authorization", Credentials.basic(ACCOUNT_SID, AUTH_TOKEN))
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.twilio.com")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TwilioApi twilioApi = retrofit.create(TwilioApi.class);
        Call<TwilioMessage> call = twilioApi.sendSms(
                ACCOUNT_SID,
                "+"+phoneNumber,
                "+15074073493",
                message
        );
        call.enqueue(new Callback<TwilioMessage>() {
            @Override
            public void onResponse(Call<TwilioMessage> call, Response<TwilioMessage> response) {
                Log.d("TWILIO API", "onResponse: " + response);
            }

            @Override
            public void onFailure(Call<TwilioMessage> call, Throwable t) {
                Log.d("TWILIO API", "onResponse: " + t.getMessage());

            }
        });

    }
	public interface TwilioApi {
    @FormUrlEncoded
    @POST("/2010-04-01/Accounts/{accountSid}/Messages.json")
    Call<TwilioMessage> sendSms(
            @Path("accountSid") String accountSid,
            @Field("To") String to,
            @Field("From") String from,
            @Field("Body") String body
    );
}
}
