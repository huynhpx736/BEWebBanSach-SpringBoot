package com.example.bookshop.service;

import com.example.bookshop.dto.ImportReportDetailDTO;

import java.util.List;

public interface ImportReportDetailService {
    List<ImportReportDetailDTO> findIPDetailByProductId(Integer productId);
    //CRUD
    List<ImportReportDetailDTO> getAllImportReportDetail();
    ImportReportDetailDTO getImportReportDetailById(Integer id);
    ImportReportDetailDTO createImportReportDetail(ImportReportDetailDTO importReportDetailDTO);
    ImportReportDetailDTO updateImportReportDetail(Integer id,ImportReportDetailDTO importReportDetailDTO);
    void deleteImportReportDetail(Integer id);

}
