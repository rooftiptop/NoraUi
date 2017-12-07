package com.github.noraui;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import com.github.noraui.exception.TechnicalException;
import com.github.noraui.utils.Context;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, glue = { "com.github.noraui.application.steps", "com.github.noraui.browser.steps" },
        plugin = { "html:target/reports/html", "json:target/reports/json/report.json", "junit:target/reports/junit/report.xml" }, features = { "src/test/resources" })
public class Runner {

    /**
     * BeforeClass Read constants file.
     *
     * @throws TechnicalException
     *             is thrown if you have a technical error (format, configuration, datas, ...) in NoraUi.
     */
    @BeforeClass
    public static void setUpClass() throws TechnicalException {
        Context.getInstance().initializeEnv("demoExcel.properties");
        Context.getInstance().initializeRobot(Runner.class);
    }

    /**
     * AfterClass Read constants file.
     */
    @AfterClass
    public static void tearDownClass() {
        Context.clear();
    }

}