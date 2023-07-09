/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.wave.prj_webpage.test;

import org.testng.annotations.DataProvider;

public class LoginDetails {
    @DataProvider
    public static Object[] loginData() {

        return new Object[][]{
            {"test", "2", "Invalid userID or password"},
            {"Hoadnt", "1", "Hoa Doan"},
            {"admin", "1", "Toi la admin"},
        };
    }
}
