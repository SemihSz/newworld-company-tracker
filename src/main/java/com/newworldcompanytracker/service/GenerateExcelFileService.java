package com.newworldcompanytracker.service;

import com.newworldcompanytracker.entity.CompanyMemberGearEntity;
import com.newworldcompanytracker.model.ExcelResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * Created by Semih, 28.09.2020
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GenerateExcelFileService {


    public int generateExcel(List<CompanyMemberGearEntity> response, String excelName, String [] excelCell) throws IOException {

        int status = 0;
        try {

            log.info("Start to create excel file...");
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) +  excelName;
            try (FileOutputStream outputStream = new FileOutputStream(fileLocation)) {

                final Workbook workbook = new XSSFWorkbook();

                final Sheet sheet = workbook.createSheet("Turkey-List");
                log.info("Start to create sheet...");
                for (int i = 0; i <= 6; i++) {
                    sheet.setColumnWidth(i, 6000);
                }
                final CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
                headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                Row header = sheet.createRow(0);
                for (int i = 0; i < excelCell.length; i++) {
                    final Cell headerCell = header.createCell(i);
                    headerCell.setCellValue(excelCell[i]);
                    headerCell.setCellStyle(headerStyle);
                }
                int rowCount = 1;
                log.info("Adding all list of element for row and column");
                for (CompanyMemberGearEntity info : response) {
                    final Row row = sheet.createRow(++rowCount);
                    row.createCell(0).setCellValue(info.getCompanyName());
                    row.createCell(1).setCellValue(info.getUsername());
                    row.createCell(2).setCellValue(info.getBuildNumber());
                }
                workbook.write(outputStream);
                status = 200;
                log.info("Excel Status: {}", status);
            }
        }
        catch (IOException e) {
            log.error(e.getMessage());
            status = 404;
            log.error("Excel Status: {}", status);
        }
        return status;
    }

    public ExcelResponse byteArray(List<CompanyMemberGearEntity> response, String [] excelCell) throws IOException {
        ByteArrayInputStream byteArray = null;
        int status = 0;
        try {

            log.info("Start to create excel file...");

            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {

                final Workbook workbook = new XSSFWorkbook();

                final Sheet sheet = workbook.createSheet("Gear Score ");
                sheet.autoSizeColumn(40);
                log.info("Start to create sheet...");
                for (int i = 0; i <= 40; i++) {
                    sheet.setColumnWidth(i, 5000);
                }

                final Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 11);
                headerFont.setColor(IndexedColors.RED.getIndex());

                final CellStyle headerStyle = workbook.createCellStyle();
                headerStyle.setWrapText(true);
                headerStyle.setFont(headerFont);
                headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                headerStyle.setAlignment(HorizontalAlignment.CENTER);

                Row header = sheet.createRow(0);
                for (int i = 0; i < excelCell.length; i++) {

                    final Cell headerCell = header.createCell(i);
                    headerCell.setCellValue(excelCell[i]);
                    headerCell.setCellStyle(headerStyle);
                }

                int rowCount = 1;
                log.info("Adding all list of element for row and column");

                final CellStyle dataStyle = workbook.createCellStyle();
                dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                dataStyle.setAlignment(HorizontalAlignment.CENTER);

                int i = 0;
                for (CompanyMemberGearEntity info : response) {

                    final Row row = sheet.createRow(++rowCount);
                    generateCell(i, info.getCompanyName().toString() ,row, workbook);
                    generateCell(i+1, info.getUsername().toString() ,row, workbook);
                    generateCell(i+2, info.getBuildNumber().toString() ,row, workbook);
                    generateCell(i+3, info.getGearScore().toString() ,row, workbook);
                    generateCell(i+4, info.getDescription().toString() ,row, workbook);
                    generateCell(i+5, info.getMainBuildName().toString() ,row, workbook);
                    generateCell(i+6, info.getStats().toString(), row, workbook);
                    generateCell(i+7, info.getGearHeadPerks().toString() ,row, workbook);
                    generateCell(i+8, info.getGearChestPerks().toString() ,row, workbook);
                    generateCell(i+9, info.getGearGlovesPerks().toString() ,row, workbook);
                    generateCell(i+10, info.getGearLegPerks().toString() ,row, workbook);
                    generateCell(i+11, info.getGearFootPerks().toString() ,row, workbook);
                    generateCell(i+12, info.getGearAmuletPerks().toString() ,row, workbook);
                    generateCell(i+13, info.getGearRingPerks().toString() ,row, workbook);
                    generateCell(i+14, info.getGearEarringPerks().toString() ,row, workbook);
                    generateCell(i+15, info.getGearWeaponFirstPerks().toString() ,row, workbook);
                    generateCell(i+16, info.getGearWeaponSecondPerks().toString() , row, workbook);
                    generateCell(i+17, info.getIsWarBuild().toString(), row, workbook);

                }
                workbook.write(outputStream);
                byteArray = new ByteArrayInputStream(outputStream.toByteArray());
                status = 200;
                log.info("Excel Status: {}", status);
            }
        }
        catch (IOException e) {
            log.error(e.getMessage());
            status = 404;
            log.error("Excel Status: {}", status);
        }
        return ExcelResponse.builder()
                .byteArrayInputStream(byteArray)
                .status(status).build();
    }

    public void generateCell(int i, String info, Row row, Workbook workbook) {

        final Font rowfont = workbook.createFont();
        rowfont.setBold(true);
        rowfont.setFontHeightInPoints((short) 8);
        rowfont.setColor(IndexedColors.BLACK.getIndex());
        rowfont.setFontName("Times New Roman");

        final CellStyle rowStyle = workbook.createCellStyle();
        rowStyle.setWrapText(true);
        rowStyle.setFont(rowfont);
        rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        rowStyle.setAlignment(HorizontalAlignment.CENTER);

        Cell cll = row.createCell(i);
        cll.setCellValue(info);
        cll.setCellStyle(rowStyle);


    }
}

