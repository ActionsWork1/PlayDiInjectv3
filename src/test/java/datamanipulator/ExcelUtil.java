package datamanipulator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static List<Map<String, String>> getTestData(String filePath, String sheetName) throws IOException, FileNotFoundException {
        List<Map<String, String>> testData = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        // Get header row
        XSSFRow headerRow = sheet.getRow(0);
        int colCount = headerRow.getLastCellNum();

        // Iterate through data rows
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            XSSFRow currentRow = sheet.getRow(i);
            Map<String, String> dataMap = new HashMap<>();

            for (int j = 0; j < colCount; j++) {
                String columnName = headerRow.getCell(j).getStringCellValue();
                XSSFCell cell = currentRow.getCell(j);
                String cellValue = cell != null ? cell.toString() : "";
                dataMap.put(columnName, cellValue);
            }

            testData.add(dataMap);
        }
        workbook.close();
        fis.close();
        return testData;
    }
}