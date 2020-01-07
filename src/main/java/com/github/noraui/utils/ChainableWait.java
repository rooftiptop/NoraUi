package com.github.noraui.utils;

import java.util.Collection;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ChainableWait<T> {
    private WebDriverWait webDriverWait;
    private T chainedValue;

    public ChainableWait(WebDriverWait webDriverWait) {
        this.webDriverWait = webDriverWait;
    }

    public ChainableWait(WebDriverWait webDriverWait, T chainedValue) {
        this.webDriverWait = webDriverWait;
        this.chainedValue = chainedValue;
    }

    public ChainableWait<T> wait(Function<Object, ExpectedCondition<T>> mapper) {
        return new ChainableWait<T>(webDriverWait, webDriverWait.until(mapper.apply(chainedValue)));
    }

    public <R> ChainableWait<R> then(Function<T, R> mapper) {
        return new ChainableWait<R>(webDriverWait, mapper.apply(chainedValue));
    }

    public ChainableWait<T> wait(ExpectedCondition<T> condition) {
        return new ChainableWait<T>(webDriverWait, webDriverWait.until(condition));
    }

    @SuppressWarnings("unchecked")
    public Collection<ChainableWait<T>> all() {
        if (chainedValue instanceof Collection) {
            return ((Collection<T>) chainedValue).stream().map(v -> new ChainableWait<T>(webDriverWait, v)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public T get() {
        return chainedValue;
    }

}

@FunctionalInterface
interface Expect<T> {
    default T expect(Object... arguments) {
    }

}
