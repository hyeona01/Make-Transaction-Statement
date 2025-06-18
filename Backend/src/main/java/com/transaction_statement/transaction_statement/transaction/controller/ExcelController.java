package com.transaction_statement.transaction_statement.transaction.controller;

import com.transaction_statement.transaction_statement.transaction.dto.ExcelTransactionDto;
import com.transaction_statement.transaction_statement.transaction.service.ExcelService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @PostMapping("/transaction-statement")
    public ResponseEntity<byte[]> generateExcel(@RequestBody ExcelTransactionDto dto) {
        try {
            byte[] excelBytes = excelService.generateTransactionStatementExcel(dto);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "transaction-statement.xlsx");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
