package com.test.pages;

import com.test.basepage.BasePage;
import com.test.infrastructure.driver.WindowHandler;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CheckOutModalPage extends BasePage{

    @FindBy(xpath = "//div[@class='checkoutui-SalesFrontCash-m5Re7']//button[text()='Sepete git']")
    private WebElement goToCartButton;

    @FindBy(xpath = "//div[@class='checkoutui-SalesFrontCash-m5Re7']//button[text()='Alışverişe devam et']")
    private WebElement continueToShoppingButton;

    @FindBy(className = "checkoutui-Modal-2iZXl")
    private WebElement modalCloseButton;

    public CheckOutModalPage() {
        PageFactory.initElements(driver, this);
    }

    public void waitForModalReady(){
        wait.forElementToBeClickable(15, goToCartButton);
    }

    public void clickGoToCartButton(){
        wait.forElementToBeClickable(15, goToCartButton);
        this.goToCartButton.click();
        wait.forLoading(15);
        //WindowHandler.switchToParent(this.driver);
    }

    public void clickModalCloseButton(){
        modalCloseButton.click();
    }

    public boolean checkIfPageShown(){
        return modalCloseButton.isDisplayed();

    }

    public void waitForModalOpen(){
        WindowHandler.switchToPopUp(this.driver);
    }





}
