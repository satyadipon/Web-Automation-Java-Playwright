package com.automation.pages;

import com.microsoft.playwright.Locator;

/**
 * Login Page Object class
 */
public class LoginPage extends BasePage {
    
    // Locators
    private final Locator usernameField;
    private final Locator passwordField;
    private final Locator loginButton;
    private final Locator errorMessage;
    private final Locator loginTitle;
    private final Locator forgotPasswordLink;

    public LoginPage() {
        super();
        this.usernameField = page.locator("input[name='username']");
        this.passwordField = page.locator("input[name='password']");
        this.loginButton = page.locator("button[type='submit']");
        this.errorMessage = page.locator(".alert-danger, .error-message, .oxd-alert-content-text");
        this.loginTitle = page.locator("h1, h2, h3, .login-title, .oxd-text--h5");
        this.forgotPasswordLink = page.locator("a:has-text('Forgot'), .forgot-password, .oxd-text--p");
    }

    public void enterUsername(String username) {
        usernameField.fill(username);
    }

    public void enterPassword(String password) {
        passwordField.fill(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isLoginPageDisplayed() {
        return loginTitle.isVisible() && usernameField.isVisible() && passwordField.isVisible();
    }

    public String getErrorMessage() {
        if (errorMessage.isVisible()) {
            return errorMessage.textContent();
        }
        return "";
    }

    public boolean isErrorMessageDisplayed() {
        return errorMessage.isVisible();
    }

    public String getLoginTitle() {
        return loginTitle.textContent();
    }

    public boolean isForgotPasswordLinkDisplayed() {
        return forgotPasswordLink.isVisible();
    }

    public void waitForLoginPage() {
        // Wait for the essential login elements that we know exist
        usernameField.waitFor();
        passwordField.waitFor();
        // Don't wait for login button as it might have different selectors on different sites
    }
}
