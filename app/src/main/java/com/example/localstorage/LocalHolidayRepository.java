package com.example.localstorage;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.dto.HolidayDto;
import com.example.holidayview.MainActivity;

import java.util.List;

public class LocalHolidayRepository {

    private LocalStorage localStorage;
    private LiveData<List<LocalHoliday>> allLocalHoliday;

    public LocalHolidayRepository(Application application) {
        this.localStorage = Room.databaseBuilder(MainActivity.context, LocalStorage.class, "localholiday").build();;
        allLocalHoliday = localStorage.getHolidayDao().getAllHolidays();
    }

    public LiveData<List<LocalHoliday>> getAllLocalHoliday() {
        return allLocalHoliday;
    }

    public LiveData<LocalHoliday> findLocalHolidayById(String id) {
        return localStorage.getHolidayDao().getHolidayByIdentifier(id);
    }

    public void insert(List<LocalHoliday> localHolidays) {
        LocalStorage.databaseWriteExecutor.execute(() -> {
            localStorage.getHolidayDao().insert(localHolidays);
        });
    }



}
