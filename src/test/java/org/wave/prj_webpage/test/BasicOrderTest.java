/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package org.wave.prj_webpage.test;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author ASUS
 */
public class BasicOrderTest {

    @Test
    public void testC() {
        System.out.println("Test 1");
    }

    @Test
    public void testA() {
        System.out.println("Test2");
    }

    @Test
    public void testB() {
        System.out.println("Test 3");
    }

    int count = 0;

    @Test(invocationCount = 5, successPercentage = 50)
    public void a() {
        count++;
        System.out.println("Invoked count : " + count);

        if (count % 6 == 0) {
            System.out.println("false");
            assertTrue(false);
        } else {
            System.out.println("true");
            assertTrue(true);
        }
    }
}

