package com.vnptit.vnpthis.utils;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static Time convertStringToTime(String s) {
        try {
            return new Time(sdf.parse(s).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
