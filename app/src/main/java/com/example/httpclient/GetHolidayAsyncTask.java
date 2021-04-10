package com.example.httpclient;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import com.example.dto.HolidayDto;
import com.example.holidayview.MainActivity;

import java.io.IOException;
import java.util.List;

public class GetHolidayAsyncTask implements Runnable {


    HolidayHttpClient holidayHttpClient;
    Integer year;
    String countryCode;


    public GetHolidayAsyncTask(Integer year, String countryCode, HolidayHttpClient holidayHttpClient) {
        this.year = year;
        this.countryCode = countryCode;
        this.holidayHttpClient = holidayHttpClient;
    }

    @Override
    public void run() {
        try {
            holidayHttpClient.getHolidays(year, countryCode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
