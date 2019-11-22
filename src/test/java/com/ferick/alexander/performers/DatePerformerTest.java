package com.ferick.alexander.performers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.ferick.alexander.utils.DateConfig;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

public class DatePerformerTest {

    @Test
    public void performPresentDateMillisecTest() {
        DatePerformer performer = new DatePerformer();
        String[] args = new String[]{"now", "millisec"};
        String actualResult = performer.perform(args);
        assertFalse("Date result is empty!", actualResult.isEmpty());
        for (Character character : actualResult.toCharArray()) {
            assertTrue(String.format("Character %s is not a digit!", character),
                    Character.isDigit(character));
        }
    }

    @Test
    public void performPresentDateIsoTest() {
        DatePerformer performer = new DatePerformer();
        String[] args = new String[]{"now", "iso8601"};
        String actualResult = performer.perform(args);
        assertFalse("Date result is empty!", actualResult.isEmpty());
        String regex = "\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(actualResult);
        assertTrue("Date is not matched!", m.find());
    }

    @Test
    public void performDateInPastTest() {
        DatePerformer performer = new DatePerformer();
        long twoHourAgo = new Date().getTime() - (DateConfig.HOUR_MILLISECONDS * 2L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH");
        String formattedPastDate = dateFormat.format(twoHourAgo);

        String[] args = new String[]{"past", "2", "hour", "iso8601"};
        String actualResult = performer.perform(args);
        assertFalse("Date result is empty!", actualResult.isEmpty());
        assertTrue("Date is not matched!", actualResult.startsWith(formattedPastDate));
    }

    @Test
    public void performDateInFutureTest() {
        DatePerformer performer = new DatePerformer();
        long twoDaysForward = new Date().getTime() + (DateConfig.DAY_MILLISECONDS * 2L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedFutureDate = dateFormat.format(twoDaysForward);

        String[] args = new String[]{"future", "2", "day", "iso8601"};
        String actualResult = performer.perform(args);
        assertFalse("Date result is empty!", actualResult.isEmpty());
        assertTrue("Date is not matched!", actualResult.startsWith(formattedFutureDate));
    }

    @Test
    public void performUnknownArgTest() {
        DatePerformer performer = new DatePerformer();
        String[] args = new String[]{"today", "iso8601"};
        String errorMessage = "";
        try {
            performer.perform(args);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Unknown argument to perform date replacing!", errorMessage);
    }

    @Test
    public void performWrongArgNumberTest() {
        DatePerformer performer = new DatePerformer();
        String[] args = new String[]{"past", "2", "hour", "iso8601", "millisec"};
        String errorMessage = "";
        try {
            performer.perform(args);
        } catch (IllegalStateException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Wrong number of arguments!", errorMessage);
    }

    @Test
    public void performWrongNumericArgTest() {
        DatePerformer performer = new DatePerformer();
        String[] args = new String[]{"future", "00:15", "min", "iso8601"};
        String errorMessage = "";
        try {
            performer.perform(args);
        } catch (NumberFormatException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("For input string: \"00:15\"", errorMessage);
    }

    @Test
    public void performWrongTimeTypeArgTest() {
        DatePerformer performer = new DatePerformer();
        String[] args = new String[]{"past", "2", "hours", "millisec"};
        String errorMessage = "";
        try {
            performer.perform(args);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Unknown time type format!", errorMessage);
    }

    @Test
    public void performWrongDateArgTest() {
        DatePerformer performer = new DatePerformer();
        String[] args = new String[]{"past", "2", "hour", "sec"};
        String errorMessage = "";
        try {
            performer.perform(args);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
        assertEquals("Unknown date format!", errorMessage);
    }
}