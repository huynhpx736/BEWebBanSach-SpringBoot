package com.example.bookshop.mapper;

import com.example.bookshop.dto.AuthorProductDTO;
import com.example.bookshop.entity.AuthorProduct;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-23T00:50:41+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class AuthorProductMapperImpl implements AuthorProductMapper {

    @Override
    public AuthorProductDTO toDTO(AuthorProduct authorProduct) {
        if ( authorProduct == null ) {
            return null;
        }

        AuthorProductDTO authorProductDTO = new AuthorProductDTO();

        authorProductDTO.setId( authorProduct.getId() );

        return authorProductDTO;
    }

    @Override
    public AuthorProduct toEntity(AuthorProductDTO authorProductDTO) {
        if ( authorProductDTO == null ) {
            return null;
        }

        AuthorProduct authorProduct = new AuthorProduct();

        authorProduct.setId( authorProductDTO.getId() );

        return authorProduct;
    }
}
