package com.automation.utils;

import com.microsoft.playwright.*;

/**
 * Browser Manager to handle Playwright browser instances
 */
public class BrowserManager {
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();
    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();

    public static void initializeBrowser() {
        ConfigManager config = ConfigManager.getInstance();
        
        // Create a new Playwright instance for this thread
        playwright.set(Playwright.create());

        try {
            // Override config setting to prevent browser display issues
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                    .setHeadless(config.isHeadless())
                    .setSlowMo(500)     // Small delay for stability
                    .setTimeout(config.getTimeout()); // Increase timeout
            
            Browser.NewContextOptions contextOptions = new Browser.NewContextOptions()
                    .setViewportSize(1920, 1080)
                    .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36");

            switch (config.getBrowser().toLowerCase()) {
                case "chromium":
                    browser.set(playwright.get().chromium().launch(launchOptions));
                    break;
                case "firefox":
                    browser.set(playwright.get().firefox().launch(launchOptions));
                    break;
                case "webkit":
                    browser.set(playwright.get().webkit().launch(launchOptions));
                    break;
                default:
                    throw new IllegalArgumentException("Browser not supported: " + config.getBrowser());
            }

            context.set(browser.get().newContext(contextOptions));
            page.set(context.get().newPage());
            
            // Set default timeout
            page.get().setDefaultTimeout(config.getTimeout());
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize browser: " + e.getMessage(), e);
        }
    }

    public static Page getPage() {
        return page.get();
    }

    public static Browser getBrowser() {
        return browser.get();
    }

    public static BrowserContext getContext() {
        return context.get();
    }

    public static void closeBrowser() {
        try {
            if (page.get() != null) {
                page.get().close();
                page.remove();
            }
            
            if (context.get() != null) {
                context.get().close();
                context.remove();
            }
            
            if (browser.get() != null) {
                browser.get().close();
                browser.remove();
            }
            
            if (playwright.get() != null) {
                playwright.get().close();
                playwright.remove();
            }
        } catch (Exception e) {
            // Log error but don't throw to avoid masking test failures
            System.err.println("Error closing browser: " + e.getMessage());
        }
    }
}
