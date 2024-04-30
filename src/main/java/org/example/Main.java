package org.example;

import org.example.configuation.SeleniumConfig;
import org.example.service.MyController;
import org.example.service.WriteDataToExcel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        SeleniumConfig seleniumConfig = new SeleniumConfig();
        ChromeDriver driver = seleniumConfig.driver();
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

        for(int i = 0; i < headers.size(); i ++){
            System.out.println("Header: " + headers.get(i).getText());
            System.out.println("SubHeader: " + subheaders.get(i).getText());
            System.out.println("Body content: " + bodyContents.get(i).getText());
            WriteDataToExcel writter = new WriteDataToExcel();
            writter.writeToExcel(headers, subheaders, bodyContents);
        }



        driver.quit();

    }
}


