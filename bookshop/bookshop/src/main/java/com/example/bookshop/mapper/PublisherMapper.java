package com.example.bookshop.mapper;

import com.example.bookshop.dto.PublisherDTO;
import com.example.bookshop.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PublisherMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);
    PublisherDTO toDTO(Publisher publisher);
    Publisher toEntity(PublisherDTO publisherDTO);
}
