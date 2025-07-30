# Web Automation Java Playwright

A comprehensive Maven-based web automation framework using Java and Playwright for testing the OrangeHRM demo application.

## Project Structure

```
Web-Automation-Java-Playwright/
├── src/
│   ├── main/java/com/automation/
│   │   ├── pages/
│   │   │   ├── BasePage.java          # Base page with common operations
│   │   │   ├── LoginPage.java         # Login page object
│   │   │   └── DashboardPage.java     # Dashboard page object
│   │   └── utils/
│   │       ├── ConfigManager.java     # Configuration management
│   │       ├── BrowserManager.java    # Browser instance management
│   │       ├── ExtentReportManager.java # Extent report management
│   │       ├── ExtentReportListener.java # TestNG listener for reports
│   │       └── ScreenshotUtil.java    # Screenshot utility
│   └── test/
│       ├── java/com/automation/tests/
│       │   ├── BaseTest.java          # Base test class
│       │   └── LoginTest.java         # Login test scenarios
│       └── resources/
│           ├── config.properties      # Environment and credentials config
│           └── testng.xml             # TestNG suite configuration
├── test-output/                       # Test reports and screenshots
├── pom.xml                           # Maven configuration
└── README.md                         # Project documentation
```

## Features

- **Maven-based Project**: Easy dependency management and build process
- **Playwright Integration**: Modern web automation with multiple browser support
- **Page Object Model (POJO)**: Well-structured and maintainable test code
- **TestNG Framework**: Powerful testing framework with annotations and listeners
- **Environment Configuration**: Properties-based configuration for different environments
- **Extent Reports**: Rich HTML test reports with screenshots
- **Screenshot on Failure**: Automatic screenshot capture on test failures
- **Multi-browser Support**: Chrome, Firefox, and Safari support

## Prerequisites

- Java 11 or higher
- Maven 3.6+
- Internet connection (for downloading browser binaries)

## Configuration

### Environment Settings (`src/test/resources/config.properties`)

```properties
# Environment Configuration
env=dev
dev.base.url=https://opensource-demo.orangehrmlive.com

# Login Credentials
admin.username=Admin
admin.password=admin123

# Browser Configuration
browser=chromium
headless=false
timeout=30000
```

## Test Scenarios

### LoginTest.java

1. **testValidLogin**: Verifies successful login with valid credentials (Admin/admin123)
2. **testInvalidLogin**: Verifies error handling with invalid credentials
3. **testEmptyCredentials**: Verifies behavior with empty username/password
4. **testLoginPageElements**: Verifies all login page elements are present

## Running Tests

### Install Dependencies and Browser Binaries
```bash
mvn clean install
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

### Run All Tests
```bash
mvn test
```

### Run Specific Test Class
```bash
mvn test -Dtest=LoginTest
```

### Run with Different Browser
```bash
mvn test -Dbrowser=firefox
```

### Run in Headless Mode
```bash
mvn test -Dheadless=true
```

## Reports

- **Extent Reports**: Generated in `test-output/extent-report.html`
- **TestNG Reports**: Generated in `test-output/`
- **Screenshots**: Captured on failure in `test-output/screenshots/`

## Browser Support

- **Chromium** (default)
- **Firefox**
- **WebKit** (Safari)

## Key Components

### ConfigManager
- Handles all configuration properties
- Environment-specific URL management
- Credential management

### BrowserManager
- Thread-safe browser instance management
- Multiple browser support
- Automatic browser lifecycle management

### ExtentReportManager
- Rich HTML reporting
- Screenshot integration
- System information logging

### Page Objects
- Clean separation of page logic
- Reusable page components
- Playwright locator strategies

## Best Practices Implemented

1. **Page Object Model**: Clear separation between test logic and page elements
2. **Configuration Management**: Externalized configuration for different environments
3. **Thread Safety**: Thread-local storage for parallel execution
4. **Error Handling**: Comprehensive error handling and reporting
5. **Screenshot Evidence**: Automatic screenshot capture on failures
6. **Reporting**: Detailed test execution reports
7. **Maven Structure**: Standard Maven directory layout

## Troubleshooting

### Common Issues

1. **Browser Not Found**: Run `mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"`
2. **Configuration Issues**: Verify `config.properties` file exists and has correct values
3. **Network Issues**: Ensure internet connectivity for accessing the demo site

### Logs
- Check console output for detailed execution logs
- Review extent reports for test execution details
- Check screenshots for visual verification of failures

## Contributing

1. Follow the existing code structure and naming conventions
2. Add appropriate JavaDoc comments
3. Include test documentation for new test scenarios
4. Update this README for any significant changes
