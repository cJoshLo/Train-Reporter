package org.example.service;

import org.example.Alert;
import org.example.configuation.SeleniumConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyController {

    @Autowired
    ScraperService scraperService;

    @Autowired
    SeleniumConfig seleniumConfig;

    @Autowired
    WriteDataToExcel writter;


    public List<WebElement> controlELineAlertScrapper(ChromeDriver driver){
        return scraperService.scrape(driver);
    }

    public synchronized ChromeDriver createDriver(){
        ChromeDriver driver = seleniumConfig.driver();
        return driver;
    }

    public synchronized void waitTime() throws InterruptedException {
        System.out.println("Waiting 3 hours till next run.");
        wait(28800000);// 8 hours
//        wait(10800000);// 3 hours
//        wait(60000);//1 min
    }

    public synchronized void mainWithWait(ChromeDriver driver) throws IOException {
        List<WebElement> alertList = controlELineAlertScrapper(driver);

        List<WebElement> headers = new ArrayList<>();
        List<WebElement> subheaders = new ArrayList<>();
        List<WebElement> bodyContents = new ArrayList<>();

        for(int i = 0; i < alertList.size(); i ++){
        WebElement singleAlert = alertList.get(i);
        WebElement header = singleAlert.findElement(By.className("Heading-a3mmj6-0"));
        WebElement subheader = singleAlert.findElement(By.className("RiderAlert___StyledHeading2-sc-1763m0z-8"));
        WebElement bodyContent = singleAlert.findElement(By.className("RiderAlert___StyledDiv3-sc-1763m0z-9"));
        headers.add(header);
        subheaders.add(subheader);
        bodyContents.add(bodyContent);
    }

    //create newAlerts excel file
        writter.writeToExcel(headers, subheaders, bodyContents);

    //full list of new alerts, already compared with old alerts file
    List<Alert> alertsToSend = writter.compareExcelFiles();
        if(!alertsToSend.isEmpty()) {
        SendSMS sendSMS = new SendSMS();
        sendSMS.sendSMS(alertsToSend);
    }
    //saves the new alerts in the old alerts file so it can be compared later
        writter.saveOverOldAlerts();
    }
}
