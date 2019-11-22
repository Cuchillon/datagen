package com.ferick.alexander.performers;

import java.util.Map;
import java.util.Optional;

public class SetPerformer {

    public String perform(final String[] args, final Map<String, String> substituteValues) {
        if (args.length > 1) {
            throw new IllegalStateException("Wrong number of arguments!");
        }

        Optional<String> result = Optional.ofNullable(substituteValues.get(args[0]));
        return result.orElse("");
    }
}
