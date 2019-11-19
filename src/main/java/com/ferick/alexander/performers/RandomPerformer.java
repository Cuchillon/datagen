package com.ferick.alexander.performers;

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomPerformer {

    private static final String ALPHABETIC = "alph";
    private static final String ALPHANUMERIC = "alphnum";
    private static final String NUMERIC = "num";
    private static final String DOUBLE = "double";
    private static final String NAME = "name";
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxz";
    private static final String VOWELS = "aeiouy";

    public String perform(final String[] args) {
        if (args.length > 2) {
            throw new IllegalStateException("Wrong number of arguments!");
        }

        String result = "";

        switch (args[0]) {
            case ALPHABETIC:
                result = RandomStringUtils.randomAlphabetic(Integer.parseInt(args[1]));
                break;
            case ALPHANUMERIC:
                result = RandomStringUtils.randomAlphanumeric(Integer.parseInt(args[1]));
                break;
            case NUMERIC:
                result = RandomStringUtils.randomNumeric(Integer.parseInt(args[1]));
                break;
            case DOUBLE:
                result = generateDouble(args[1]);
                break;
            case NAME:
                result = generateName();
                break;
            default:
                throw new IllegalArgumentException("Unknown argument to perform random replacing!");
        }

        return result;
    }

    private String generateDouble(String doubleLength) {
        String[] lengthValues = doubleLength.split("\\.");
        if (lengthValues.length != 2) {
            throw new IllegalArgumentException("Wrong double argument!");
        }
        String intValue = RandomStringUtils.randomNumeric(Integer.parseInt(lengthValues[0]));
        String fractionalValue = RandomStringUtils.randomNumeric(Integer.parseInt(lengthValues[1]));
        return intValue + "." + fractionalValue;
    }

    private String generateName() {
        StringBuilder result = new StringBuilder();

        Random rand = new Random();
        int nameLength = (rand.nextInt(5) + 4);

        for (int i = 0; i < nameLength; i++) {
            if (i == 0) {
                int consonantIndex = rand.nextInt(20);
                Character character = CONSONANTS.charAt(consonantIndex);
                result.append(Character.toUpperCase(character));
            } else if (i % 2 == 0) {
                int consonantIndex = rand.nextInt(20);
                Character character = CONSONANTS.charAt(consonantIndex);
                result.append(character);
            } else {
                int consonantIndex = rand.nextInt(6);
                Character character = VOWELS.charAt(consonantIndex);
                result.append(character);
            }
        }

        return result.toString();
    }
}
