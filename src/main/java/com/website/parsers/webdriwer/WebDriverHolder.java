package com.website.parsers.webdriwer;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverHolder {
    private WebDriver webDriver;
    private volatile static WebDriverHolder instance;



    public WebDriverHolder() {

        WebDriverManager.chromedriver().setup();

        this.webDriver = new ChromeDriver();
    }

    public static WebDriverHolder getInstance() {
        if (instance == null) {
            synchronized (WebDriverHolder.class) {
                if (instance == null)
                    instance = new WebDriverHolder();
            }
        }
        return instance;
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }


}
