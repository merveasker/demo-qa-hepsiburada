package com.test.infrastructure.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class WindowHandler {

    private static String parentWindowHandler;
    private WebDriver driver;
    private static ArrayList<String> tabs;

    public WindowHandler(WebDriver driver) {
        this.driver = Setup.driver;
    }

    public static void switchToNewTab(WebDriver driver) {
        tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }

    public static void switchToMainTab(WebDriver driver) {
        driver.close();
        driver.switchTo().window(tabs.get(0));
    }

    public static void switchToPopUp(WebDriver driver) {
        parentWindowHandler = driver.getWindowHandle(); // Store your parent window
        String subWindowHandler = null;

        Set<String> handles = driver.getWindowHandles(); // get all window handles
        Iterator<String> iterator = handles.iterator();
        while (iterator.hasNext()){
            subWindowHandler = iterator.next();
        }
        driver.switchTo().window(subWindowHandler); // switch to popup window

    }
    public static void switchToParent(WebDriver driver) {
        driver.switchTo().window(parentWindowHandler);  // switch back to parent window
    }

    public static void switchToIframe(WebDriver driver, WebElement iframe){
        driver.switchTo().frame(iframe);
    }

    public static void switchToDefaultAfterIframe(WebDriver driver){
        driver.switchTo().defaultContent();
    }

}
