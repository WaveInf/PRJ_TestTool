/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package org.wave.prj_webpage.test;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

/**
 *
 * @author ASUS
 */
public class CreateSystem {

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

    @DataProvider(name = "createData")
    //data provider = với cái serialized testing của JUnit nhưng này thì đc phân biệt bới Name
    public static Object[] createData() {

        return new Object[][]{
            {"SE172989", "Phuc An", "1", "1"},
            {"testUser", "TestUser", "2", "1"},};
    }

    @DataProvider(name = "errorData")
    public static Object[] errorData() {
        return new Object[][]{
            {"S", "se", "2", "1"},
            {"", "", "", ""},};
    }

    //tên groups giống như param id vậy, hàm include với exclude ghi trong file xml sẽ quyết định hàm có id nhất định chạy
    @Test(dataProvider = "createData", groups = "function")
    //hàm test của testng nó cho mình configurate thêm nhiều tính năng
    //còn JUnit thì cần các tag khác
    //dataProvider đây để lấy data theo kiểu DDT
    //priority là điều chỉnh thứ tự test nếu ko để thì java sẽ chạy theo bảng chữ cái
    //đây vd ko để priority thì hàm testError chạy trc hàm testSearch (test 2 chạy trc test 1)
    //có priority thì nó chạy theo thứ tự nhưng priority này chỉ dùng trong cùng 1 test file thôi
    public void testCreate(String userID, String name, String password, String confirm) throws InterruptedException {

        myBrowser.get("http://localhost:8080/PE_PRJ301_T4S4_JSTL/login.html");
        myBrowser.manage().window().maximize();
        WebElement btnCreate = myBrowser.findElement(By.xpath("(//a[normalize-space()='Create Page'])[1]"));
        btnCreate.click();
        Thread.sleep(2000);
        WebElement txtUserID = myBrowser.findElement(By.xpath("(//input[@name='userID'])[1]"));
        txtUserID.sendKeys(userID);
        WebElement txtName = myBrowser.findElement(By.xpath("(//input[@name='fullName'])[1]"));
        txtName.sendKeys(name);
        WebElement txtPassword = myBrowser.findElement(By.xpath("(//input[@name='password'])[1]"));
        txtPassword.sendKeys(password);
        WebElement txtConfirm = myBrowser.findElement(By.xpath("(//input[@name='confirm'])[1]"));
        txtConfirm.sendKeys(confirm);
        WebElement btnCreateUser = myBrowser.findElement(By.xpath("(//input[@value='Create'])[1]"));
        btnCreateUser.click();
        Thread.sleep(2000);
        WebElement txtLoginID = myBrowser.findElement(By.xpath("(//input[@type='text'])[1]"));
        txtLoginID.sendKeys("admin");
        WebElement txtLoginPassword = myBrowser.findElement(By.xpath("(//input[@name='password'])[1]"));
        txtLoginPassword.sendKeys("1");
        WebElement btnLogin = myBrowser.findElement(By.xpath("(//input[@name='action'])[1]"));
        btnLogin.click();
        Thread.sleep(2000);
        WebElement btnSearch = myBrowser.findElement(By.xpath("(//input[@value='Search'])[1]"));
        btnSearch.click();
        Thread.sleep(1000);
        String txtUser = myBrowser.findElement(By.xpath("(//td)[51]")).findElement(By.xpath("/html[1]/body[1]/table[1]/tbody[1]/tr[8]/td[2]/input[1]")).getAttribute("value");
        assertEquals(txtUser, userID);
        WebElement btnDelete = myBrowser.findElement(By.xpath("//body[1]/table[1]/tbody[1]/tr[8]/td[7]/a[1]"));
        btnDelete.click();
        Thread.sleep(2000);
        WebElement btnLogout = myBrowser.findElement(By.xpath("(//a[normalize-space()='Log out'])[1]"));
        btnLogout.click();
        WebElement btnCreate1 = myBrowser.findElement(By.xpath("(//a[normalize-space()='Create Page'])[1]"));
        btnCreate1.click();
        Thread.sleep(2000);
    }

    //@dependsOnMethod sẽ override priority hàm depends sẽ LUÔN chạy sau
    @Test(dataProvider = "errorData", groups = "error", dependsOnMethods = {"testCreate"})
    public void testError(String userID, String name, String password, String confirm) throws InterruptedException { 
        
        WebElement txtUserID = myBrowser.findElement(By.xpath("(//input[@name='userID'])[1]"));
        txtUserID.sendKeys(userID);
        WebElement txtName = myBrowser.findElement(By.xpath("(//input[@name='fullName'])[1]"));
        txtName.sendKeys(name);
        WebElement txtPassword = myBrowser.findElement(By.xpath("(//input[@name='password'])[1]"));
        txtPassword.sendKeys(password);
        WebElement txtConfirm = myBrowser.findElement(By.xpath("(//input[@name='confirm'])[1]"));
        txtConfirm.sendKeys(confirm);
        WebElement btnCreateUser = myBrowser.findElement(By.xpath("(//input[@value='Create'])[1]"));
        btnCreateUser.click();
        Thread.sleep(2000);
        assertEquals(myBrowser.getTitle(),"Create user");
        
    }
}
