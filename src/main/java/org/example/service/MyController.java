package org.example.service;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class MyController {
    public List<WebElement> controlELineAlertScrapper(ChromeDriver driver){
        ScraperService scraperService = new ScraperService();
        return scraperService.scrape(driver);
    }
}
