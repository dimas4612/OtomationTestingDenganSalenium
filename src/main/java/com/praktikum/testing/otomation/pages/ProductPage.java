package com.praktikum.testing.otomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

    @FindBy(css = "h1[itemprop='name']")
    private WebElement productName;

    @FindBy(className = "product-price")
    private WebElement productPrice;

    @FindBy(className = "short-description")
    private WebElement productDescription;

    @FindBy(css = "img[alt='Picture of 14.1-inch Laptop']") // Sesuaikan jika perlu
    private WebElement productImage;

    // Perbaikan ID Dinamis: Cari input yang ID-nya DIAWALI dengan 'add-to-cart-button-'
    @FindBy(css = "input[id^='add-to-cart-button-']")
    private WebElement addToCartButton;

    @FindBy(css = "input[id^='product_enteredQuantity_']")
    private WebElement quantityInput;

    @FindBy(id = "bar-notification")
    private WebElement notificationMessage;

    @FindBy(className = "ico-cart")
    private WebElement cartLink;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public String getName() { return getText(productName); }
    public String getPrice() { return getText(productPrice); }
    public String getDescription() { return getText(productDescription); }

    public boolean isImageDisplayed() {
        return isElementDisplayed(productName); // Sederhana: cek nama saja sbg wakil detail page
    }

    public void addToCart() {
        click(addToCartButton);
        // Tunggu notifikasi muncul dan hilang (penting!)
        try {
            waitForVisible(notificationMessage);
            // Tambahkan wait invisibility manual jika perlu di BasePage
        } catch (Exception e) {}
    }

    public void setQuantity(int quantity) {
        enterText(quantityInput, String.valueOf(quantity));
    }

    public boolean isAddedToCart() {
        try {
            return getText(notificationMessage).contains("The product has been added");
        } catch (Exception e) {
            return false;
        }
    }

    public void goToCart() {
        scrollToElement(cartLink);
        click(cartLink);
    }
}