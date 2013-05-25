package org.andidev.webdriverextension.internal.area51.festbot;

import org.andidev.webdriverextension.internal.BotUtils;

public class IsStringTypes {

    private final String actual;

    public IsStringTypes(String actual) {
        this.actual = actual;
    }

    /* Is */
    public boolean equalTo(String text) {
        return BotUtils.is(text, actual);
    }

    public boolean notEqualTo(String text) {
        return BotUtils.isNot(text, actual);
    }

    public boolean contains(String text) {
        return BotUtils.isContains(text, actual);
    }

    public boolean notContains(String text) {
        return BotUtils.isNotContains(text, actual);
    }

    public boolean startsWith(String prefix) {
        return BotUtils.isStartsWith(prefix, actual);
    }

    public boolean notStartsWith(String prefix) {
        return BotUtils.isNotStartsWith(prefix, actual);
    }

    public boolean endingWith(String suffix) {
        return BotUtils.isEndsWith(suffix, actual);
    }

    public boolean notEndsWith(String suffix) {
        return BotUtils.isNotEndsWith(suffix, actual);
    }

    public boolean matching(String regularExpression) {
        return BotUtils.isMatching(regularExpression, actual);
    }

    public boolean notMatching(String regularExpression) {
        return BotUtils.isNotMatching(regularExpression, actual);
    }

    /* Is Any Of */
    public boolean equalToAnyOf(String[] texts) {
        return BotUtils.isAnyOf(texts, actual);
    }

    public boolean notEqualToAnyOf(String[] texts) {
        return BotUtils.isNotAnyOf(texts, actual);
    }

    public boolean containsAnyOf(String[] texts) {
        return BotUtils.isContainsAnyOf(texts, actual);
    }

    public boolean notContainsAnyOf(String[] texts) {
        return BotUtils.isNotContainsAnyOf(texts, actual);
    }

    public boolean startsWithAnyOf(String[] prefixes) {
        return BotUtils.isStartsWithAnyOf(prefixes, actual);
    }

    public boolean notStartsWithAnyOf(String[] prefixes) {
        return BotUtils.isNotStartsWithAnyOf(prefixes, actual);
    }

    public boolean endingWithAnyOf(String[] suffix) {
        return BotUtils.isEndsWithAnyOf(suffix, actual);
    }

    public boolean notEndsWithAnyOf(String[] suffix) {
        return BotUtils.isNotEndsWithAnyOf(suffix, actual);
    }

    public boolean matchingAnyOf(String[] regularExpressions) {
        return BotUtils.isMatchingAnyOf(regularExpressions, actual);
    }

    public boolean notMatchingAnyOf(String[] regularExpressions) {
        return BotUtils.isNotMatchingAnyOf(regularExpressions, actual);
    }

    /* Is Ignore Case */
    public boolean equalToignoreCase(String text) {
        return BotUtils.isIgnoreCase(text, actual);
    }

    public boolean notEqualToIgnoreCase(String text) {
        return BotUtils.isNotIgnoreCase(text, actual);
    }

    public boolean containsIgnoreCase(String text) {
        return BotUtils.isContainsIgnoreCase(text, actual);
    }

    public boolean notContainsIgnoreCase(String text) {
        return BotUtils.isNotContainsIgnoreCase(text, actual);
    }

    public boolean startsWithIgnoreCase(String prefix) {
        return BotUtils.isStartsWithIgnoreCase(prefix, actual);
    }

    public boolean notStartsWithIgnoreCase(String prefix) {
        return BotUtils.isNotStartsWithIgnoreCase(prefix, actual);
    }

    public boolean endingWithIgnoreCase(String suffix) {
        return BotUtils.isEndsWithIgnoreCase(suffix, actual);
    }

    public boolean notEndsWithIgnoreCase(String suffix) {
        return BotUtils.isNotEndsWithIgnoreCase(suffix, actual);
    }
}
