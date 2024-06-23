package com.juaracoding;

import com.juaracoding.ujianselenium.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][] {
                {"standard_user", "secret_sauce", true},
                {"locked_out_user", "secret_sauce", false},
                {"problem_user", "secret_sauce", true},
                {"performance_glitch_user", "secret_sauce", true},
                {"invalid_user", "invalid_password", false}
        };
    }

    @Test(dataProvider = "loginData")
    public void testLogin(String username, String password, boolean isSuccessExpected) {
        loginPage.login(username, password);
        if (isSuccessExpected) {
            Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
        } else {
            Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        }
        driver.get("https://www.saucedemo.com/"); // Reset to login page

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
