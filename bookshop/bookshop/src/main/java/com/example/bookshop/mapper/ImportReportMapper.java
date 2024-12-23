package com.example.bookshop.mapper;
import com.example.bookshop.dto.ImportReportDTO;
import com.example.bookshop.entity.ImportReport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImportReportMapper {
    ImportReportMapper INSTANCE = Mappers.getMapper(ImportReportMapper.class);
    ImportReportDTO toDTO(ImportReport importReport);
    ImportReport toEntity(ImportReportDTO importReportDTO);
}
