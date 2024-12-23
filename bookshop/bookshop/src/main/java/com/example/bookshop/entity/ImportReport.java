package com.example.bookshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "import_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImportReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", nullable = false, updatable = false, insertable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", nullable = false, insertable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
//    @JoinColumn(name = "created_by", referencedColumnName = "id", nullable = false)
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    private User user;

    // Liên kết với chi tiết phiếu nhập
    @OneToMany(mappedBy = "importReport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImportReportDetail> importReportDetails;
}
