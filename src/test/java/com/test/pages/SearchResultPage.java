package com.test.pages;

import com.test.basepage.BasePage;
import com.test.infrastructure.driver.WindowHandler;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SearchResultPage extends BasePage{

    private static final String PAGE_TITLE = " - Hepsiburada";
    private static String firstProductTitle;

    @FindBy(className = "searchResultSummaryBar-summary")
    private WebElement searchResultSummaryBar;

    @FindBy(xpath = "//ul[@class='productListContent-wrapper productListContent-grid-0']/li[@id='i0']//a")
    private WebElement firstProductItem;

    public SearchResultPage() {
        PageFactory.initElements(driver, this);
    }

    public void verifyPageTitle(String searchText) {
        wait.forTextToBePresentInElement(10, searchResultSummaryBar, searchText);
        Assert.assertEquals(driver.getTitle(), searchText + PAGE_TITLE);
    }

    public void verifySearchResultsListed(String searchText) {
        Assert.assertTrue(this.searchResultSummaryBar.getText().contains(searchText + " ile ilgili"));
        Assert.assertTrue(this.searchResultSummaryBar.getText().contains(" ürün bulduk"));
    }

    public void selectFirstProductItem(){
        firstProductTitle = this.firstProductItem.getAttribute("title");
        firstProductItem.click();
        WindowHandler.switchToNewTab(this.driver);
        wait.forLoading(15);
    }

    public String getFirstProductItemTitle(){
        return firstProductTitle;
    }

    public String getFirstProductItemLink(){
        return this.firstProductItem.getAttribute("href");
    }



}
