package com.test.pages;

import com.test.basepage.BasePage;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BasePage{

    private static final String PAGE_TITLE = "Üye Giriş Sayfası & Üye Ol - Hepsiburada";

    @FindBy(xpath = "//a[@href='https://www.hepsiburada.com/']")
    private WebElement hepsiburadaHomePageLink;

    @FindBy(xpath = "//div[text()='Giriş yap']")
    private WebElement loginTab;

    @FindBy(xpath = "//div[text()='Üye ol']")
    private WebElement signUpTab;

    @FindBy(id = "txtUserName")
    private WebElement userNameInput;

    @FindBy(id = "btnLogin")
    private WebElement loginButton;

    @FindBy(xpath = "//span[text()='Yardıma ihtiyacım var']")
    private WebElement help;

    @FindBy(xpath = "//span[text()='Farklı hesap kullan']")
    private WebElement useDifferentAccountLink;

    @FindBy(id = "txtPassword")
    private WebElement passwordInput;

    @FindBy(xpath = "//span[text()='Şifremi unuttum']")
    private WebElement forgotPasswordLink;

    @FindBy(id = "btnEmailSelect")
    private WebElement loginButtonAfterPassword;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void verifyPageTitle() {
        Assert.assertEquals(driver.getTitle(), PAGE_TITLE);
    }

    public void login(String userName, String password){
        wait.forElementToBeClickable(5, userNameInput);
        this.userNameInput.sendKeys(userName);
        loginButton.click();
        wait.forElementToBeClickable(5, passwordInput);
        this.passwordInput.sendKeys(password);
        loginButtonAfterPassword.click();
    }


}
