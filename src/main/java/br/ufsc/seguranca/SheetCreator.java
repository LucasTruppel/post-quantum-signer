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

        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 14);
        font.setBold(true);
        headerStyle.setFont(font);

        Cell headerCell;
        for (int n = 0; n < sheetHeaders.length; n++) {
            sheet.setColumnWidth(n, 6000);
            headerCell = header.createCell(n);
            headerCell.setCellValue(sheetHeaders[n]);
            headerCell.setCellStyle(headerStyle);
        }

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);

        Row row;
        Cell cell;
        HashMap<String,Long> signatureResults;
        for (int k = 0; k < algorithms.length; k++) {
            row = sheet.createRow(k+1);
            cell = row.createCell(0);
            cell.setCellValue(algorithms[k]);
            cell.setCellStyle(style);
            cell = row.createCell(1);
            cell.setCellValue((k < 2) ? "false" : "true");
            cell.setCellStyle(style);

            signatureResults = allResults.get(k);
            for (int i = 2; i < sheetHeaders.length; i++) {
                cell = row.createCell(i);
                cell.setCellValue(signatureResults.get(sheetHeaders[i]));
                cell.setCellStyle(style);
            }
        }

        FileOutputStream outputStream = new FileOutputStream("src/main/resources/report.xlsx");
        workbook.write(outputStream);
        workbook.close();

    }

}
