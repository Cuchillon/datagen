package com.ferick.alexander.performers;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomPerformer {

    public String perform(final String[] args) {
        if (args.length > 2) {
            throw new IllegalStateException("Wrong number of arguments!");
        }

        String result = "";

        switch (args[0]) {
            case "alph":
                result = RandomStringUtils.randomAlphabetic(Integer.parseInt(args[1]));
                break;
            case "alphnum":
                result = RandomStringUtils.randomAlphanumeric(Integer.parseInt(args[1]));
                break;
            case "num":
                result = RandomStringUtils.randomNumeric(Integer.parseInt(args[1]));
                break;
            case "double":
                result = generateDouble(args[1]);
                break;
        }

        return result;
    }

    private String generateDouble(String doubleLength) {
        String[] lengthValues = doubleLength.split(".");
        String intValue = RandomStringUtils.randomNumeric(Integer.parseInt(lengthValues[0]));
        String fractionalValue = RandomStringUtils.randomNumeric(Integer.parseInt(lengthValues[1]));
        return intValue + "." + fractionalValue;
    }
}
