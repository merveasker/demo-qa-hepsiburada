package com.test.pages;

import com.test.Seller.Seller;
import com.test.basepage.BasePage;
import com.test.infrastructure.driver.WindowHandler;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;


public class CheckOutPage extends BasePage {

    @FindBy(className = "delete_all_2uTds")
    private WebElement deleteSelectedItemsButton;

    @FindBy(className = "red_3m-Kl")
    private WebElement deleteSelectedItemsConfirmButton;

    @FindBy(xpath = "//section[@id='onboarding_item_list']//li")
    private List<WebElement> onboardingItems;

    @FindBy(id = "basket-item-count")
    private WebElement basketItemCount;

    private static final String CHECK_OUT_PAGE = "https://checkout.hepsiburada.com/sepetim";


    public CheckOutPage() {
        PageFactory.initElements(driver, this);
    }

    public int controlItemsCountInTheCart() {
        wait.forLoading(10);
        return Integer.parseInt(basketItemCount.getText());
    }

    public boolean isItemsCountRightInTheCart(int itemCountForEachProduct) {
        boolean result = true;
        for (WebElement item : onboardingItems) {
            if (! (itemCountForEachProduct == Integer.parseInt(item.findElement(By.xpath(".//div[@class='container_HX1zs']//input")).getAttribute("value")))) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isItemsProductNameRightInTheCart(String productName) {
        boolean result = true;
        for (WebElement item : onboardingItems) {
            if (!productName.equals(item.findElement(By.xpath(".//div[@class='product_name_3Lh3t']//a")).getText())) {
                result = false;
                break;
            }
        }
        return result;
    }

    public boolean isItemsProductSellerRightInTheCart(List<Seller> sellerList) {
        boolean result = true;
        String itemsSellerName;
        for (int i = 0; i < onboardingItems.size(); i++) {
            //System.out.println(onboardingItems.get(i).findElement(By.xpath(".//div[@class='merchant_name_1NA4w']//a")).getText());
            itemsSellerName = onboardingItems.get(i).findElement(By.xpath(".//div[@class='merchant_name_1NA4w']//a")).getText();
            if (!itemsSellerName.equals(sellerList.get(i).getName())) {
                result = false;
                break;
            }
        }
        return result;
    }

    public void emptyTheCart(){
        driver.get(CHECK_OUT_PAGE);
        wait.forLoading(10);
        deleteSelectedItemsButton.click();
        deleteSelectedItemsConfirmButton.click();
    }


}
