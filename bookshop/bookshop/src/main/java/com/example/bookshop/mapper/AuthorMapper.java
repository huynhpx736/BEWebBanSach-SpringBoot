package com.example.bookshop.mapper;

import com.example.bookshop.dto.AuthorDTO;
import com.example.bookshop.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface AuthorMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class );
    AuthorDTO toDTO(Author author);
    Author toEntity(AuthorDTO authorDTO);
}
