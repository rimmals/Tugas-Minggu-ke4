package com.juaracoding.ujianselenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    private WebDriver driver;

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "finish")
    private WebElement finishButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillInformationAndContinue(String fName, String lName, String pCode) {
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        postalCode.sendKeys(pCode);
        continueButton.click();
    }

    public void finishCheckout() {
        finishButton.click();
    }
    public boolean isOrderCompleteMessageDisplayed() {
        return driver.findElement(By.cssSelector("h2.complete-header")).isDisplayed();
    }
}
