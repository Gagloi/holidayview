package com.example.holidayview;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.localstorage.LocalHoliday;
import com.example.localstorage.LocalHolidayRepository;
import com.example.localstorage.LocalStorage;

import java.util.List;

import okhttp3.Callback;

public class ViewHoliday extends AndroidViewModel {

    private final LiveData<List<LocalHoliday>> data;

    private LocalHolidayRepository repository;

    public ViewHoliday(Application application) {
        super(application);
        repository = new LocalHolidayRepository(application);
        data = repository.getAllLocalHoliday();
    }

    public LiveData<List<LocalHoliday>> getData() {
        return data;
    }

    public void insert(List<LocalHoliday> localHoliday) {
        repository.insert(localHoliday);
    }
}
