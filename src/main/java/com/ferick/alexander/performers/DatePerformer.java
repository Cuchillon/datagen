package com.ferick.alexander.performers;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePerformer {

    private static final String PRESENT_DATE = "now";
    private static final String DATE_IN_PAST = "past";
    private static final String DATE_IN_FUTURE = "future";
    private static final String MILLISECONDS_FORMAT = "millisec";
    private static final String ISO_FORMAT = "iso8601";

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
                //
                break;
            case DATE_IN_FUTURE:
                //
                break;
            default:
                throw new IllegalArgumentException("Unknown argument to perform date replacing!");
        }

        return result;
    }

    private String getPresentDate(String format) {
        Date date = new Date();
        return getFormattedDate(format, date);
    }

    private String getFormattedDate(String format, Date date) {
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
}
