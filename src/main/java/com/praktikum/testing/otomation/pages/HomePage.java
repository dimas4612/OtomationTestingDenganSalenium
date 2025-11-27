package com.praktikum.testing.otomation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(className = "header-logo")
    private WebElement logo;

    @FindBy(id = "small-searchterms")
    private WebElement searchBox;

    @FindBy(css = "input[value='Search']")
    private WebElement searchButton;

    @FindBy(className = "product-item")
    private List<WebElement> products;

    // Locator yang lebih spesifik untuk tombol add to cart di homepage
    @FindBy(css = ".product-box-add-to-cart-button")
    private List<WebElement> addToCartButtons;

    @FindBy(id = "topcartlink")
    private WebElement cartLink;

    @FindBy(className = "cart-qty")
    private WebElement cartQuantity;

    @FindBy(className = "ico-login")
    private WebElement loginLink;

    @FindBy(className = "ico-register")
    private WebElement registerLink;

    @FindBy(className = "ico-account")
    private WebElement accountLink;

    @FindBy(className = "ico-logout")
    private WebElement logoutLink;

    @FindBy(className = "product-title")
    private List<WebElement> productTitles;

    @FindBy(className = "result")
    private WebElement searchResult;

    // Elemen notifikasi hijau
    @FindBy(id = "bar-notification")
    private WebElement notificationBar;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void goToHomePage() {
        driver.get("http://demowebshop.tricentis.com/");
        waitForVisible(logo);
    }

    public void search(String keyword) {
        enterText(searchBox, keyword);
        click(searchButton);
    }

    public void addToCart(int productIndex) {
        if (productIndex >= 0 && productIndex < addToCartButtons.size()) {
            scrollToElement(addToCartButtons.get(productIndex)); // Scroll dulu
            click(addToCartButtons.get(productIndex));

            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bar-notification")));
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("bar-notification")));
            } catch (Exception e) {
                System.out.println("Warning: Notification bar sync issue");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        }
    }

    public void goToCart() {
        // Scroll ke atas karena tombol cart ada di header
        scrollToElement(cartLink);
        click(cartLink);
    }

    public String getCartItemCount() {
        try {
            return getText(cartQuantity).replaceAll("[()]", ""); // Hapus tanda kurung
        } catch (Exception e) {
            return "0";
        }
    }

    public boolean isUserLoggedIn() {
        return isDisplayed(accountLink);
    }

    public int getSearchResultCount() {
        // Gunakan findElements langsung untuk menghindari Timeout jika 0 hasil
        return products.size();
    }

    public String getProductTitle(int index) {
        if (index >= 0 && index < productTitles.size()) {
            return getText(productTitles.get(index));
        }
        return "";
    }

    public void clickProduct(int index) {
        if (index >= 0 && index < productTitles.size()) {
            click(productTitles.get(index));
        }
    }

    public String getSearchMessage() {
        try {
            waitForVisible(searchResult);
            return getText(searchResult);
        } catch (Exception e) {
            return "";
        }
    }
}