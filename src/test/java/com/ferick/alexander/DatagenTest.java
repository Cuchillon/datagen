package com.ferick.alexander;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class DatagenTest {

    @Test
    public void getTest() {
        Datagen datagen = new Datagen();
        Map<String, String> substituteValues = new HashMap<>();

        String template = "{ \"first\": ${-range 1 10 zero-}, \"second\": \"${-random alph 10-}\", \"third\": ${-random double 4.2-}, " +
                "\"fourth\": \"${-date now millisec-}\", \"fifth\": \"${-date paste 30 day isofull-}\", \"sixth\": \"${-set name-}\", " +
                "\"seventh\": \"${-set person.email.value-}\", \"other\": \"hardcore value ${}\" }";

        String expectedResult = "{ \"first\": RANGE110ZERO_1, \"second\": \"RANDOMALPH10_2\", \"third\": RANDOMDOUBLE4.2_3, \"fourth\": \"DATENOWMILLISEC_4\", " +
                "\"fifth\": \"DATEPASTE30DAYISOFULL_5\", \"sixth\": \"SETNAME_6\", \"seventh\": \"SETPERSON.EMAIL.VALUE_7\", \"other\": \"hardcore value ${}\" }";

        String actualResult = datagen.get(template, substituteValues);

        assertEquals(expectedResult, actualResult);
    }
}
