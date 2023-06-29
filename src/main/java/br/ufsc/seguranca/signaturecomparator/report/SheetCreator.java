package br.ufsc.seguranca.signaturecomparator.report;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SheetCreator {

    private static final String[] resultKeys = {"Algorithm", "isPostQuantum", "executionTimes", "signatureSize",
            "publicKeySize", "privateKeySize", "keyGenTimeMean", "keyGenTimeStandardDeviation", "signatureTimeMean",
            "signatureTimeStandardDeviation", "verifyTimeMean", "verifyTimeStandardDeviation"};
    private static final String[] sheetHeader = {"Algorithm", "Is post-quantum?", "Execution times",
            "Signature size (B)", "Public key size (B)", "Private key size (B)", "Keys gen time mean (ns)",
            "Keys gen time standard deviation (ns)", "Signature time mean (ns)",
            "Signature time standard deviation (ns)", "Verify time mean (ns)", "Verify time standard deviation (ns)"};

    public static void createSheet(List<HashMap<String,Long>> allResults, String[] algorithms) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Signatures comparison");

        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.MEDIUM);
        headerStyle.setBorderLeft(BorderStyle.MEDIUM);
        headerStyle.setBorderRight(BorderStyle.MEDIUM);
        headerStyle.setBorderTop(BorderStyle.MEDIUM);
        headerStyle.setWrapText(true);

        XSSFFont headerFont = workbook.createFont();
        headerFont.setFontName("Arial");
        headerFont.setFontHeightInPoints((short) 14);
        headerStyle.setFont(headerFont);

        sheet.setColumnWidth(0, 9000);
        for (int n = 1; n < resultKeys.length; n++) {
            sheet.setColumnWidth(n, 6000);
        }

        CellStyle style = workbook.createCellStyle();
        style.setWrapText(true);
        style.setAlignment(HorizontalAlignment.RIGHT);
        XSSFFont font = workbook.createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        style.setFont(font);

        Row row;
        Cell cell;
        for (int i = 0; i < resultKeys.length; i++) {
            row = sheet.createRow(i);
            row.setHeight((short) 750);
            cell = row.createCell(0);
            cell.setCellValue(sheetHeader[i]);
            cell.setCellStyle(headerStyle);
            for (int j = 1; j < algorithms.length + 1; j++) {
                cell = row.createCell(j);
                if (i == 0) {
                    cell.setCellValue(algorithms[j-1]);
                    cell.setCellStyle(headerStyle);
                } else if (i == 1) {
                    cell.setCellValue((j < 3) ? "false" : "true");
                    cell.setCellStyle(style);
                } else {
                    cell.setCellValue(allResults.get(j-1).get(resultKeys[i]));
                    cell.setCellStyle(style);
                }
            }
        }

        FileOutputStream outputStream = new FileOutputStream("src/main/resources/report.xlsx");
        workbook.write(outputStream);
        workbook.close();

    }

}
