package com.riksasuviana.mvvm_google.room;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * Created by riksa on 25/11/2017.
 */

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date){
        return date == null ? null : date.getTime();
    }
}
