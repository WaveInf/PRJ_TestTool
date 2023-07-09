/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package org.wave.prj_webpage.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 *
 * @author ASUS
 */
public class SearchSystem {
    
    private static WebDriver myBrowser;   
    
    
    @BeforeClass
    @Parameters("browser")
    public static void setUpClass(@Optional("chrome")String browser) throws Exception {
        switch(browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                myBrowser = new ChromeDriver();
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "msedgedriver.exe");
                myBrowser = new EdgeDriver();
        }
        
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
         Thread.sleep(3000); //1000 là 1s
        myBrowser.quit();
    }
    
    @DataProvider(name="updateData")
     public static Object[] initData(){
        
        return new Object[][]{
                                {"Phuoc Hoa","AD"},
                                {"Thai Binh","US"},
                                
        };
    }
     
    @Parameters("url")
    @Test(priority = 1, groups = "function")
    public void testSearch(@Optional("http://localhost:8080/PE_PRJ301_T4S4_JSTL/login.html")String url) throws InterruptedException{
        System.out.println("Test 1");
        myBrowser.get("http://localhost:8080/PE_PRJ301_T4S4_JSTL/login.html");
        myBrowser.manage().window().maximize();
        WebElement txtUserID = myBrowser.findElement(By.xpath("(//input[@type='text'])[1]"));
        txtUserID.sendKeys("admin");        
        WebElement txtPassword= myBrowser.findElement(By.xpath("(//input[@name='password'])[1]"));
        txtPassword.sendKeys("1");
        WebElement btnLogin = myBrowser.findElement(By.xpath("(//input[@name='action'])[1]"));
        btnLogin.click();
        Thread.sleep(2000);       
        WebElement btnSearch = myBrowser.findElement(By.xpath("(//input[@value='Search'])[1]"));
        btnSearch.click();
        Thread.sleep(1000);       
        System.out.println(myBrowser.findElement(By.xpath("(//table)[1]")).isDisplayed());
    }
    
    @Test(dataProvider="updateData", priority = 2, groups = {"function","edit"})
    public void testEdit(String userName, String role) throws InterruptedException{
        System.out.println("Test 2");
        WebElement txtUserName = myBrowser.findElement(By.xpath("(//td)[24]")).findElement(By.name("fullName"));        
        txtUserName.clear();
        txtUserName.sendKeys(userName);    
        WebElement txtRole = myBrowser.findElement(By.xpath("(//td)[25]")).findElement(By.name("roleID"));
        txtRole.clear();
        txtRole.sendKeys(role);
        WebElement btnUpdate = myBrowser.findElement(By.xpath("//tbody/tr[6]/td[1]")).findElement(By.xpath("(//button[@name='action'][normalize-space()='Update'])[4]"));
        btnUpdate.click();
        Thread.sleep(2000);
        WebElement txtSearch = myBrowser.findElement(By.cssSelector("form[method='post'] input[name='search']"));
        txtSearch.sendKeys(userName);        
        WebElement btnSearch1 = myBrowser.findElement(By.cssSelector("input[value='Search']"));
        btnSearch1.click();
        Thread.sleep(2000);
        WebElement txtTestName = myBrowser.findElement(By.xpath("(//td)[3]")).findElement(By.name("fullName"));
        WebElement txtTestRole = myBrowser.findElement(By.xpath("(//td)[4]")).findElement(By.name("roleID"));
        assertEquals(txtTestName.getAttribute("value"), userName);
        assertEquals(txtTestRole.getAttribute("value"), role);
        Thread.sleep(1000);
        WebElement txtSearch1 = myBrowser.findElement(By.cssSelector("form[method='post'] input[name='search']"));
        txtSearch1.clear();       
        WebElement btnSearch2 = myBrowser.findElement(By.cssSelector("input[value='Search']"));
        btnSearch2.click();
    }
}
