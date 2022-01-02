package com.test.pages;

import com.test.basepage.BasePage;
import com.test.infrastructure.driver.WindowHandler;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SuggestedProductsPage extends BasePage{

    @FindBy(className = "deny-text")
    private WebElement denySuggestedProductButton;

    @FindBy(xpath = "//div[@class='popup']//i[@class='close']")
    private WebElement closeButton;

    Actions action = new Actions(driver);

    public SuggestedProductsPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickDenySuggestedProductButton(){
        wait.forElementToBeClickable(15, closeButton);
        closeButton.click();
        //action.moveToElement(this.denySuggestedProductButton).perform();
        //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", denySuggestedProductButton);
        //wait.waitfor(10);
        //this.denySuggestedProductButton.click();
        wait.forLoading(15);
    }

}
