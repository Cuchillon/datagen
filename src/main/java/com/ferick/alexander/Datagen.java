package com.ferick.alexander;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Datagen {

    public String get(String template) {
        String wholeRegex = "\\$\\{-(\\w+)\\s([\\w.]+)\\s?(\\w+\\.?\\w?)?\\s?(\\w+)?\\s?(\\w+)?-}";
        String leftRemovable = "\\$\\{-";
        String rightRemovable = "-}";

        Pattern p = Pattern.compile(wholeRegex);
        Matcher m = p.matcher(template);
        int count = 1;
        while (m.find()) {
            String printedString = m.group();
            String actual = printedString.replaceFirst(leftRemovable, "").replaceFirst(rightRemovable, "");
            actual = actual.toUpperCase().replaceAll("\\s+", "");
            template = template.replaceFirst(Pattern.quote(printedString), actual + "_" + count);
            m = p.matcher(template);
            count++;
        }

        return template;
    }
}
