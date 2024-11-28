package com.example.bookshop.mapper;

import com.example.bookshop.dto.ProductTagDTO;
import com.example.bookshop.entity.Product;
import com.example.bookshop.entity.ProductTag;
import com.example.bookshop.entity.Tag;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-25T12:27:15+0700",
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

        productTagDTO.setProductId( productTagProductId( productTag ) );
        productTagDTO.setTagId( productTagTagId( productTag ) );
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

    private Integer productTagProductId(ProductTag productTag) {
        if ( productTag == null ) {
            return null;
        }
        Product product = productTag.getProduct();
        if ( product == null ) {
            return null;
        }
        Integer id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer productTagTagId(ProductTag productTag) {
        if ( productTag == null ) {
            return null;
        }
        Tag tag = productTag.getTag();
        if ( tag == null ) {
            return null;
        }
        Integer id = tag.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
