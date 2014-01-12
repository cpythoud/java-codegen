package org.jcodegen.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * ...
 */
public class ImportsManager {

    private Set<String> imports = new HashSet<String>();

    private static final Pattern importValidationPattern;

    static {
        try {
            importValidationPattern = Pattern.compile("([_a-zA-Z][_\\w]+\\.)+[_a-zA-Z][_\\w]+(\\.\\*)?");
        } catch (final PatternSyntaxException pse) {
            throw new RuntimeException(pse);
        }
    }


    public void addImport(final String importStr) {
        final Matcher matcher = importValidationPattern.matcher(importStr);
        if (!matcher.matches())
            throw new IllegalArgumentException("Illegal character in import name: " + importStr);

        imports.add(importStr);
    }

    public void clear() {
        imports.clear();
    }

    public int getCount() {
        return imports.size();
    }

    public String getImports() {
        final List<String> javaImports = new ArrayList<String>();
        final List<String> otherImports = new ArrayList<String>();

        for (String importStr: imports) {
            if (importStr.startsWith("java.") || importStr.startsWith("javax."))
                javaImports.add(importStr);
            else
                otherImports.add(importStr);
        }

        final StringBuilder buf = new StringBuilder();
        if (javaImports.size() > 0) {
            Collections.sort(javaImports);
            addImports(buf, javaImports);
            buf.append("\n");
        }
        if (otherImports.size() > 0) {
            Collections.sort(otherImports);
            addImports(buf, otherImports);
            buf.append("\n");
        }

        return buf.toString();
    }

    private void addImports(final StringBuilder buf, final List<String> imports) {
        String lastSubPackageName = "";

        for (String importStr: imports) {
            final String subPackageName = getPackageName(importStr);

            if (!subPackageName.equals(lastSubPackageName) && !lastSubPackageName.equals(""))
                buf.append("\n");

            buf.append("import ");
            buf.append(importStr);
            buf.append(";\n");

            lastSubPackageName = subPackageName;
        }
    }

    private String getPackageName(final String importStr) {
        return importStr.substring(0, importStr.lastIndexOf("."));
    }
}
