package com.ferick.alexander;

import com.ferick.alexander.utils.MapUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    /**
     * Method takes a template and returns a string with random and substitute values
     *
     * @param template a string with patterns to be substituted
     * @param substituteValues a map with values to be returned instead of 'set' patterns
     * @return string with random and substitute values
     */
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

    /**
     * Method takes a template and returns a string with random and substitute values
     *
     * @param template a string with patterns to be substituted
     * @param substituteValues key-value pairs with values to be returned instead of 'set' patterns
     * @return string with random and substitute values
     */
    public String get(final String template, final String... substituteValues) {
        return get(template, MapUtils.asMap(substituteValues));
    }

    /**
     * Method takes a template and returns a string with random values
     *
     * @param template a string with patterns to be substituted
     * @return string with random values
     */
    public String get(final String template) {
        return get(template, new HashMap<>());
    }

    /**
     * Method takes a template and returns a list of strings with random and substitute values
     *
     * @param count number of strings to be in the list
     * @param template a string with patterns to be substituted
     * @param substituteValues a map with values to be returned instead of 'set' patterns
     * @return list of strings with random and substitute values
     */
    public List<String> get(Integer count, final String template, final Map<String, String> substituteValues) {
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            resultList.add(get(template, substituteValues));
        }
        return resultList;
    }

    /**
     * Method takes a template and returns a list of strings with random and substitute values
     *
     * @param count number of strings to be in the list
     * @param template a string with patterns to be substituted
     * @param substituteValues key-value pairs with values to be returned instead of 'set' patterns
     * @return list of strings with random and substitute values
     */
    public List<String> get(Integer count, final String template, final String... substituteValues) {
        return get(count, template, MapUtils.asMap(substituteValues));
    }

    /**
     * Method takes a template and returns a list of strings with random values
     *
     * @param count number of strings to be in the list
     * @param template a string with patterns to be substituted
     * @return list of strings with random values
     */
    public List<String> get(Integer count, final String template) {
        return get(count, template, new HashMap<>());
    }
}
