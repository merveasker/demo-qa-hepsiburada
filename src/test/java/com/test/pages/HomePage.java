package com.test.pages;

import com.test.basepage.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage{

    private static final String HOME_PAGE = "https://hepsiburada.com/";
    private static final String PAGE_TITLE = "Türkiye'nin En Büyük Online Alışveriş Sitesi Hepsiburada.com";
    private Actions action;

    @FindBy(id = "myAccount")
    private WebElement loginOrSignUpDiv;

    @FindBy(id = "login")
    private WebElement loginLink;

    @FindBy(xpath = "//span[text()='Hesabım']")
    private WebElement accountLink;

    @FindBy(className = "sf-OldMyAccount-1k66b")
    private WebElement userNameTextbox;

    @FindBy(className = "desktopOldAutosuggestTheme-input")
    private WebElement searchTextbox;

    @FindBy(className = "SearchBoxOld-buttonContainer")
    private WebElement searchButton;

    @FindBy(id = "shoppingCart")
    private WebElement shoppingCartButton;

    public HomePage() {
        PageFactory.initElements(driver, this);
        action = new Actions(driver);
    }

    public static String getHomePage() {
        return HOME_PAGE;
    }

    public void visitUrl(){
        driver.get(HOME_PAGE);
        wait.forLoading(5);
    }

    public void verifyPageTitle() {
        Assert.assertEquals(driver.getTitle(), PAGE_TITLE);
    }

    public void hoverLoginOrSignUp(){
        action.moveToElement(this.loginOrSignUpDiv).perform();
    }

    public void clickLoginLink(){
        wait.forElementToBeClickable(10, loginLink);
        this.loginLink.click();
    }

    public void verifyUserName(String userName){
        wait.forElementToBeClickable(10, accountLink);
        Assert.assertEquals(userName, userNameTextbox.getText());

    }

    public void searchProduct(String productName){
        searchTextbox.clear();
        this.searchTextbox.sendKeys(productName);
    }

    public void clickSearchButton(){
        this.searchButton.click();
    }

    public void clickShoppingCartButton(){
        wait.forElementToBeClickable(15, shoppingCartButton);
        shoppingCartButton.click();
    }

}
