package com.example.bookshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import com.example.bookshop.dto.ImportReportDetailDTO;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.ImportReportDetailService;

import java.util.List;
@RequestMapping("/api/import-report-details")
@RestController
public class ImportReportDetailController {
    @Autowired
    private ImportReportDetailService importReportDetailService;

    @GetMapping("/get-by-product-id/{productId}")
    public ResponseEntity<ResponseData> getImportReportDetailByProductId(@PathVariable int productId) {
        List<ImportReportDetailDTO> importReportDetails = importReportDetailService.findIPDetailByProductId(productId);
        return ResponseEntity.ok(new ResponseData(200, "Success", importReportDetails, true));
    }
    @GetMapping("/get-all")
    public ResponseEntity<ResponseData> getAllImportReportDetails() {
        List<ImportReportDetailDTO> importReportDetails = importReportDetailService.getAllImportReportDetail();
        return ResponseEntity.ok(new ResponseData(200, "Success", importReportDetails, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getImportReportDetailById(@PathVariable int id) {
        ImportReportDetailDTO importReportDetail = importReportDetailService.getImportReportDetailById(id);
        if (importReportDetail != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", importReportDetail, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Import report detail not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createImportReportDetail(@RequestBody ImportReportDetailDTO importReportDetailDTO) {
        ImportReportDetailDTO createdImportReportDetail = importReportDetailService.createImportReportDetail(importReportDetailDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "Import report detail created", createdImportReportDetail, true));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> updateImportReportDetail(@PathVariable int id, @RequestBody ImportReportDetailDTO importReportDetailDTO) {
        ImportReportDetailDTO updatedImportReportDetail = importReportDetailService.updateImportReportDetail(id, importReportDetailDTO);
        if (updatedImportReportDetail != null) {
            return ResponseEntity.ok(new ResponseData(200, "Import report detail updated", updatedImportReportDetail, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Import report detail not found", null, false));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseData> deleteImportReportDetail(@PathVariable int id) {
        importReportDetailService.deleteImportReportDetail(id);
        return ResponseEntity.ok(new ResponseData(200, "Import report detail deleted", null, true));
    }

}
