package com.automation.utils;

import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Screenshot utility for capturing screenshots
 */
public class ScreenshotUtil {
    private static final String SCREENSHOT_DIR = "test-output/screenshots/";

    public static String captureScreenshot(String testName) {
        try {
            // Create screenshots directory if it doesn't exist
            java.nio.file.Files.createDirectories(Paths.get(SCREENSHOT_DIR));
            
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String fileName = testName + "_" + timestamp + ".png";
            String filePath = SCREENSHOT_DIR + fileName;
            
            // Capture screenshot using Playwright
            BrowserManager.getPage().screenshot(new com.microsoft.playwright.Page.ScreenshotOptions()
                    .setPath(Paths.get(filePath))
                    .setFullPage(true));
            
            // Return relative path for extent reports (relative to the HTML file location)
            return "screenshots/" + fileName;
        } catch (Exception e) {
            throw new RuntimeException("Failed to capture screenshot", e);
        }
    }
}
