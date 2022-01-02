package com.test.runner;

import com.test.basepage.BasePage;
import com.test.infrastructure.driver.Setup;
import io.cucumber.java.Before;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;


@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        strict = true, plugin = {"pretty", "json:target/cucumber-reports/home-page.json", "html:target/cucumber-reports/home-page.html"},
        glue = {"com.test.infrastructure.driver", "com.test.pages", "com.test.steps"})
//tags = {"not @login"})
public class TestRunner {

    @AfterClass
    public static void quitDriver() {
        if (Setup.driver != null){
            Setup.driver.quit();
        }
        Setup.driver = null;
    }

}
