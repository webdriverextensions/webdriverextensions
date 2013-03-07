package org.andidev.webdriverextension.bot.festbot.typetypes;

import org.andidev.webdriverextension.bot.BotUtils;

public class TypeTypes {

    public TypeInTypes text(String text) {
        return new TypeInTypes(text);
    }

//    public TypeInTypes number(Double number) {
//        return new TypeInTypes(BotUtils.toString(number));
//    }

}
