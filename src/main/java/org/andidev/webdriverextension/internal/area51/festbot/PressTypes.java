package org.andidev.webdriverextension.internal.area51.festbot;

import org.openqa.selenium.Keys;

public class PressTypes {

    public PressKeysTypes enter() {
        return new PressKeysTypes(Keys.ENTER);
    }
//
//    public String space() {
//        return BotUtils.read(webElement);
//    }
//
//    public String backspace() {
//        return BotUtils.read(webElement);
//    }
//
//    public String up() {
//        return BotUtils.read(webElement);
//    }
//
//    public String down() {
//        return BotUtils.read(webElement);
//    }
//
//    public String left() {
//        return BotUtils.read(webElement);
//    }
//
//    public String right() {
//        return BotUtils.read(webElement);
//    }
//
//    public Double key() {
//        return BotUtils.readNumber(webElement);
//    }

//    public PressKeyTypes shift(CharSequence... keys) {
//        return new PressKeyTypes(Keys.chord(Keys.SHIFT.toString() + keys));
//    }
//
//    public PressKeyTypes control(CharSequence... keys) {
//        return BotUtils.readNumber(webElement);
//    }
//
//    public PressKeyTypes alt(CharSequence... keys) {
//        return BotUtils.readNumber(webElement);
//    }
//
//    public PressKeyTypes meta(CharSequence... keys) {
//        return BotUtils.readNumber(webElement);
//    }

    public PressKeysTypes keys(CharSequence... keys) {
        return new PressKeysTypes(keys);
    }
}
