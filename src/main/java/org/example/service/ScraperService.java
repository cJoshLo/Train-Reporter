package org.example.service;

import org.example.configuation.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScraperService {

    public static final String URL = "https://app.rtd-denver.com/route/E/alerts";

    public void scrape(){
        SeleniumConfig seleniumConfig = new SeleniumConfig();
        ChromeDriver driver = seleniumConfig.driver();
        driver.get(URL);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        final List<WebElement> alerts = driver.findElements(By.className("RouteRiderAlerts___StyledDiv3-sc-13vycjo-4"));
        final List<WebElement> alertList = alerts.getFirst().findElements(By.tagName("ul"));
        alertList.forEach(alert -> System.out.println(alert.getText()));
        driver.quit();
    }
}
