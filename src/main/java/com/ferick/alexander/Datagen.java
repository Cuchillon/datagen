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

    public String get(final String template, final String... substituteValues) {
        return get(template, MapUtils.asMap(substituteValues));
    }

    public String get(final String template) {
        return get(template, new HashMap<>());
    }

    public List<String> get(Integer count, final String template, final Map<String, String> substituteValues) {
        List<String> resultList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            resultList.add(get(template, substituteValues));
        }
        return resultList;
    }

    public List<String> get(Integer count, final String template, final String... substituteValues) {
        return get(count, template, MapUtils.asMap(substituteValues));
    }

    public List<String> get(Integer count, final String template) {
        return get(count, template, new HashMap<>());
    }
}
