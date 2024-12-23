package com.example.bookshop.repository;

import com.example.bookshop.entity.ImportReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImportReportRepository extends JpaRepository<ImportReport, Integer> {

    //hàm trả về danh sách các báo cáo nhập hàng có chi tiết nhập hàng chứa sản phẩm có id
//    @Query("SELECT ir FROM ImportReport ir WHERE ir.importProductDetails.product.id = ?1")
    @Query(value = "SELECT * FROM import_report ir JOIN import_reportdetail id ON ir.id = id.import_report_id JOIN products p ON id.product_id = p.id WHERE p.id = ?1", nativeQuery = true)
    List<ImportReport> findIPByImportDetailsProductId(Integer productId);

    @Query(value = "SELECT * FROM import_report ir JOIN import_reportdetail id ON ir.id = id.import_report_id JOIN products p ON id.product_id = p.id WHERE p.title LIKE %?1%", nativeQuery = true)
    List<ImportReport> findIPByProductNameContaining(String keyword);
}