package com.example.bookshop.service.impl;

import com.example.bookshop.dto.ImportReportDetailDTO;
import com.example.bookshop.mapper.ImportReportDetailMapper;
import com.example.bookshop.repository.ImportReportDetailRepository;
import com.example.bookshop.repository.ImportReportRepository;
import com.example.bookshop.repository.ProductRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.ImportReportDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImportReportDetailServiceImpl implements ImportReportDetailService {


    @Autowired
    private ImportReportDetailRepository importReportDetailRepository;
    @Autowired
    private ImportReportDetailMapper importReportDetailMapper;

    @Autowired
    private ImportReportRepository importReportRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<ImportReportDetailDTO> findIPDetailByProductId(Integer productId) {
        return importReportDetailRepository.findIPDetailByProductId(productId).stream()
                .map(importReportDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ImportReportDetailDTO> getAllImportReportDetail() {
        return importReportDetailRepository.findAll().stream()
                .map(importReportDetailMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Override
    public ImportReportDetailDTO getImportReportDetailById(Integer id) {
        return importReportDetailRepository.findById(id)
                .map(importReportDetailMapper::toDTO)
                .orElse(null);
    }
    @Override
    public ImportReportDetailDTO createImportReportDetail(ImportReportDetailDTO importReportDetailDTO) {
        return importReportDetailMapper.toDTO(importReportDetailRepository.save(importReportDetailMapper.toEntity(importReportDetailDTO)));
    }
    @Override
    public ImportReportDetailDTO updateImportReportDetail(Integer id, ImportReportDetailDTO importReportDetailDTO) {
       if (importReportDetailRepository.existsById(id)) {
            importReportDetailDTO.setId(id);
            return importReportDetailMapper.toDTO(importReportDetailRepository.save(importReportDetailMapper.toEntity(importReportDetailDTO)));
        }
        return null;
    }

    @Override
    public void deleteImportReportDetail(Integer id) {
        importReportDetailRepository.deleteById(id);
    }
}
