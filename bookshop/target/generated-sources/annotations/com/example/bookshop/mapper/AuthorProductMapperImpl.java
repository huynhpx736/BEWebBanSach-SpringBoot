package com.example.bookshop.mapper;

import com.example.bookshop.dto.AuthorProductDTO;
import com.example.bookshop.entity.Author;
import com.example.bookshop.entity.AuthorProduct;
import com.example.bookshop.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-02T11:13:49+0700",
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

        authorProductDTO.setAuthorId( authorProductAuthorId( authorProduct ) );
        authorProductDTO.setProductId( authorProductProductId( authorProduct ) );
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

    private Integer authorProductAuthorId(AuthorProduct authorProduct) {
        if ( authorProduct == null ) {
            return null;
        }
        Author author = authorProduct.getAuthor();
        if ( author == null ) {
            return null;
        }
        Integer id = author.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer authorProductProductId(AuthorProduct authorProduct) {
        if ( authorProduct == null ) {
            return null;
        }
        Product product = authorProduct.getProduct();
        if ( product == null ) {
            return null;
        }
        Integer id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
