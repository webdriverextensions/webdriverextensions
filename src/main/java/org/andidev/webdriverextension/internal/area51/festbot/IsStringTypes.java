package org.andidev.webdriverextension.internal.area51.festbot;

import org.andidev.webdriverextension.internal.BotUtils;

public class IsStringTypes {

    private final String actual;

    public IsStringTypes(String actual) {
        this.actual = actual;
    }

    /* Is */
    public boolean equals(String text) {
        return BotUtils.equals(text, actual);
    }

    public boolean notEquals(String text) {
        return BotUtils.notEquals(text, actual);
    }

    public boolean contains(String text) {
        return BotUtils.contains(text, actual);
    }

    public boolean notContains(String text) {
        return BotUtils.notContains(text, actual);
    }

    public boolean startsWith(String prefix) {
        return BotUtils.startsWith(prefix, actual);
    }

    public boolean notStartsWith(String prefix) {
        return BotUtils.notStartsWith(prefix, actual);
    }

    public boolean endingWith(String suffix) {
        return BotUtils.endsWith(suffix, actual);
    }

    public boolean notEndsWith(String suffix) {
        return BotUtils.notEndsWith(suffix, actual);
    }

    public boolean matching(String regularExpression) {
        return BotUtils.matches(regularExpression, actual);
    }

    public boolean notMatching(String regularExpression) {
        return BotUtils.notMatches(regularExpression, actual);
    }

    /* Is Any Of */
    public boolean equalsAnyOf(String[] texts) {
        return BotUtils.equalsAnyOf(texts, actual);
    }

    public boolean notEqualsAnyOf(String[] texts) {
        return BotUtils.notEqualsAnyOf(texts, actual);
    }

    public boolean containsAnyOf(String[] texts) {
        return BotUtils.containsAnyOf(texts, actual);
    }

    public boolean notContainsAnyOf(String[] texts) {
        return BotUtils.notContainsAnyOf(texts, actual);
    }

    public boolean startsWithAnyOf(String[] prefixes) {
        return BotUtils.startsWithAnyOf(prefixes, actual);
    }

    public boolean notStartsWithAnyOf(String[] prefixes) {
        return BotUtils.notStartsWithAnyOf(prefixes, actual);
    }

    public boolean endsWithAnyOf(String[] suffix) {
        return BotUtils.endsWithAnyOf(suffix, actual);
    }

    public boolean notEndsWithAnyOf(String[] suffix) {
        return BotUtils.notEndsWithAnyOf(suffix, actual);
    }

    public boolean matchingAnyOf(String[] regularExpressions) {
        return BotUtils.matchingAnyOf(regularExpressions, actual);
    }

    public boolean notMatchingAnyOf(String[] regularExpressions) {
        return BotUtils.notMatchingAnyOf(regularExpressions, actual);
    }

    /* Is Ignore Case */
    public boolean equalsIgnoreCase(String text) {
        return BotUtils.equalsIgnoreCase(text, actual);
    }

    public boolean notEqualsIgnoreCase(String text) {
        return BotUtils.notEqualsIgnoreCase(text, actual);
    }

    public boolean containsIgnoreCase(String text) {
        return BotUtils.containsIgnoreCase(text, actual);
    }

    public boolean notContainsIgnoreCase(String text) {
        return BotUtils.notContainsIgnoreCase(text, actual);
    }

    public boolean startsWithIgnoreCase(String prefix) {
        return BotUtils.startsWithIgnoreCase(prefix, actual);
    }

    public boolean notStartsWithIgnoreCase(String prefix) {
        return BotUtils.notStartsWithIgnoreCase(prefix, actual);
    }

    public boolean endsWithIgnoreCase(String suffix) {
        return BotUtils.endsWithIgnoreCase(suffix, actual);
    }

    public boolean notEndsWithIgnoreCase(String suffix) {
        return BotUtils.notEndsWithIgnoreCase(suffix, actual);
    }
}
