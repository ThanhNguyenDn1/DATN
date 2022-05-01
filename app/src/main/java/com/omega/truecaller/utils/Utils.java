package com.omega.truecaller.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utils {

    public static String covertTimestampToHours(long timestamp) {
        Calendar calendarMess = Calendar.getInstance();
        Calendar calendarNow = Calendar.getInstance();

        calendarMess.setTimeInMillis(timestamp);
        calendarNow.setTimeInMillis(System.currentTimeMillis());
        if (calendarMess.get(Calendar.YEAR) == calendarNow.get(Calendar.YEAR)) {
            if (calendarMess.get(Calendar.MONTH) == calendarNow.get(Calendar.MONTH)) {
                if (calendarMess.get(Calendar.WEEK_OF_MONTH) == calendarNow.get(Calendar.WEEK_OF_MONTH)) {
                    if (calendarMess.get(Calendar.DAY_OF_MONTH) == calendarNow.get(Calendar.DAY_OF_MONTH)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                        String AM_PM;
                        if (calendarMess.get(Calendar.AM_PM) == Calendar.AM) {
                            AM_PM = "AM";
                        } else
                            AM_PM = "PM";
                        return sdf.format(calendarMess.getTime()) + " " + AM_PM;
                    } else {
                        SimpleDateFormat format = new SimpleDateFormat("EEE");
                        return format.format(calendarMess.getTime());
                    }

                } else {
                    SimpleDateFormat format = new SimpleDateFormat("EEE dd");
                    return format.format(calendarMess.getTime());
                }
            } else {
                SimpleDateFormat format = new SimpleDateFormat("dd MMM");
                return format.format(calendarMess.getTime());
            }
        } else {
            SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
            return format.format(calendarMess.getTime());
        }
    }
}
