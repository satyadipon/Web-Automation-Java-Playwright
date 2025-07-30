package com.automation.pages;

import com.automation.utils.BrowserManager;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

/**
 * Base Page class with common page operations
 */
public class BasePage {
    protected Page page;

    public BasePage() {
        this.page = BrowserManager.getPage();
    }

    public void navigateTo(String url) {
        page.navigate(url);
    }

    public void waitForPageLoad() {
        page.waitForLoadState();
    }

    public void click(String selector) {
        page.click(selector);
    }

    public void click(Locator locator) {
        locator.click();
    }

    public void fill(String selector, String text) {
        page.fill(selector, text);
    }

    public void fill(Locator locator, String text) {
        locator.fill(text);
    }

    public String getText(String selector) {
        return page.textContent(selector);
    }

    public String getText(Locator locator) {
        return locator.textContent();
    }

    public boolean isVisible(String selector) {
        return page.isVisible(selector);
    }

    public boolean isVisible(Locator locator) {
        return locator.isVisible();
    }

    public void waitForSelector(String selector) {
        page.waitForSelector(selector);
    }

    public String getTitle() {
        return page.title();
    }

    public String getCurrentUrl() {
        return page.url();
    }
}
