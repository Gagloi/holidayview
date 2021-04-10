package com.example.localstorage;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public abstract class LocalHolidayDao {

    @Query("SELECT * from holiday")
    public abstract LiveData<List<LocalHoliday>> getAllHolidays();

    @Query("SELECT * from holiday where identifier=:identifier")
    public abstract LiveData<LocalHoliday> getHolidayByIdentifier(String identifier);

    @Insert
    public abstract long[] insert(List<LocalHoliday> holidays);

}
