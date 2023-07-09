package org.wave.prj_webpage.test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
import java.lang.reflect.InvocationTargetException;
import java.time.Duration;
import java.util.NoSuchElementException;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author ASUS
 */
public class LoginSystem {

    private static ChromeDriver myBrowser;

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        myBrowser = new ChromeDriver();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        Thread.sleep(3000); //1000 là 1s
        myBrowser.quit();
    }

    @DataProvider(name = "loginData")
    public static Object[] initData() {

        return new Object[][]{
            {"test", "2", "Invalid userID or password"},
            {"Hoadnt", "1", "Hoa Doann"},
            {"admin", "1", "Toi la admin"},};
    }

    
    @Test(dataProvider = "loginData", priority = 1)
    public void testLogin(String userID, String password, String expected) throws InterruptedException {
        myBrowser.get("http://localhost:8080/PE_PRJ301_T4S4_JSTL/login.html");
        myBrowser.manage().window().maximize();
        WebElement txtUserID = myBrowser.findElement(By.xpath("(//input[@type='text'])[1]"));
        txtUserID.sendKeys(userID);
        WebElement txtPassword = myBrowser.findElement(By.xpath("(//input[@name='password'])[1]"));
        txtPassword.sendKeys(password);
        WebElement btnLogin = myBrowser.findElement(By.xpath("(//input[@name='action'])[1]"));
        btnLogin.click();
        Thread.sleep(5000);
        try {
            WebElement error = myBrowser.findElement(By.xpath("(//div[normalize-space()='Invalid userID or password'])[1]"));
            assertEquals(expected, error.getText());
        } catch (Exception e) {
            WebElement txtUserName = myBrowser.findElement(By.cssSelector("body h1"));
            assertEquals(expected + " Information", txtUserName.getText());
            WebElement btnLogout = myBrowser.findElement(By.xpath("(//a[normalize-space()='Log out'])[1]"));
            btnLogout.click();
        }
    }

}
