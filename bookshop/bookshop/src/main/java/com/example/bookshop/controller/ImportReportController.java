package com.example.bookshop.controller;

import com.example.bookshop.dto.ImportReportDTO;
import com.example.bookshop.dto.ImportReportDetailDTO;
import com.example.bookshop.entity.ImportReportDetail;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.ImportReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/import-reports")
public class ImportReportController {

    //TODO: Implement ImportReportController
    @Autowired
    private ImportReportService importReportService;

    @GetMapping("/get-by-product-id/{productId}")
    public ResponseEntity<ResponseData> getImportReportByProductId(@PathVariable int productId) {
        List<ImportReportDTO> importReports = importReportService.findIPByImportDetailsProductId(productId);
        return ResponseEntity.ok(new ResponseData(200, "Success", importReports, true));
    }
    @GetMapping("/get-detail/{importReportId}")
    public ResponseEntity<ResponseData> getImportReportDetailByImportReportId(@PathVariable int importReportId) {
        List<ImportReportDetailDTO> importReportDetails = importReportService.getImportReportDetailByImportReportId(importReportId);
        return ResponseEntity.ok(new ResponseData(200, "Success", importReportDetails, true));
    }
    @PostMapping("/add-item")
    public ResponseEntity<ResponseData> addItemToImportReport(@RequestParam int importReportId, @RequestParam int productId, @RequestParam int quantity, @RequestParam int price) {
        importReportService.addItemToImportReport(importReportId, productId, quantity, price);
        return ResponseEntity.ok(new ResponseData(200, "Item added to import report", null, true));
    }
    @GetMapping("/get-all")
    public ResponseEntity<ResponseData> getAllImportReports() {
        List<ImportReportDTO> importReports = importReportService.getAllImportReport();
        return ResponseEntity.ok(new ResponseData(200, "Success", importReports, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getImportReportById(@PathVariable int id) {
        ImportReportDTO importReport = importReportService.getImportReportById(id);
        if (importReport != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", importReport, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Import report not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createImportReport(@RequestBody ImportReportDTO importReportDTO) {
        ImportReportDTO createdImportReport = importReportService.createImportReport(importReportDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "Import report created", createdImportReport, true));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseData> updateImportReport(@PathVariable int id, @RequestBody ImportReportDTO importReportDTO) {
        ImportReportDTO updatedImportReport = importReportService.updateImportReport(id, importReportDTO);
        if (updatedImportReport != null) {
            return ResponseEntity.ok(new ResponseData(200, "Import report updated", updatedImportReport, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Import report not found", null, false));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseData> deleteImportReport(@PathVariable int id) {
        importReportService.deleteImportReport(id);
        return ResponseEntity.ok(new ResponseData(200, "Import report deleted", null, true));
    }


}
