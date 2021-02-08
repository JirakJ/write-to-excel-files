package com.jakubjirak.excel;

import com.jakubjirak.beans.WorkSheet;
import com.jakubjirak.utils.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.LocalDate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ExcelWriter {
    public static void main(String[] args) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Leden 2021");

        List<WorkSheet> rows = new ArrayList<>();
        DateUtils dateUtils = new DateUtils();
        List<LocalDate> dates = dateUtils.getDatesWithoutWeekDates(new LocalDate(2021,01,01),new LocalDate(2021,01,31));

        for (LocalDate date:
             dates) {
            rows.add(new WorkSheet(date,8,"h","just work"));
        }


        Object[][] bookData = {
                {"Date","Count","Units","Description"},
        };


        int rowCount = 0;

        for (Object[] field: bookData) {
            Row row = sheet.createRow(rowCount++);

            int cellCount = 0;
            for (Object nestedField: field) {
                Cell cell = row.createCell(cellCount++);
                if(nestedField instanceof String){
                    cell.setCellValue((String) nestedField);
                } else if (nestedField instanceof Integer){
                    cell.setCellValue((Integer) nestedField);
                }
            }

            for (WorkSheet nestedCell: rows) {
                row = sheet.createRow(rowCount++);
                cellCount = 0;
                for (Object nestedField: nestedCell.toObject()) {
                    Cell cell = row.createCell(cellCount++);
                    if(nestedField instanceof String){
                        cell.setCellValue((String) nestedField);
                    } else if (nestedField instanceof Integer){
                        cell.setCellValue((Integer) nestedField);
                    }
                }
            }

        }

        try{
            FileOutputStream fileOutputStream = new FileOutputStream("ExcelFromJava.xlsx");
            workbook.write(fileOutputStream);
            fileOutputStream.close();

            boolean result = Files.deleteIfExists(Path.of("ExcelFromJava.xlsx"));
            System.out.printf("Deleted: %b%n", result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
