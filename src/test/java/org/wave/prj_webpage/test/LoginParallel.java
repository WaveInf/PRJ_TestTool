/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package org.wave.prj_webpage.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author ASUS
 */
public class LoginParallel {

    private static ChromeDriver myBrowser;
    private static EdgeDriver myEdge;

    @DataProvider(name = "loginData1")
    public static Object[] loginData1() {

        return new Object[][]{
            {"Hoadnt", "1"},
        };
    }

    @DataProvider(name = "loginData2")
    public static Object[] loginData2() {

        return new Object[][]{
            {"admin", "1"},
        };
    }

    @Test(dataProvider = "loginData1")
    public void testChromeParallel(String userID, String password) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        myBrowser = new ChromeDriver();
        myBrowser.get("http://localhost:8080/PE_PRJ301_T4S4_JSTL/login.html");
        Thread.sleep(1000);
        myBrowser.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(userID);
        Thread.sleep(1000);
        myBrowser.findElement(By.xpath("(//input[@name='password'])[1]")).sendKeys(password);
        WebElement btnLogin = myBrowser.findElement(By.xpath("(//input[@name='action'])[1]"));
        btnLogin.click();
        Thread.sleep(3000);
        assertNotEquals(myBrowser.getTitle(), "Login Page");
        myBrowser.quit();
    }

    @Test(dataProvider = "loginData2")
    public void testEdgeParallel(String userID, String password) throws InterruptedException {
        System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
        myEdge = new EdgeDriver();
        myEdge.get("http://localhost:8080/PE_PRJ301_T4S4_JSTL/login.html");
        Thread.sleep(1000);
        myEdge.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(userID);
        Thread.sleep(1000);
        myEdge.findElement(By.xpath("(//input[@name='password'])[1]")).sendKeys(password);
        WebElement btnLogin = myEdge.findElement(By.xpath("(//input[@name='action'])[1]"));
        btnLogin.click();
        Thread.sleep(3000);
        assertNotEquals(myEdge.getTitle(), "Login Page");
        myEdge.quit();
    }
}
