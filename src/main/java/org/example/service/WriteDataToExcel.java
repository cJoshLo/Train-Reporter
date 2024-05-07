package org.example.service;
import java.io.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.Alert;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WriteDataToExcel {

    public void writeToExcel(List<WebElement> headers, List<WebElement> subheaders, List<WebElement> bodyContents) throws IOException {

        // workbook object
        XSSFWorkbook workbook = new XSSFWorkbook();

        // spreadsheet object
        XSSFSheet spreadsheet = workbook.createSheet("E-line Alerts");

        // creating a row object
        XSSFRow row;

        // This data needs to be written (Object[])
        Map<String, Object[]> alertData = new TreeMap<String, Object[]>();

        for(int i = 0; i < headers.size(); i ++) {
            alertData.put(Integer.toString(i+1), new Object[]{headers.get(i).getText(), subheaders.get(i).getText(), bodyContents.get(i).getText()});
        }

        Set<String> keyid = alertData.keySet();
        int rowid = 0;

        // writing the data into the sheets
            for (String key : keyid) {
            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = alertData.get(key);
            int cellid = 0;
            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }
        FileOutputStream out = new FileOutputStream(new File(System.getenv("Alerts") + "/newAlerts.xlsx"));
            workbook.write(out);
            out.close();
    }

    public List<Alert> compareExcelFiles() throws IOException {
        FileInputStream oldFile = new FileInputStream(new File(System.getenv("Alerts") + "/oldAlerts.xlsx"));
        FileInputStream newfile = new FileInputStream(new File(System.getenv("Alerts") + "/newAlerts.xlsx"));

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook oldWorkbook = new XSSFWorkbook(oldFile);
        XSSFWorkbook newWorkbook = new XSSFWorkbook(newfile);


        //Get first/desired sheet from the workbook
        XSSFSheet oldsheet = oldWorkbook.getSheetAt(0);
        XSSFSheet newsheet = newWorkbook.getSheetAt(0);

        //Iterate through each rows one by one
        Iterator<Row> oldrowIterator = oldsheet.iterator();
        Iterator<Row> newrowIterator = newsheet.iterator();

        List<org.example.Alert> alertsToSend = new ArrayList<>();
        Boolean found = false;

        while (newrowIterator.hasNext()) {
            found = false;
            Row newrow = newrowIterator.next();
            Cell newBodyContent = newrow.getCell(2);
            oldrowIterator = oldsheet.iterator();
            while (oldrowIterator.hasNext()){
                Row oldrow = oldrowIterator.next();
                Cell oldBodyContent = oldrow.getCell(2);
                if(oldBodyContent.getStringCellValue().equals(newBodyContent.getStringCellValue())){
                    found = true;
                }
            }
            if(!found){
                //save the alert to be sent out
                org.example.Alert toSend =
                        new org.example.Alert(newrow.getCell(0).getStringCellValue(),newrow.getCell(1).getStringCellValue(),newrow.getCell(2).getStringCellValue());
                alertsToSend.add(toSend);
            }
        }
        oldFile.close();
        newfile.close();
        return alertsToSend;
    }

    public void saveOverOldAlerts() throws IOException {
        FileInputStream file = new FileInputStream(new File(System.getenv("Alerts") + "/newAlerts.xlsx"));
        Workbook wb = WorkbookFactory.create(file);
        FileOutputStream fileout = new FileOutputStream(System.getenv("Alerts") + "/oldAlerts.xlsx");
        wb.write(fileout);
        fileout.close();
    }
}
