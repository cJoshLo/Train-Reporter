package org.example;

import org.example.configuation.SeleniumConfig;
import org.example.service.MyController;
import org.example.service.SendSMS;
import org.example.service.WriteDataToExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        SeleniumConfig seleniumConfig = new SeleniumConfig();
        ChromeDriver driver = seleniumConfig.driver();
        while(true){
            Main main = new Main();
            main.waitTime();
            main.mainWithWait(driver);
        }
    }
    public synchronized void waitTime() throws InterruptedException {
        wait(28800000);
        LocalTime currentTime = LocalTime.now();
        System.out.println("Running, current time: " + currentTime);
    }

    public synchronized void mainWithWait(ChromeDriver driver) throws IOException {
        MyController controller = new MyController();
        List<WebElement> alertList = controller.controlELineAlertScrapper(driver);

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

        WriteDataToExcel writter = new WriteDataToExcel();

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


