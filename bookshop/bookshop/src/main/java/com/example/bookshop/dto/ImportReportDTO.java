package com.example.bookshop.dto;

import com.example.bookshop.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportReportDTO {
    private Integer id;
//    private String importDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User user;


}
