package com.example.bookshop.mapper;

import com.example.bookshop.dto.ImportReportDetailDTO;
import com.example.bookshop.entity.ImportReport;
import com.example.bookshop.entity.ImportReportDetail;
import com.example.bookshop.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-24T03:46:48+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ImportReportDetailMapperImpl implements ImportReportDetailMapper {

    @Override
    public ImportReportDetailDTO toDTO(ImportReportDetail importReportDetail) {
        if ( importReportDetail == null ) {
            return null;
        }

        ImportReportDetailDTO importReportDetailDTO = new ImportReportDetailDTO();

        importReportDetailDTO.setImportReportId( importReportDetailImportReportId( importReportDetail ) );
        importReportDetailDTO.setProductId( importReportDetailProductId( importReportDetail ) );
        importReportDetailDTO.setImage( importReportDetailProductImage( importReportDetail ) );
        importReportDetailDTO.setProductName( importReportDetailProductTitle( importReportDetail ) );
        importReportDetailDTO.setId( importReportDetail.getId() );
        importReportDetailDTO.setCreatedAt( importReportDetail.getCreatedAt() );
        importReportDetailDTO.setUpdatedAt( importReportDetail.getUpdatedAt() );
        importReportDetailDTO.setQuantity( importReportDetail.getQuantity() );
        importReportDetailDTO.setPrice( importReportDetail.getPrice() );

        return importReportDetailDTO;
    }

    @Override
    public ImportReportDetail toEntity(ImportReportDetailDTO importReportDetailDTO) {
        if ( importReportDetailDTO == null ) {
            return null;
        }

        ImportReportDetail importReportDetail = new ImportReportDetail();

        importReportDetail.setImportReport( importReportDetailDTOToImportReport( importReportDetailDTO ) );
        importReportDetail.setProduct( importReportDetailDTOToProduct( importReportDetailDTO ) );
        importReportDetail.setId( importReportDetailDTO.getId() );
        importReportDetail.setQuantity( importReportDetailDTO.getQuantity() );
        importReportDetail.setPrice( importReportDetailDTO.getPrice() );
        importReportDetail.setCreatedAt( importReportDetailDTO.getCreatedAt() );
        importReportDetail.setUpdatedAt( importReportDetailDTO.getUpdatedAt() );

        return importReportDetail;
    }

    private Integer importReportDetailImportReportId(ImportReportDetail importReportDetail) {
        if ( importReportDetail == null ) {
            return null;
        }
        ImportReport importReport = importReportDetail.getImportReport();
        if ( importReport == null ) {
            return null;
        }
        Integer id = importReport.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer importReportDetailProductId(ImportReportDetail importReportDetail) {
        if ( importReportDetail == null ) {
            return null;
        }
        Product product = importReportDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        Integer id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String importReportDetailProductImage(ImportReportDetail importReportDetail) {
        if ( importReportDetail == null ) {
            return null;
        }
        Product product = importReportDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String image = product.getImage();
        if ( image == null ) {
            return null;
        }
        return image;
    }

    private String importReportDetailProductTitle(ImportReportDetail importReportDetail) {
        if ( importReportDetail == null ) {
            return null;
        }
        Product product = importReportDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String title = product.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }

    protected ImportReport importReportDetailDTOToImportReport(ImportReportDetailDTO importReportDetailDTO) {
        if ( importReportDetailDTO == null ) {
            return null;
        }

        ImportReport importReport = new ImportReport();

        importReport.setId( importReportDetailDTO.getImportReportId() );

        return importReport;
    }

    protected Product importReportDetailDTOToProduct(ImportReportDetailDTO importReportDetailDTO) {
        if ( importReportDetailDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( importReportDetailDTO.getProductId() );

        return product;
    }
}
