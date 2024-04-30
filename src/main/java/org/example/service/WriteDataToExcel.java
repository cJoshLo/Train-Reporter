package org.example.service;
import java.io.File;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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

        // writing the data into the sheets...
            for (String key : keyid) {

            row = spreadsheet.createRow(rowid++);
            Object[] objectArr = alertData.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String)obj);
            }
        }

        // .xlsx is the format for Excel Sheets...
        // writing the workbook into the file...
        FileOutputStream out = new FileOutputStream(new File("C:/Users/lopez/OneDrive/Desktop/Projects/Development/alerts/GFGsheet.xlsx"));
            workbook.write(out);
            out.close();
    }
}
