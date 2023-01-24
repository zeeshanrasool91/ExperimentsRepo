package com.example.myapplication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class BeforeJava8DateTimeZoneConversion {

    public static void convertToRequiredTimeZone(String pattern,Date currentTime,String requiredTimZone) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern, Locale.ENGLISH);
        // Date time to UTC
        // Creating UTC timezone
        TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");
        // setting UTC timezone to formatter.
        dateFormatter.setTimeZone(utcTimeZone);
        // converting Base to UTC
        String utcTime = dateFormatter.format(currentTime);
        System.out.println("PST time : " + utcTime);
        // Date time to GST - Dubai Gulf
        // Creating GST timezone
        TimeZone requiredTimeZone = TimeZone.getTimeZone(requiredTimZone);
        // setting pst timezone to formatter.
        dateFormatter.setTimeZone(requiredTimeZone);
        // converting IST to PST
        String requiredTime = dateFormatter.format(currentTime);
        System.out.println("GST time : " + requiredTime);

        try {
            SimpleDateFormat baseDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
            System.out.println("GST time232 : " + baseDateFormat.parse(requiredTime));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }
}