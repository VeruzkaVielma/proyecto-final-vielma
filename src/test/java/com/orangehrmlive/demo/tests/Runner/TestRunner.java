package com.orangehrmlive.demo.tests.Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

/**
 * ============================================================
 * TEST RUNNER - Configuring Cucumber with TestNG
 * ============================================================
 */
@CucumberOptions(
        // features: Location of .feature files
        features = "src/test/resources/features",

        // glue: Location of Step Definitions and Hooks
        glue = {"com.orangehrmlive.demo.tests.steps",
                "com.orangehrmlive.demo.tests.Runner"},

        // plugin: Report format
        plugin = {"pretty",
                "html:target/cucumber-reports.html"},

        // monochrome: Console format
        monochrome = false,

        // tags: Filter which Scenarios to execute (optional).
        // Example: to execute only data-driven ones: tags = "@data-driven"
        tags = "")

public class TestRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {return super.scenarios();}
}