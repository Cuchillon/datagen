package com.ferick.alexander;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Datagen {

    private static final String WHOLE_REGEX = "\\$\\{-(\\w+)\\s([\\w.]+)\\s?(\\w+\\.?\\w?)?\\s?(\\w+)?\\s?(\\w+)?-}";
    private static final String LEFT_REMOVABLE = "\\$\\{-";
    private static final String RIGHT_REMOVABLE = "-}";

    private ReplacementHandler replacementHandler;

    public Datagen() {
        replacementHandler = new ReplacementHandler();
    }

    public String get(final String template, final Map<String, String> substituteValues) {
        String result = template;
        Pattern p = Pattern.compile(WHOLE_REGEX);
        Matcher m = p.matcher(result);
        while (m.find()) {
            String matchedString = m.group();
            String precept = matchedString.replaceFirst(LEFT_REMOVABLE, "").replaceFirst(RIGHT_REMOVABLE, "");
            String replacement = replacementHandler.getReplacement(precept, substituteValues);
            result = result.replaceFirst(Pattern.quote(matchedString), replacement);
            m = p.matcher(result);
        }

        return result;
    }
}
