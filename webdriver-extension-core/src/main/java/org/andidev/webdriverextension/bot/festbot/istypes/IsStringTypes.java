package org.andidev.webdriverextension.bot.festbot.istypes;

import org.andidev.webdriverextension.bot.BotUtils;

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

    public boolean containing(String text) {
        return BotUtils.isContaining(text, actual);
    }

    public boolean notContaining(String text) {
        return BotUtils.isNotContaining(text, actual);
    }

    public boolean startingWith(String prefix) {
        return BotUtils.isStartingWith(prefix, actual);
    }

    public boolean notStartingWith(String prefix) {
        return BotUtils.isNotStartingWith(prefix, actual);
    }

    public boolean endingWith(String suffix) {
        return BotUtils.isEndingWith(suffix, actual);
    }

    public boolean notEndingWith(String suffix) {
        return BotUtils.isNotEndingWith(suffix, actual);
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

    public boolean containingAnyOf(String[] texts) {
        return BotUtils.isContainingAnyOf(texts, actual);
    }

    public boolean notContainingAnyOf(String[] texts) {
        return BotUtils.isNotContainingAnyOf(texts, actual);
    }

    public boolean startingWithAnyOf(String[] prefixes) {
        return BotUtils.isStartingWithAnyOf(prefixes, actual);
    }

    public boolean notStartingWithAnyOf(String[] prefixes) {
        return BotUtils.isNotStartingWithAnyOf(prefixes, actual);
    }

    public boolean endingWithAnyOf(String[] suffix) {
        return BotUtils.isEndingWithAnyOf(suffix, actual);
    }

    public boolean notEndingWithAnyOf(String[] suffix) {
        return BotUtils.isNotEndingWithAnyOf(suffix, actual);
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

    public boolean containingIgnoreCase(String text) {
        return BotUtils.isContainingIgnoreCase(text, actual);
    }

    public boolean notContainingIgnoreCase(String text) {
        return BotUtils.isNotContainingIgnoreCase(text, actual);
    }

    public boolean startingWithIgnoreCase(String prefix) {
        return BotUtils.isStartingWithIgnoreCase(prefix, actual);
    }

    public boolean notStartingWithIgnoreCase(String prefix) {
        return BotUtils.isNotStartingWithIgnoreCase(prefix, actual);
    }

    public boolean endingWithIgnoreCase(String suffix) {
        return BotUtils.isEndingWithIgnoreCase(suffix, actual);
    }

    public boolean notEndingWithIgnoreCase(String suffix) {
        return BotUtils.isNotEndingWithIgnoreCase(suffix, actual);
    }
}
