package com.example.bookshop.service.impl;

import com.example.bookshop.dto.ImportReportDTO;
import com.example.bookshop.dto.ImportReportDetailDTO;
import com.example.bookshop.entity.ImportReport;
import com.example.bookshop.entity.ImportReportDetail;
import com.example.bookshop.entity.Product;
import com.example.bookshop.mapper.ImportReportDetailMapper;
import com.example.bookshop.mapper.ImportReportMapper;
import com.example.bookshop.repository.ImportReportDetailRepository;
import com.example.bookshop.repository.ImportReportRepository;
import com.example.bookshop.repository.ProductRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.ImportReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportReportServiceImpl implements ImportReportService {

    @Autowired
    ImportReportRepository importReportRepository;
    @Autowired
    ImportReportMapper importReportMapper;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImportReportDetailRepository importReportDetailRepository;
    @Autowired
    ImportReportDetailMapper importReportDetailMapper;
    @Autowired
    UserRepository userRepository;


    @Override
    public List<ImportReportDTO> findIPByImportDetailsProductId(Integer productId) {
        return importReportRepository.findIPByImportDetailsProductId(productId).stream()
                .map(importReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ImportReportDetailDTO> getImportReportDetailByImportReportId(Integer importReportId) {
        return importReportDetailRepository.findByImportReportId(importReportId).stream()
                .map(importReportDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addItemToImportReport(Integer importReportId, Integer productId, Integer quantity, Integer price) {
        ImportReport importReport = importReportRepository.findById(importReportId).orElseThrow(() -> new RuntimeException("Import report not found"));
        Product product = productRepository.findById(productId).
                orElseThrow(() -> new RuntimeException("Product not found"));
        ImportReportDetail importReportDetail = new ImportReportDetail();
        importReportDetail.setImportReport(importReport);
        importReportDetail.setProduct(product);
        importReportDetail.setQuantity(quantity);
        importReportDetail.setPrice(price);
        importReportDetailRepository.save(importReportDetail);

        //cập nhật lại so luong san pham trong kho
        product.setSalesVolume(product.getSalesVolume() + quantity);
        productRepository.save(product);

    }


    @Override
    public List<ImportReportDTO> getAllImportReport() {
        return importReportRepository.findAll().stream()
                .map(importReportMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ImportReportDTO getImportReportById(Integer id) {
        return importReportRepository.findById(id)
                .map(importReportMapper::toDTO)
                .orElse(null);
    }

    @Override
    public ImportReportDTO createImportReport(ImportReportDTO importReportDTO) {
        if (importReportDTO.getUser() == null) {
            importReportDTO.setUser(userRepository.findById(1).get());
        }
        ImportReport importReport = importReportMapper.toEntity(importReportDTO);
        return importReportMapper.toDTO(importReportRepository.save(importReport));

    }

    @Override
    public ImportReportDTO updateImportReport(Integer id, ImportReportDTO importReportDTO) {
        if (importReportRepository.existsById(id)) {
            ImportReport importReport = importReportMapper.toEntity(importReportDTO);
            importReport.setId(id);
            return importReportMapper.toDTO(importReportRepository.save(importReport));
        }
        return null;
    }


    @Override
    public void deleteImportReport(Integer id) {
        importReportRepository.deleteById(id);
    }



}

