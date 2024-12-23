package com.example.bookshop.mapper;
import com.example.bookshop.dto.ImportReportDetailDTO;
import com.example.bookshop.entity.ImportReportDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ImportReportDetailMapper {
    ImportReportDetailMapper INSTANCE = Mappers.getMapper(ImportReportDetailMapper.class);
    @Mapping(target = "importReportId", source = "importReportDetail.importReport.id")
    @Mapping(target = "productId", source = "importReportDetail.product.id")
    @Mapping(target = "image", source = "importReportDetail.product.image")
    @Mapping(target = "productName", source = "importReportDetail.product.title")

    ImportReportDetailDTO toDTO(ImportReportDetail importReportDetail);

    @Mapping(target = "importReport.id", source = "importReportDetailDTO.importReportId")
    @Mapping(target = "product.id", source = "importReportDetailDTO.productId")
    ImportReportDetail toEntity(ImportReportDetailDTO importReportDetailDTO);
}
