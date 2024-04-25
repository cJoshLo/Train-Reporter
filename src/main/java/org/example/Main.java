package org.example;

import org.example.service.MyController;
import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {
        MyController controller = new MyController();
        controller.controlELineAlertScrapper();
    }
}


