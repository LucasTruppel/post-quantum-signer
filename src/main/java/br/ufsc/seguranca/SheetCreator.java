package br.ufsc.seguranca;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SheetCreator {

    private static final String[] sheetHeaders = {"Algorithm", "isPostQuantum", "executionTimes", "signatureSize",
            "publicKeySize", "privateKeySize", "keyGenTimeMean", "keyGenTimeStandardDeviation", "signatureTimeMean",
            "signatureTimeStandardDeviation", "verifyTimeMean", "verifyTimeStandardDeviation"};

    public static void createSheet(List<HashMap<String,Long>> allResults, String[] algorithms) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Signatures comparison");
        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.MEDIUM);
        headerStyle.setBorderLeft(BorderStyle.MEDIUM);
        headerStyle.setBorderRight(BorderStyle.MEDIUM);
        headerStyle.setBorderTop(BorderStyle.MEDIUM);

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 14);
        headerStyle.setFont(font);

        sheet.setColumnWidth(0, 9000);
        for (int n = 1; n < sheetHeaders.length; n++) {
            sheet.setColumnWidth(n, 6000);
        }

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        Row row;
        Cell cell;
        for (int i = 0; i < sheetHeaders.length; i++) {
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(sheetHeaders[i]);
            cell.setCellStyle(headerStyle);
            for (int j = 1; j < algorithms.length + 1; j++) {
                cell = row.createCell(j);
                if (i == 0) {
                    cell.setCellValue(algorithms[j-1]);
                } else if (i == 1) {
                    cell.setCellValue((j < 3) ? "false" : "true");
                } else {
                    cell.setCellValue(allResults.get(j-1).get(sheetHeaders[i]));
                }
                cell.setCellStyle(style);
            }
        }

        FileOutputStream outputStream = new FileOutputStream("src/main/resources/report.xlsx");
        workbook.write(outputStream);
        workbook.close();

    }

}
