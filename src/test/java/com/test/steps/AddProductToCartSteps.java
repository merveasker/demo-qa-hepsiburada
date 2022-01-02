package com.test.steps;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.test.Seller.Seller;
import com.test.infrastructure.driver.Setup;
import com.test.infrastructure.driver.WindowHandler;
import com.test.pages.*;
import com.test.product.Product;
import com.test.user.User;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.server.handler.DeleteSession;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

public class AddProductToCartSteps {

    private HomePage homePage;
    private LoginPage loginPage;
    private SearchResultPage searchPageResult;
    private User user;
    private Product product;
    private ProductDetailPage productDetailPage;
    private CheckOutModalPage checkOutModalPage;
    private List<Seller> sellerList;
    private SuggestedProductsPage suggestedProductsPage;
    private CheckOutPage checkOutPage;

    public AddProductToCartSteps() {
        this.homePage = new HomePage();
        this.loginPage = new LoginPage();
        this.searchPageResult = new SearchResultPage();
        this.productDetailPage = new ProductDetailPage();
        this.checkOutModalPage = new CheckOutModalPage();
        this.suggestedProductsPage = new SuggestedProductsPage();
        this.sellerList = new ArrayList<Seller>();
        this.checkOutPage = new CheckOutPage();
    }

    @Given("Merve has a valid credentials for Hepsiburada")
    public void userHasAValidCredentialsForHepsiburada() {
        user = new User("Merve", "Asker", "demo-qa-mail@yopmail.com", ".Abc12345");
    }

    @Given("User visits Hepsiburada")
    public void userVisitsHepsiburada() {
        homePage.visitUrl();
        homePage.verifyPageTitle();
    }

    @And("Navigates to Login Page")
    public void navigatesToLoginPage() throws InterruptedException {
        homePage.hoverLoginOrSignUp();
        homePage.clickLoginLink();
        loginPage.verifyPageTitle();
    }

    @When("She logs in to Hepsiburada with valid credentials")
    public void sheLogsInToHepsiburadaWithValidCredentials() {
        loginPage.login(user.getEmail(), user.getPassword());
    }

    @Then("She can see her own account")
    public void sheCanSeeHerOwnAccount() {
        homePage.verifyUserName(user.getFullName());
    }

    @When("She searches for {string}")
    public void sheSearchesFor(String searchText) {
        homePage.searchProduct(searchText);
        homePage.clickSearchButton();
    }

    @Then("Search page should be updated with the {string} list")
    public void searchPageShouldBeUpdatedWithTheList(String productName) {
        searchPageResult.verifyPageTitle(productName);
        searchPageResult.verifySearchResultsListed(productName);
    }


    @When("She selects a {string} product from the search results")
    public void sheSelectsAProductFromTheSearchResults(String productName) {
        searchPageResult.selectFirstProductItem();
        this.product = new Product(productName, searchPageResult.getFirstProductItemTitle(), homePage.getHomePage() + searchPageResult.getFirstProductItemTitle());
    }

    @Then("She can see product's details")
    public void sheCanSeeProductSDetails() {
        Assert.assertEquals(this.product.getTitle(), searchPageResult.getFirstProductItemTitle());
    }

    @When("She adds the product to the cart with default selected seller")
    public void sheAddsTheProductToTheCartWithDefaultSelectedSeller() {
        this.product = new Product("", searchPageResult.getFirstProductItemTitle(), homePage.getHomePage() + searchPageResult.getFirstProductItemTitle());

        Seller seller = new Seller(productDetailPage.getDefaultSellerName());
        sellerList.add(seller);
        this.productDetailPage.clickAddToCartButton();
        System.out.println("Other Seller: " + !productDetailPage.getOtherSellers().isEmpty());
        if (!productDetailPage.getOtherSellers().isEmpty()) {
            checkOutModalPage.waitForModalOpen();
            this.checkOutModalPage.waitForModalReady();
            Assert.assertTrue(this.productDetailPage.checkIfProductAddedSuccessfully());
        }
    }

    @And("If there is an another seller she adds same product with another seller to her cart")
    public void ifThereIsAnAnotherSellerSheAddsSameProductWithAnotherSellerToHerCart() {
        if (!productDetailPage.getOtherSellers().isEmpty()) {
            checkOutModalPage.clickModalCloseButton();
            Seller seller = new Seller(productDetailPage.getOtherFirstSellersName());
            sellerList.add(seller);
            this.productDetailPage.clickAddCartForOtherFirstSeller();
            this.suggestedProductsPage.clickDenySuggestedProductButton();
            this.checkOutModalPage.waitForModalReady();
            Assert.assertTrue(this.productDetailPage.checkIfProductAddedSuccessfully());
        }
    }

    @And("She goes to her cart")
    public void sheGoesToHerCart() {
        if (checkOutModalPage.checkIfPageShown()) {
            checkOutModalPage.clickGoToCartButton();
        } else {
            homePage.clickShoppingCartButton();
        }
    }

    @Then("She sees her cart contains all the products that she added")
    public void sheSeesHerCartContainsAllTheProductsThatSheAdded() {
        Assert.assertEquals(checkOutPage.controlItemsCountInTheCart(), sellerList.size());
        Assert.assertTrue(checkOutPage.isItemsCountRightInTheCart(1));
        Assert.assertTrue(checkOutPage.isItemsProductNameRightInTheCart(this.product.getTitle()));
        Assert.assertTrue(checkOutPage.isItemsProductSellerRightInTheCart(this.sellerList));
    }

    @Given("She empties the cart")
    public void sheEmptiesTheCart() {
        checkOutPage.emptyTheCart();
    }

    @After("@quit")
    public void quit() {
        if (Setup.driver != null) {
            Setup.driver.quit();
        }
        Setup.driver = null;

    }
}

