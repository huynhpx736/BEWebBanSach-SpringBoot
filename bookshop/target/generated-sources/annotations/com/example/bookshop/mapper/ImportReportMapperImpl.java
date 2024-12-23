package com.example.bookshop.mapper;

import com.example.bookshop.dto.ImportReportDTO;
import com.example.bookshop.entity.ImportReport;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-24T03:46:49+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ImportReportMapperImpl implements ImportReportMapper {

    @Override
    public ImportReportDTO toDTO(ImportReport importReport) {
        if ( importReport == null ) {
            return null;
        }

        ImportReportDTO importReportDTO = new ImportReportDTO();

        importReportDTO.setId( importReport.getId() );
        importReportDTO.setCreatedAt( importReport.getCreatedAt() );
        importReportDTO.setUpdatedAt( importReport.getUpdatedAt() );
        importReportDTO.setUser( importReport.getUser() );

        return importReportDTO;
    }

    @Override
    public ImportReport toEntity(ImportReportDTO importReportDTO) {
        if ( importReportDTO == null ) {
            return null;
        }

        ImportReport importReport = new ImportReport();

        importReport.setId( importReportDTO.getId() );
        importReport.setCreatedAt( importReportDTO.getCreatedAt() );
        importReport.setUpdatedAt( importReportDTO.getUpdatedAt() );
        importReport.setUser( importReportDTO.getUser() );

        return importReport;
    }
}
