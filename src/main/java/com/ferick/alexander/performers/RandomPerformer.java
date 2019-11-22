package com.ferick.alexander.performers;

import com.ferick.alexander.utils.Config;
import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class RandomPerformer {

    public String perform(final String[] args) {
        if (args.length > 2) {
            throw new IllegalStateException("Wrong number of arguments!");
        }

        String result = "";

        switch (args[0]) {
            case Config.ALPHABETIC:
                result = RandomStringUtils.randomAlphabetic(Integer.parseInt(args[1]));
                break;
            case Config.ALPHANUMERIC:
                result = RandomStringUtils.randomAlphanumeric(Integer.parseInt(args[1]));
                break;
            case Config.NUMERIC:
                result = RandomStringUtils.randomNumeric(Integer.parseInt(args[1]));
                break;
            case Config.DOUBLE:
                result = generateDouble(args[1]);
                break;
            case Config.NAME:
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
                Character character = Config.CONSONANTS.charAt(rand.nextInt(20));
                result.append(Character.toUpperCase(character));
            } else if (i % 2 == 0) {
                Character character = Config.CONSONANTS.charAt(rand.nextInt(20));
                result.append(character);
            } else {
                Character character = Config.VOWELS.charAt(rand.nextInt(6));
                result.append(character);
            }
        }

        return result.toString();
    }
}
