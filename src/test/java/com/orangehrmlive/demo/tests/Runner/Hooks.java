package com.orangehrmlive.demo.tests.Runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Hooks {

    // Static driver for access from Step Definitions
    public static WebDriver driver;

    // @Before - Executes BEFORE each Scenario/Example
    @Before
    public void setUp(Scenario scenario) {
        System.out.println("\n========================================");
        System.out.println("Starting up: " + scenario.getName());
        System.out.println("========================================\n");

        // Configure ChromeDriver automatically
        WebDriverManager.chromedriver().setup();

        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();

        // Disable search engine selection screen
        options.addArguments("--disable-search-engine-choice-screen");

        // Disable notifications
        options.addArguments("--disable-notifications");

        // Disable information bar
        options.addArguments("--disable-infobars");

        // Allow remote origins
        options.addArguments("--remote-allow-origins=*");

        // Experimental preferences
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        // Create the driver
        driver = new ChromeDriver(options);

        // Maximize window
        driver.manage().window().maximize();
    }

    // @After - Executes AFTER each Scenario/Example
    @After
    public void tearDown(Scenario scenario) {
        // Show result
        if (scenario.isFailed()) {System.out.println("\n❌ FAILED: " + scenario.getName());
        } else {System.out.println("\n✅ PASS: " + scenario.getName());}
        System.out.println("========================================\n");

        // Close browser
        if (driver != null) {driver.quit();driver = null;}
    }
}