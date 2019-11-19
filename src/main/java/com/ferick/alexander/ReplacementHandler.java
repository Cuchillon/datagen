package com.ferick.alexander;

import com.ferick.alexander.performers.DatePerformer;
import com.ferick.alexander.performers.RandomPerformer;
import com.ferick.alexander.performers.RangePerformer;
import com.ferick.alexander.performers.SetPerformer;

import java.util.Arrays;
import java.util.Map;

public class ReplacementHandler {

    private static final String RANGE = "range";
    private static final String RANDOM = "random";
    private static final String DATE = "date";
    private static final String SET = "set";

    public String getReplacement(final String precept, final Map<String, String> substituteValues) {
        String[] preceptArray = precept.split(" ");
        String method = preceptArray[0].toLowerCase();
        String[] args = Arrays.copyOfRange(preceptArray, 1, preceptArray.length);

        String result = "";

        switch (method) {
            case RANGE:
                result = new RangePerformer().perform(args);
                break;
            case RANDOM:
                result = new RandomPerformer().perform(args);
                break;
            case DATE:
                result = new DatePerformer().perform(args);
                break;
            case SET:
                result = new SetPerformer().perform(args, substituteValues);
                break;
            default:
                throw new IllegalStateException("Unknown method to perform replacing!");
        }

        return result;
    }
}
