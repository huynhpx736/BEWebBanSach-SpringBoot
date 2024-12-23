package com.example.bookshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportReportDetailDTO {
    private Integer id;
    private Integer importReportId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer productId;
    private Integer quantity;
    private Integer price;
    private String image;
    private String productName;

}
