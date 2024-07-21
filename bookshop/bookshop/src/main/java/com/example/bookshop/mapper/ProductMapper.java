//package com.example.bookshop.mapper;

package com.example.bookshop.mapper;

import com.example.bookshop.dto.ProductDTO;
import com.example.bookshop.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);
}
