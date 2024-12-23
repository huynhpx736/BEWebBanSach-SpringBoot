package com.example.bookshop.repository;

import com.example.bookshop.entity.ImportReportDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImportReportDetailRepository extends JpaRepository<ImportReportDetail, Integer> {

    List<ImportReportDetail> findByImportReportId(Integer importReportId);
    //hàm trả về danh sách các chi tiết nhập hàng chứa sản phẩm có id
    @Query(value = "SELECT * FROM import_reportdetail WHERE product_id = ?1", nativeQuery = true)
    List<ImportReportDetail> findIPDetailByProductId(Integer productId);

    //hàm trả về danh sách các chi tiết nhập hàng chứa sản phẩm có tên chứa từ khóa
    @Query(value = "SELECT * FROM import_reportdetail id JOIN products p ON id.product_id = p.id WHERE p.title LIKE %?1%", nativeQuery = true)
    List<ImportReportDetail> findIPDetailByProductNameContaining(String productName);
}
