package com.ferick.alexander;

import com.ferick.alexander.performers.DatePerformer;
import com.ferick.alexander.performers.RandomPerformer;
import com.ferick.alexander.performers.RangePerformer;
import com.ferick.alexander.performers.SetPerformer;

import java.util.Arrays;
import java.util.Map;

public class ReplacementHandler {

    public String getReplacement(final String precept, final Map<String, String> substituteValues) {
        String[] preceptArray = precept.split(" ");
        String method = preceptArray[0].toLowerCase();
        String[] args = Arrays.copyOfRange(preceptArray, 1, preceptArray.length);

        String result = "";

        switch (method) {
            case "range":
                result = new RangePerformer().perform(args);
                break;
            case "random":
                result = new RandomPerformer().perform(args);
                break;
            case "date":
                result = new DatePerformer().perform(args);
                break;
            case "set":
                result = new SetPerformer().perform(args, substituteValues);
                break;
            default:
                throw new IllegalStateException("Unknown method to perform replacing!");
        }

        return result;
    }
}
