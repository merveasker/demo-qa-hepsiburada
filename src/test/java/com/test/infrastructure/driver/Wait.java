package com.test.infrastructure.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Wait {

    private WebDriver driver;

    public Wait(WebDriver driver) {
        this.driver = driver;
    }

    private void waitUntilCondition(ExpectedCondition condition, String timeoutMessage, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.withMessage(timeoutMessage);
        wait.until(condition);
    }

    public void forLoading(int timeout){
        //driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        //boolean hey = ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");

        ExpectedCondition<Object> condition = ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
        String timeoutMessage = "Page didn't load after " + timeout + " seconds.";
        waitUntilCondition(condition, timeoutMessage, timeout);
    }

    public void forElementToBeDisplayed(int timeout, WebElement webElement){
        ExpectedCondition<WebElement> condition = ExpectedConditions.visibilityOf(webElement);
        String timeoutMessage = webElement + " wasn't displayed after " + timeout + " seconds.";
        waitUntilCondition(condition, timeoutMessage, timeout);
    }

    public void forPresenceOfElement(int timeout, By elementLocator){
        ExpectedCondition<List<WebElement>> condition = ExpectedConditions.presenceOfAllElementsLocatedBy(elementLocator);
        String timeoutMessage = " Element was not displayed after " + timeout + " seconds.";
        waitUntilCondition(condition, timeoutMessage, timeout);
    }

    public void forElementToBeClickable(int timeout, WebElement webElement){
        ExpectedCondition<WebElement> condition = ExpectedConditions.elementToBeClickable(webElement);
        String timeoutMessage = webElement + " was not displayed after " + timeout + " seconds.";
        waitUntilCondition(condition, timeoutMessage, timeout);
    }

    public void forTextToBePresentInElement(int timeout, WebElement webElement, String text){
        ExpectedCondition<Boolean> condition = ExpectedConditions.textToBePresentInElement(webElement,text);
        String timeoutMessage = webElement + " didn't have " + text + "in " + timeout + " seconds.";
        waitUntilCondition(condition, timeoutMessage, timeout);
    }

    public void waitfor(int timeout){
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }
}
