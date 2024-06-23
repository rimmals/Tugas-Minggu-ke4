package com.juaracoding;

import com.juaracoding.ujianselenium.InventoryPage;
import com.juaracoding.ujianselenium.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddToCartTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private InventoryPage inventoryPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        loginPage.login("standard_user", "secret_sauce");
    }

    @Test
    public void testAddToCart() {
        inventoryPage.addFirstItemToCart();
        inventoryPage.goToCart();
        delay(2);
        Assert.assertTrue(driver.getCurrentUrl().contains("cart"));
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
