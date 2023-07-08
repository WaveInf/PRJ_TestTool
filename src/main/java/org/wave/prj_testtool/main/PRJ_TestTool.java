/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package org.wave.prj_testtool.main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 *
 * @author ASUS
 */
public class PRJ_TestTool {

    public static void main(String[] args) throws InterruptedException {
        playWithGoogleSearch();
    }
    
    public static void playWithGoogleSearch() throws InterruptedException{
        WebDriver myBrowser; //đây là biến object sẽ trỏ vào 1 tab trình duyệt
                            // mà lát đổi do new()
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        //tải phẩn mềm trung gian vào trong RAM, nếu coi browser là data base
        //thì JDBC tương đương với WebDriver giúp giao tiếp với CSDL/browser
                
        //Hàm Class.forName(); JDBC, load caí driver vào trong JUnit
        
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--incognito");
        opt.addArguments("--lang=ja-JP");
        //opt.addArguments("");
        
        myBrowser = new ChromeDriver(opt);
        myBrowser.get("https://google.com");
        myBrowser.manage().window().maximize();
        
        //ta đi định vị 1 cái tag trong myBrowser
        //mỗi tag đc xem là 1 object, UI object; ta định vị tag
        //bằng findElement by()
                                            //Chuỗi định vị 1 tag
                                            //where ra 1 cái tag trong
                                            //cây DOM
        WebElement searchBox = myBrowser.findElement(By.name("q"));
        searchBox.sendKeys("tiền học đại học fpt");        
        
        searchBox.submit();
        
        searchBox = myBrowser.findElement(By.xpath("(//h3[contains(text(),'Học phí - Trường Đại học FPT')])[1]"));
        searchBox.click();
        searchBox = myBrowser.findElement(By.id("onesignal-slidedown-cancel-button"));
        searchBox.click();        
        searchBox = myBrowser.findElement(By.xpath("(//div[@class='widget-preview--btn-close'])[1]"));
        searchBox.click();     
        searchBox = myBrowser.findElement(By.xpath("(//img[@alt='chat button'])[1]"));
        searchBox.click();    
        searchBox = myBrowser.findElement(By.xpath("(//span[@class='widget-header--button-close-icon'])[1]"));
        searchBox.click(); 
        searchBox.submit();
        myBrowser.close();
    }
    
}

