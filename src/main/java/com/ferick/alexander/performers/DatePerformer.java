package com.ferick.alexander.performers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePerformer {

    private static final String PRESENT_DATE = "now";
    private static final String DATE_IN_PAST = "past";
    private static final String DATE_IN_FUTURE = "future";
    private static final String MILLISECONDS_FORMAT = "millisec";
    private static final String ISO_FORMAT = "iso8601";
    private static final String SECONDS_FORMAT = "sec";
    private static final String MINUTES_FORMAT = "min";
    private static final String HOUR_FORMAT = "hour";
    private static final String DAY_FORMAT = "day";
    private static final Long DAY_MILLISECONDS = 86400000L;
    private static final Long HOUR_MILLISECONDS = 3600 * 1000L;
    private static final Long MINUTE_MILLISECONDS = 60 * 1000L;

    public String perform(final String[] args) {
        if (args.length < 2 || args.length > 4) {
            throw new IllegalStateException("Wrong number of arguments!");
        }

        String result = "";

        switch (args[0]) {
            case PRESENT_DATE:
                result = getPresentDate(args[1]);
                break;
            case DATE_IN_PAST:
                result = getDateInPast(args[1], args[2], args[3]);
                break;
            case DATE_IN_FUTURE:
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
            case MILLISECONDS_FORMAT:
                formattedDate = String.valueOf(date.getTime());
                break;
            case ISO_FORMAT:
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
            case SECONDS_FORMAT:
                period = time * 1000L;
                break;
            case MINUTES_FORMAT:
                period = time * MINUTE_MILLISECONDS;
                break;
            case HOUR_FORMAT:
                period = time * HOUR_MILLISECONDS;
                break;
            case DAY_FORMAT:
                period = time * DAY_MILLISECONDS;
                break;
            default:
                throw new IllegalArgumentException("Unknown time type format!");
        }
        return period;
    }
}
