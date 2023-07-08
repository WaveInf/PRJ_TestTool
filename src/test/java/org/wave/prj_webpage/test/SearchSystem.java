/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package org.wave.prj_webpage.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author ASUS
 */
public class SearchSystem {
    
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
    
    @DataProvider(name="updateData")
     public static Object[] initData(){
        
        return new Object[][]{
                                {"Phuoc Hoa","AD"},
                                {"Thai Binh","US"},
                                
        };
    }
     
    @Test(dataProvider="updateData",priority = 2)
    public void testSearch(String userName, String role) throws InterruptedException{
        // Open google and navigate to website
        myBrowser.get("http://localhost:8080/PE_PRJ301_T4S4_JSTL/login.html");
        myBrowser.manage().window().maximize();
        
        // Locate user box and input accordingly
        WebElement txtUserID = myBrowser.findElement(By.xpath("(//input[@type='text'])[1]"));
        txtUserID.sendKeys("admin");        
        
        // Locate password box and input accordingly
        WebElement txtPassword= myBrowser.findElement(By.xpath("(//input[@name='password'])[1]"));
        txtPassword.sendKeys("1");
        
        // Locate and press Login button
        WebElement btnLogin = myBrowser.findElement(By.xpath("(//input[@name='action'])[1]"));
        btnLogin.click();
        Thread.sleep(2000);
        
        //Locate and press search button
        WebElement btnSearch = myBrowser.findElement(By.xpath("(//input[@value='Search'])[1]"));
        btnSearch.click();
        Thread.sleep(1000);
        
        // Clear name box and input new name
        WebElement txtUserName = myBrowser.findElement(By.xpath("(//td)[24]")).findElement(By.name("fullName"));        
        txtUserName.clear();
        txtUserName.sendKeys(userName);  
        
        // CLear role box and input new role
        WebElement txtRole = myBrowser.findElement(By.xpath("(//td)[25]")).findElement(By.name("roleID"));
        txtRole.clear();
        txtRole.sendKeys(role);
        
        // Click update button
        WebElement btnUpdate = myBrowser.findElement(By.xpath("//tbody/tr[6]/td[1]")).findElement(By.xpath("(//button[@name='action'][normalize-space()='Update'])[4]"));
        btnUpdate.click();
        Thread.sleep(2000);
        
        // Locate search box and input name
        WebElement txtSearch = myBrowser.findElement(By.cssSelector("form[method='post'] input[name='search']"));
        txtSearch.sendKeys(userName);  
        
        // Locate and press search button
        WebElement btnSearch1 = myBrowser.findElement(By.cssSelector("input[value='Search']"));
        btnSearch1.click();
        Thread.sleep(2000);
        
        // Compare
        WebElement txtTestName = myBrowser.findElement(By.xpath("(//td)[3]")).findElement(By.name("fullName"));
        WebElement txtTestRole = myBrowser.findElement(By.xpath("(//td)[4]")).findElement(By.name("roleID"));
        assertEquals(txtTestName.getAttribute("value"), userName);
        assertEquals(txtTestRole.getAttribute("value"), role);
    }
    
}
