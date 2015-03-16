package com.github.webdriverextensions.internal.junitrunner;

import com.github.webdriverextensions.Bot;
import static com.github.webdriverextensions.internal.utils.StringUtils.quote;
import static com.github.webdriverextensions.internal.utils.WebDriverUtils.getScreenshotFilePath;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.slf4j.Logger;

public class TakeScreenshotOnFailureRunListener extends RunListener {

    private final Logger log;
    private final String fileName;

    public TakeScreenshotOnFailureRunListener(Logger log, String fileName) {
        this.log = log;
        this.fileName = fileName;
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        String filePath = getScreenshotFilePath(fileName);
        log.trace("Saving test failure screenshot to " + quote(filePath));
        Bot.takeScreenshot(fileName);
    }
}
