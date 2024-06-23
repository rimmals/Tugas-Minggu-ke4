package com.juaracoding.ujianselenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage {
    private WebDriver driver;

    @FindBy(css = ".inventory_item:first-child .btn_inventory")
    private WebElement firstItemAddToCartButton;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLink;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addFirstItemToCart() {
        firstItemAddToCartButton.click();
    }

    public void goToCart() {
        cartLink.click();
    }
}
