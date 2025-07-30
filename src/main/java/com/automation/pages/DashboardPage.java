package com.automation.pages;

import com.microsoft.playwright.Locator;

/**
 * Dashboard Page Object class
 */
public class DashboardPage extends BasePage {
    
    // Locators
    private final Locator dashboardTitle;
    private final Locator userDropdown;
    private final Locator logoutButton;
    private final Locator sideMenu;
    private final Locator welcomeMessage;
    public DashboardPage() {
        super();
        this.dashboardTitle = page.locator(".oxd-text--h6").first();
        this.userDropdown = page.locator(".oxd-userdropdown");
        this.logoutButton = page.locator("text=Logout");
        this.sideMenu = page.locator(".oxd-sidepanel");
        this.welcomeMessage = page.locator(".oxd-text--h6").nth(1);
    }

    public boolean isDashboardDisplayed() {
        return dashboardTitle.isVisible() && sideMenu.isVisible();
    }

    public String getDashboardTitle() {
        return dashboardTitle.textContent();
    }

    public void clickUserDropdown() {
        userDropdown.click();
    }

    public void logout() {
        clickUserDropdown();
        logoutButton.click();
    }

    public boolean isUserDropdownDisplayed() {
        return userDropdown.isVisible();
    }

    public boolean isSideMenuDisplayed() {
        return sideMenu.isVisible();
    }

    public String getWelcomeMessage() {
        if (welcomeMessage.isVisible()) {
            return welcomeMessage.textContent();
        }
        return "";
    }

    public void waitForDashboard() {
        dashboardTitle.waitFor();
        sideMenu.waitFor();
    }

    public void navigateToModule(String moduleName) {
        page.locator("text=" + moduleName).click();
    }
}
