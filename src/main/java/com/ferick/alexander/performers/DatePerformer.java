package com.ferick.alexander.performers;

import com.ferick.alexander.utils.DateConfig;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePerformer {

    public String perform(final String[] args) {
        if (args.length < 2 || args.length > 4) {
            throw new IllegalStateException("Wrong number of arguments!");
        }

        String result = "";

        switch (args[0]) {
            case DateConfig.PRESENT_DATE:
                result = getPresentDate(args[1]);
                break;
            case DateConfig.DATE_IN_PAST:
                result = getDateInPast(args[1], args[2], args[3]);
                break;
            case DateConfig.DATE_IN_FUTURE:
                result = getDateInFuture(args[1], args[2], args[3]);
                break;
            default:
                throw new IllegalArgumentException("Unknown argument to perform date replacing!");
        }

        return result;
    }

    private String getPresentDate(String format) {
        Date date = new Date();
        return formatDate(format, date);
    }

    private String getDateInPast(String timeQuantity, String timeType, String format) {
        long today = new Date().getTime();
        long ago = calculateTimePeriod(timeQuantity, timeType);
        Date date = new Date(today - ago);
        return formatDate(format, date);
    }

    private String getDateInFuture(String timeQuantity, String timeType, String format) {
        long today = new Date().getTime();
        long ago = calculateTimePeriod(timeQuantity, timeType);
        Date date = new Date(today + ago);
        return formatDate(format, date);
    }

    private String formatDate(String format, Date date) {
        String formattedDate = "";
        switch (format) {
            case DateConfig.MILLISECONDS_FORMAT:
                formattedDate = String.valueOf(date.getTime());
                break;
            case DateConfig.ISO_FORMAT:
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                formattedDate = dateFormat.format(date);
                break;
            default:
                throw new IllegalArgumentException("Unknown date format!");
        }
        return formattedDate;
    }

    private long calculateTimePeriod(String timeQuantity, String timeType) {
        long period;
        long time = Long.parseLong(timeQuantity);
        switch (timeType) {
            case DateConfig.SECONDS_FORMAT:
                period = time * 1000L;
                break;
            case DateConfig.MINUTES_FORMAT:
                period = time * DateConfig.MINUTE_MILLISECONDS;
                break;
            case DateConfig.HOUR_FORMAT:
                period = time * DateConfig.HOUR_MILLISECONDS;
                break;
            case DateConfig.DAY_FORMAT:
                period = time * DateConfig.DAY_MILLISECONDS;
                break;
            default:
                throw new IllegalArgumentException("Unknown time type format!");
        }
        return period;
    }
}
