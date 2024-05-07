package org.example;

import org.example.service.MyController;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.time.LocalTime;


@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        ConfigurableApplicationContext context = SpringApplication.run(Main.class,args);
        MyController controller = context.getBean(MyController.class);
        ChromeDriver driver = controller.createDriver();
        while(true){
            LocalTime currentTime = LocalTime.now();
            System.out.println("Running, current time: " + currentTime);
            controller.mainWithWait(driver);
            controller.waitTime();
        }
    }
}


