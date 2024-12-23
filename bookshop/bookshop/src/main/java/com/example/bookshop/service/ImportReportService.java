package com.example.bookshop.service;

import com.example.bookshop.dto.ImportReportDTO;
import com.example.bookshop.dto.ImportReportDetailDTO;
import com.example.bookshop.entity.ImportReportDetail;

import java.util.List;

public interface ImportReportService {

    List <ImportReportDTO> findIPByImportDetailsProductId(Integer productId);
    List<ImportReportDetailDTO> getImportReportDetailByImportReportId(Integer importReportId);
    void addItemToImportReport(Integer importReportId, Integer productId, Integer quantity, Integer price);
    List<ImportReportDTO> getAllImportReport();
    ImportReportDTO getImportReportById(Integer id);
    ImportReportDTO createImportReport(ImportReportDTO importReportDTO);
    ImportReportDTO updateImportReport(Integer id, ImportReportDTO importReportDTO);
     void deleteImportReport(Integer id);

}
