package com.example.localstorage;

import android.text.BoringLayout;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity(tableName = "holiday", indices = {@Index("identifier")})
public class LocalHoliday {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "identifier")
    String identifier;

    @ColumnInfo(name = "date")
    String date;

    @ColumnInfo(name = "local_name")
    String localName;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "country_code")
    String countryCode;

    @ColumnInfo(name = "fixed")
    Boolean fixed;

    @ColumnInfo(name = "global")
    Boolean global;

    @ColumnInfo(name = "launch_year")
    Integer launchYear;

    @ColumnInfo(name = "type")
    String type;


}
