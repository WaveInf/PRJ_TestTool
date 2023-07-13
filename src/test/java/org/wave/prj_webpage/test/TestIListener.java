/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package org.wave.prj_webpage.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 *
 * @author ASUS
 */
public class TestIListener implements ITestListener {

    /*
    IAnnotationTransformer
    IExecutionListener
    IHookable
    IInvokedMethodListener
    IMethodInterceptor
    IReporter
    ISuiteListener
    ITestListener
     */
    
    @Override
    public void onTestStart(ITestResult result) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Test " + result.getName() + " started at " + dtf.format(now));
//        Reporter.log("Test " + result.getName() + " started at " + dtf.format(now));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
// TODO Auto-generated method stub  
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(result.getStatus() + "of " + result.getName() + " case at " + dtf.format(now));
//        Reporter.log("Status of " + result.getName() + " is " + result.getStatus());
    }

    @Override
    public void onTestFailure(ITestResult result) {
// TODO Auto-generated method stub  
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Failure of " + result.getName() + " case at " + dtf.format(now));
//        Reporter.log("Status of " + result.getName() + " is " + result.getStatus());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
// TODO Auto-generated method stub  
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Skip of " + result.getName() + " case at " + dtf.format(now));
        Reporter.log("Status of " + result.getName() + " is " + result.getStatus());
    }

}
