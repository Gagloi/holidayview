package com.example.dto;

import androidx.room.ColumnInfo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class HolidayDto {

   public String date;

    String localName;

    String name;

    String countryCode;

    Boolean fixed;

    Boolean global;

    Integer launchYear;

    String type;

}
