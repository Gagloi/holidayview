package com.example.holidayview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.dto.HolidayDto;
import com.example.httpclient.GetHolidayAsyncTask;
import com.example.httpclient.GetHolidayHandlerThread;
import com.example.httpclient.HolidayHttpClient;
import com.example.localstorage.LocalHoliday;
import com.example.localstorage.LocalStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static Context context;

    private ViewHoliday viewHoliday;

    private EditText countryCodeEditText;
    private EditText yearEditText;

    final ViewHolidayAdapter adapter = new ViewHolidayAdapter(new ViewHolidayAdapter.WordDiff());


    //public static Handler mHandler = new Handler(Looper.getMainLooper());
    public Handler mainHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
                    Log.d("dsd", msg.toString());
                    //сохранить все дтохи в базу
                    List<HolidayDto> list = (List<HolidayDto>) msg.obj;
                    List<LocalHoliday> lst = new ArrayList<>();
                    for (HolidayDto item: list) {
                        lst.add(LocalHoliday.builder()
                                .identifier(item.getName())
                                .name(item.getName())
                                .countryCode(item.getCountryCode())
                                .date(item.getDate())
                                .fixed(item.getFixed())
                                .global(item.getGlobal())
                                .launchYear(item.getLaunchYear())
                                .localName(item.getLocalName())
                                .type(item.getType()).build());
                    }
                    insertToDb(lst);
                    adapter.submitList(lst);
                    break;
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        context = this;

        HolidayHttpClient holidayHttpClient = new HolidayHttpClient(mainHandler);
        yearEditText = this.findViewById(R.id.year);
        countryCodeEditText = this.findViewById(R.id.countryCode);

        Button btn = this.findViewById(R.id.getHoliday);

        RecyclerView recyclerView = findViewById(R.id.holidayList);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewHoliday = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(ViewHoliday.class);

        viewHoliday.getData().observe(this, adapter::submitList);

        btn.setOnClickListener(v -> {
                GetHolidayHandlerThread handlerThread = new GetHolidayHandlerThread("thread");
                handlerThread.start();
                Looper looper = handlerThread.getLooper();
                Handler handler = new Handler(looper);
                handler.post(new GetHolidayAsyncTask(Integer.valueOf(yearEditText.getText().toString()), countryCodeEditText.getText().toString(), holidayHttpClient));
        });
    }

    private void insertToDb(List<LocalHoliday> localHolidays) {
        viewHoliday.insert(localHolidays);
    }

}
