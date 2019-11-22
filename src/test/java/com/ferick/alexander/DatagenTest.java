package com.ferick.alexander;

import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class DatagenTest {

    @Test
    public void getTest() {
        Datagen datagen = new Datagen();
        Map<String, String> substituteValues = new HashMap<>();
        substituteValues.put("name", "value");

        String template = "{ \"first\": ${-random name-}, \"second\": \"${-random alph 10-}\", \"third\": ${-random double 4.2-}, " +
                "\"fourth\": \"${-date now millisec-}\", \"fifth\": \"${-date past 30 day iso8601-}\", \"sixth\": \"${-set name-}\", " +
                "\"seventh\": \"${-random num 7-}\", \"other\": \"hardcore value\" }";

        String actualResult = datagen.get(template, substituteValues);

        assertFalse(actualResult.contains("$") || actualResult.contains("{-") || actualResult.contains("-}"));
    }
}
