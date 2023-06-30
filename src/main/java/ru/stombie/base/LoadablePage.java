package ru.stombie.base;

import org.openqa.selenium.By;

public abstract class LoadablePage {
    protected static final By BODY = By.tagName("body");

    public LoadablePage() {
        isLoaded();
    }

    protected abstract void isLoaded();
}
