package com.juaracoding;

import com.juaracoding.ujianselenium.CartPage;
import com.juaracoding.ujianselenium.CheckoutPage;
import com.juaracoding.ujianselenium.InventoryPage;
import com.juaracoding.ujianselenium.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CheckoutTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addFirstItemToCart();
        inventoryPage.goToCart();
        cartPage.checkout();
    }

    @Test
    public void testCheckout() {
        delay(2);
        checkoutPage.fillInformationAndContinue("riri", "riri", "12345");
        delay(2);
        checkoutPage.finishCheckout();
        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-complete"));
        // Pastikan bahwa halaman "thank you for order" muncul
        Assert.assertTrue(checkoutPage.isOrderCompleteMessageDisplayed());
        driver.quit();
    }


    private static void delay(long detik) {
        try {
            Thread.sleep(1000 * detik);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
