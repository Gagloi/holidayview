package com.example.holidayview;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.localstorage.LocalHoliday;
import com.example.localstorage.LocalHolidayRepository;

import java.util.List;

public class ViewHolidayItem extends AndroidViewModel {

    private LiveData<LocalHoliday> data;

    private LocalHolidayRepository repository;

    public ViewHolidayItem(Application application) {
        super(application);
        repository = new LocalHolidayRepository(application);
    }

    public LiveData<LocalHoliday> getData(String id) {
        return repository.findLocalHolidayById(id);
    }

    public void insert(List<LocalHoliday> localHoliday) {
        repository.insert(localHoliday);
    }
}
