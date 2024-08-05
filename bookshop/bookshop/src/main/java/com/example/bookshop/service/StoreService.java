package com.example.bookshop.service;

import org.springframework.web.multipart.MultipartFile;

public interface StoreService {
    String uploadFile(MultipartFile file);
    byte[] downloadFile(String fileName);
    String deleteFile(String fileName);
}
