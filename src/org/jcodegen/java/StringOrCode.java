package org.jcodegen.java;

import java.util.ArrayList;
import java.util.List;

/**
 * ...
 */
class StringOrCode<T> {
    private final String string;
    private final T code;

    StringOrCode(final String string) {
        this.string = string;
        code = null;
    }

    StringOrCode(final T code) {
        this.code = code;
        string = null;
    }

    static <T> List<String> getStrings(final List<StringOrCode<T>> alternatives) {
        List<String> strings = new ArrayList<String>();
        for (StringOrCode<T> alternative: alternatives)
            strings.add(alternative.toString());
        return strings;
    }

    @Override
    public String toString() {
        if (string == null)
            return code.toString();
        return string;
    }
}
