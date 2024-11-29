package com.example.bookshop.mapper;

import com.example.bookshop.dto.ProductDTO;
import com.example.bookshop.entity.Author;
import com.example.bookshop.entity.Category;
import com.example.bookshop.entity.Product;
import com.example.bookshop.entity.Tag;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-29T00:37:49+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setId( product.getId() );
        productDTO.setTitle( product.getTitle() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setPublicationYear( product.getPublicationYear() );
        productDTO.setPublisher( product.getPublisher() );
        productDTO.setSalesVolume( product.getSalesVolume() );
        productDTO.setStarRating( product.getStarRating() );
        productDTO.setImage( product.getImage() );
        productDTO.setWeight( product.getWeight() );
        Set<Category> set = product.getCategories();
        if ( set != null ) {
            productDTO.setCategories( new ArrayList<Category>( set ) );
        }
        Set<Author> set1 = product.getAuthors();
        if ( set1 != null ) {
            productDTO.setAuthors( new ArrayList<Author>( set1 ) );
        }
        Set<Tag> set2 = product.getTags();
        if ( set2 != null ) {
            productDTO.setTags( new ArrayList<Tag>( set2 ) );
        }
        productDTO.setPriority( product.getPriority() );
        productDTO.setStatus( product.getStatus() );
        productDTO.setQuantity_sold( product.getQuantity_sold() );
        productDTO.setContent( product.getContent() );

        return productDTO;
    }

    @Override
    public Product toEntity(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDTO.getId() );
        product.setTitle( productDTO.getTitle() );
        product.setDescription( productDTO.getDescription() );
        product.setPrice( productDTO.getPrice() );
        product.setPublicationYear( productDTO.getPublicationYear() );
        product.setPublisher( productDTO.getPublisher() );
        product.setSalesVolume( productDTO.getSalesVolume() );
        product.setStarRating( productDTO.getStarRating() );
        product.setImage( productDTO.getImage() );
        product.setWeight( productDTO.getWeight() );
        List<Category> list = productDTO.getCategories();
        if ( list != null ) {
            product.setCategories( new HashSet<Category>( list ) );
        }
        List<Author> list1 = productDTO.getAuthors();
        if ( list1 != null ) {
            product.setAuthors( new HashSet<Author>( list1 ) );
        }
        List<Tag> list2 = productDTO.getTags();
        if ( list2 != null ) {
            product.setTags( new HashSet<Tag>( list2 ) );
        }
        product.setPriority( productDTO.getPriority() );
        product.setStatus( productDTO.getStatus() );
        product.setQuantity_sold( productDTO.getQuantity_sold() );
        product.setContent( productDTO.getContent() );

        return product;
    }
}
