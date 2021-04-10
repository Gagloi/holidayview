package com.example.httpclient;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.dto.HolidayDto;
import com.example.holidayview.MainActivity;
import com.example.holidayview.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HolidayHttpClient {


    private OkHttpClient okHttpClient = new OkHttpClient();
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    public static final String GET_HOLIDAY_URL = "https://date.nager.at/Api/v2/PublicHolidays/";

    private Handler handler;

    public HolidayHttpClient(Handler handler) {
        this.handler = handler;
    }

    public void getHolidays(Integer year, String countryCode) throws IOException {
        StringBuilder requestedUrl = new StringBuilder(GET_HOLIDAY_URL).append(year).append("/").append(countryCode);
        Request request = new Request.Builder()
                .url(requestedUrl.toString())
                .build();
        Call call = okHttpClient.newCall(request);
        List<HolidayDto> list = new ArrayList<>();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Gson gson = new Gson();
                Type collectionType = new TypeToken<Collection<HolidayDto>>(){}.getType();
                List<HolidayDto> dto = gson.fromJson(res, collectionType);
                list.addAll(dto);

                handler.sendMessage(Message.obtain(handler, 2, list));

            }
        });
    }

}
