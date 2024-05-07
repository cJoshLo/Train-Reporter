package org.example.configuation;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Component;

@Component
public class SeleniumConfig {

    public SeleniumConfig(){
        super();
        System.out.println("Created selenium instance");
    }

    public ChromeDriver driver(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        ChromeDriver driver = new ChromeDriver(options);
        System.setProperty("webdriver.chrome.driver","/Users/lopez/bin/chromedriver");
        return driver;
    }
}
