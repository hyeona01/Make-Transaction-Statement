package com.transaction_statement.transaction_statement.transaction.service;

import com.transaction_statement.transaction_statement.transaction.dto.ExcelTransactionDto;
import com.transaction_statement.transaction_statement.transaction.dto.ExcelTransactionItemDto;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

@Service
public class ExcelService {

    public byte[] generateTransactionStatementExcel(ExcelTransactionDto dto) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("거래명세표");

            // 스타일 설정
            CellStyle headerStyle = workbook.createCellStyle();
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerStyle.setFont(headerFont);
            headerStyle.setAlignment(HorizontalAlignment.CENTER);
            headerStyle.setBorderTop(BorderStyle.THIN);
            headerStyle.setBorderBottom(BorderStyle.THIN);
            headerStyle.setBorderLeft(BorderStyle.THIN);
            headerStyle.setBorderRight(BorderStyle.THIN);

            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);

            // 제목 행
            Row titleRow = sheet.createRow(0);
            Cell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("거래명세표");
            titleCell.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));

            // 거래처 정보
            Row clientRow1 = sheet.createRow(1);
            clientRow1.createCell(0).setCellValue("거래처명");
            clientRow1.getCell(0).setCellStyle(headerStyle);
            clientRow1.createCell(1).setCellValue(dto.getClientName());
            clientRow1.getCell(1).setCellStyle(cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 3));

            clientRow1.createCell(4).setCellValue("거래일자");
            clientRow1.getCell(4).setCellStyle(headerStyle);
            clientRow1.createCell(5).setCellValue(dto.getTransactionDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            clientRow1.getCell(5).setCellStyle(cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(1, 1, 5, 6));

            Row clientRow2 = sheet.createRow(2);
            clientRow2.createCell(0).setCellValue("사업자번호");
            clientRow2.getCell(0).setCellStyle(headerStyle);
            clientRow2.createCell(1).setCellValue(dto.getBusinessNumber());
            clientRow2.getCell(1).setCellStyle(cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3));

            clientRow2.createCell(4).setCellValue("대표자");
            clientRow2.getCell(4).setCellStyle(headerStyle);
            clientRow2.createCell(5).setCellValue(dto.getRepresentativeName());
            clientRow2.getCell(5).setCellStyle(cellStyle);
            sheet.addMergedRegion(new CellRangeAddress(2, 2, 5, 6));

            // 항목 헤더
            Row headerRow = sheet.createRow(4);
            String[] headers = {"No", "품목", "규격", "수량", "단가", "금액", "비고"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(headerStyle);
            }

            // 항목 데이터
            int rowNum = 5;
            int itemNo = 1;
            for (ExcelTransactionItemDto item : dto.getItems()) {
                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(itemNo++);
                row.getCell(0).setCellStyle(cellStyle);

                row.createCell(1).setCellValue(item.getName());
                row.getCell(1).setCellStyle(cellStyle);

                row.createCell(2).setCellValue(item.getSpecification() != null ? item.getSpecification() : "");
                row.getCell(2).setCellStyle(cellStyle);

                row.createCell(3).setCellValue(item.getQuantity() != null ? item.getQuantity() : 0);
                row.getCell(3).setCellStyle(cellStyle);

                row.createCell(4).setCellValue(item.getUnitPrice() != null ? item.getUnitPrice() : 0);
                row.getCell(4).setCellStyle(cellStyle);

                row.createCell(5).setCellValue(item.getAmount() != null ? item.getAmount() : 0);
                row.getCell(5).setCellStyle(cellStyle);

                row.createCell(6).setCellValue(item.getRemark() != null ? item.getRemark() : "");
                row.getCell(6).setCellStyle(cellStyle);
            }

            // 합계 행
            Row totalRow = sheet.createRow(rowNum + 1);
            Cell totalLabelCell = totalRow.createCell(0);
            totalLabelCell.setCellValue("합계");
            totalLabelCell.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(rowNum + 1, rowNum + 1, 0, 4));

            Cell totalAmountCell = totalRow.createCell(5);
            totalAmountCell.setCellValue(dto.getTotalAmount() != null ? dto.getTotalAmount() : 0);
            totalAmountCell.setCellStyle(headerStyle);

            // 세액 행
            Row taxRow = sheet.createRow(rowNum + 2);
            Cell taxLabelCell = taxRow.createCell(0);
            taxLabelCell.setCellValue("세액");
            taxLabelCell.setCellStyle(headerStyle);
            sheet.addMergedRegion(new CellRangeAddress(rowNum + 2, rowNum + 2, 0, 4));

            Cell taxAmountCell = taxRow.createCell(5);
            taxAmountCell.setCellValue(dto.getTaxAmount() != null ? dto.getTaxAmount() : 0);
            taxAmountCell.setCellStyle(headerStyle);

            // 열 너비 자동 조정
            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }
    }

}
