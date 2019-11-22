package com.ferick.alexander.performers;

import java.util.Map;
import java.util.Optional;

public class SetPerformer {

    public String perform(final String[] args, final Map<String, String> substituteValues) {
        if (args.length != 2) {
            throw new IllegalStateException("Wrong number of arguments!");
        } else if (!args[0].equals("set")) {
            throw new IllegalArgumentException("Unknown argument to perform set replacing!");
        }

        Optional<String> result = Optional.ofNullable(substituteValues.get(args[1]));
        return result.orElse("");
    }
}
