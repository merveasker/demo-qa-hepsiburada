package com.test.pages;

import com.test.basepage.BasePage;
import com.test.infrastructure.driver.WindowHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class ProductDetailPage extends BasePage{

    @FindBy(id = "addToCart")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[@class='seller']//a")
    private WebElement defaultSellerName;

    @FindBy(xpath = "//div[@class='other-sellers']//tr")
    private List<WebElement> otherSellers;

    @FindBy(xpath = "//div[@class='marketplace-list']//tr[1]//div[@class='addToCart']//button")
    private WebElement firstOtherSellerAddToCartButton;

    @FindBy(className = "checkoutui-SalesFrontCash-XeG2a")
    private WebElement addCartSuccessMessage;

    public ProductDetailPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickAddToCartButton(){
        wait.forElementToBeClickable(20, addToCartButton);
        this.addToCartButton.click();
        wait.forElementToBeClickable(20, addToCartButton);

    }

    public boolean checkIfProductAddedSuccessfully(){
        String cartSuccessMessage = "Ürün sepetinizde";
        return this.addCartSuccessMessage.getText().equals(cartSuccessMessage);
    }

    public String getDefaultSellerName(){
        return defaultSellerName.getText();
    }

    public List<WebElement> getOtherSellers() {
        return otherSellers;
    }

    public String getOtherFirstSellersName(){
        return otherSellers.get(0).findElement(By.xpath("//div[@class='merchant-info']/a")).getText();
    }

    public void clickAddCartForOtherFirstSeller(){
        WebElement elementToScroll =  otherSellers.get(0).findElement(By.xpath("//div[@class='addToCart']//button"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementToScroll);
        wait.waitfor(10);
        elementToScroll.click();
    }





}
