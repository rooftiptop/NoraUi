package com.github.noraui.browser.waits;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.noraui.utils.Context;

public class Wait {
    /**
     * Single global instance of WebDriverWait
     */
    private static WebDriverWait webDriverWait;

    /**
     * Wait will ignore instances of NotFoundException that are encountered (thrown) by default in
     * the 'until' condition, and immediately propagate all others. You can add more to the ignore
     * list by calling ignoring(exceptions to add).
     * 
     * @param <T>
     *            The function's expected return type.
     * @param condition
     *            the parameter to pass to the {@link ExpectedCondition}
     * @return The function's return value if the function returned something different
     *         from null or false before the timeout expired.
     */
    public static <T> T until(ExpectedCondition<T> condition) {
        return untilAnd(condition).get();
    }

    /**
     * Wait will ignore instances of NotFoundException that are encountered (thrown) by default in
     * the 'until' condition, and immediately propagate all others. You can add more to the ignore
     * list by calling ignoring(exceptions to add).
     * 
     * @param <T>
     *            The function's expected return type.
     * @param condition
     *            the parameter to pass to the {@link ExpectedCondition}
     * @param timeOutInSeconds
     *            The timeout in seconds when an expectation is called
     * @return The function's return value if the function returned something different
     *         from null or false before the timeout expired.
     */
    public static <T> T until(ExpectedCondition<T> condition, int timeOutInSeconds) {
        return untilAnd(condition, timeOutInSeconds).get();
    }

    public static <T> ChainableWait<T> untilAnd(ExpectedCondition<T> condition) {
        if (webDriverWait == null) {
            webDriverWait = new WebDriverWait(Context.getDriver(), Context.getTimeout());
        }
        return new ChainableWait<T>(webDriverWait).wait(condition);
    }

    public static <T> ChainableWait<T> untilAnd(ExpectedCondition<T> condition, int timeOutInSeconds) {
        return new ChainableWait<T>(new WebDriverWait(Context.getDriver(), timeOutInSeconds)).wait(condition);
    }
}
