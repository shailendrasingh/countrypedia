package com.onetouchcode.countrypedia;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

import com.bluelinelabs.logansquare.LoganSquare;
import com.bluelinelabs.logansquare.ParameterizedType;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Shailendra on 20/2/2017.
 */
public class RestClient {
    public final static String HTTP_RESPONSE = "http-response";
    public final static String HTTP_RESPONSE_CODE = "http-status";

    protected final static int HTTP_RESPONSE_OK = 200;
    protected final static int HTTP_RESPONSE_FORBIDDEN = 403;
    protected final static int HTTP_RESPONSE_BAD_REQUEST = 400;

    protected static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    protected static final int TIMEOUT_IN_SECONDS = 20 * 60;
    protected Context context;

    public Context getContext() {
        return context;
    }

    public RestClient(Context context) {
        this.context = context;
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }
        // if running on emulator return true always.
        return android.os.Build.MODEL.equals("google_sdk");
    }

    protected static OkHttpClient configureClient() {
        final int TIME = TIMEOUT_IN_SECONDS;
        httpClientBuilder.readTimeout(TIME, TimeUnit.SECONDS);
        httpClientBuilder.writeTimeout(TIME, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(TIME, TimeUnit.SECONDS);
        return httpClientBuilder.build();
    }

    @NonNull
    public HashMap<String, Object> get(@NonNull final String endPoint) {
        return get(endPoint, null);
    }

    public HashMap<String, Object> get(@NonNull final String endPoint, ParameterizedType type) {
        Request request = new Request.Builder().url(endPoint).get().build();
        return execute(request, type);
    }

    public Object get(@NonNull final String endPoint, Class type, Boolean isList) {
        Request request = new Request.Builder().url(endPoint).get().build();
        return execute(request, type, isList);
    }

    protected HashMap<String, Object> execute(@NonNull final Request request, ParameterizedType type) {
        HashMap<String, Object> status = new HashMap<>();
        try {
            if (isOnline()) {
                OkHttpClient client = configureClient();
                // Execute the request and retrieve the response.
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    Object data = (type != null)
                            ? LoganSquare.parse(response.body().byteStream(), type)
                            : response.body().string().trim();

                    status.put(HTTP_RESPONSE, data);
                }
                status.put(HTTP_RESPONSE_CODE, response.code());
            }
        } catch (@NonNull IOException e) {
            e.printStackTrace();
        }
        return status;
    }

    protected Object execute(@NonNull final Request request, Class type, boolean isList) {
        try {
            if (isOnline()) {
                OkHttpClient client = configureClient();
                // Execute the request and retrieve the response.
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    return isList ? LoganSquare.parseList(response.body().byteStream(), type) : LoganSquare.parse(response.body().byteStream(), type);
                    // status = response.body().string().trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getData(@NonNull final HashMap<String, Object> response) {
        return response.get(HTTP_RESPONSE);
    }

    public boolean isSuccessful(final HashMap<String, Object> response) {
        int httpResponseCode = -1;
        if (!response.isEmpty()) {
            httpResponseCode = Integer.parseInt(response.get(HTTP_RESPONSE_CODE).toString());
        }
        return (!response.isEmpty() && httpResponseCode == HTTP_RESPONSE_OK);
    }

    public boolean isBadRequest(final HashMap<String, Object> response) {
        int httpResponseCode = -1;
        if (!response.isEmpty()) {
            httpResponseCode = Integer.parseInt(response.get(HTTP_RESPONSE_CODE).toString());
        }
        return (!response.isEmpty() && httpResponseCode == HTTP_RESPONSE_BAD_REQUEST);
    }

    public boolean isForbidden(final HashMap<String, Object> response) {
        int httpResponseCode = -1;
        if (!response.isEmpty()) {
            httpResponseCode = Integer.parseInt(response.get(HTTP_RESPONSE_CODE).toString());
        }
        return (!response.isEmpty() && httpResponseCode == HTTP_RESPONSE_FORBIDDEN);
    }
}