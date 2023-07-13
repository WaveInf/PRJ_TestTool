/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package org.wave.prj_webpage.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import static org.testng.Assert.*;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 *
 * @author ASUS
 */
public class SkipExTest {   
     
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
    
    @DataProvider(name = "creditCard")
    public static Object[][] creditData() {
        return new Object[][]{
            {"4888195599927818", "590", "09", "2027"},
//            {"4589515783450776", "384", "07", "2025"},
        };
    }
    
    @Test(dataProvider = "creditCard")
    public void testLimit(String num, String CVV, String month, String year) throws InterruptedException {
        boolean check = true;
        myBrowser.get("https://demo.guru99.com/payment-gateway/purchasetoy.php");
        myBrowser.manage().window().maximize();
        WebElement btnCheck = myBrowser.findElement(By.xpath("/html[1]/body[1]/header[1]/div[1]/nav[1]/a[3]"));
        btnCheck.click();
        Thread.sleep(3000);
        WebElement txtCheck = myBrowser.findElement(By.name("card_nmuber"));
        txtCheck.sendKeys(num);
        WebElement btnSubmit = myBrowser.findElement(By.xpath("(//input[@name='submit'])[1]"));
        btnSubmit.click();
        Thread.sleep(1000);
        WebElement txtLimit = myBrowser.findElement(By.cssSelector("div[class='table-wrapper'] h4 span"));
        int Limit = Integer.parseInt(txtLimit.getText());
        if(Limit <= 0){
            check = false;
        }
        if(!check){
            throw new SkipException("Not enough balance");
        }
        System.out.println("Positive Balance Check");
    }
    
    @Test(dataProvider = "creditCard", dependsOnMethods = "testLimit", invocationCount = 5)
    public void testBuy(String num, String CVV, String month, String year) throws InterruptedException{
        myBrowser.get("https://demo.guru99.com/payment-gateway/purchasetoy.php");
        myBrowser.manage().window().maximize();
        WebElement btnBuy = myBrowser.findElement(By.xpath("(//input[@value='Buy Now'])[1]"));
        btnBuy.click();
        Thread.sleep(3000);
        WebElement txtNum = myBrowser.findElement(By.name("card_nmuber"));
        txtNum.sendKeys(num);
        Select txtMonth = new Select(myBrowser.findElement(By.name("month")));
        txtMonth.selectByVisibleText(month);
        Select txtYear = new Select(myBrowser.findElement(By.name("year")));
        txtYear.selectByVisibleText(year);
        WebElement txtCVV = myBrowser.findElement(By.name("cvv_code"));
        txtCVV.sendKeys(CVV);
        WebElement btnSubmit = myBrowser.findElement(By.xpath("(//input[@name='submit'])[1]"));
        btnSubmit.click();
        String check = myBrowser.findElement(By.xpath("(//h2[normalize-space()='Payment successfull!'])[1]")).getText();
        assertEquals(check, "Payment successful");
    }
}
