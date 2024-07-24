package com.example.bookshop.mapper;

import com.example.bookshop.dto.ProductTagDTO;
import com.example.bookshop.entity.ProductTag;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-23T00:50:41+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ProductTagMapperImpl implements ProductTagMapper {

    @Override
    public ProductTagDTO toDTO(ProductTag productTag) {
        if ( productTag == null ) {
            return null;
        }

        ProductTagDTO productTagDTO = new ProductTagDTO();

        productTagDTO.setId( productTag.getId() );

        return productTagDTO;
    }

    @Override
    public ProductTag toEntity(ProductTagDTO productTagDTO) {
        if ( productTagDTO == null ) {
            return null;
        }

        ProductTag productTag = new ProductTag();

        productTag.setId( productTagDTO.getId() );

        return productTag;
    }
}
