package com.example.bookshop.mapper;

import com.example.bookshop.dto.TagDTO;
import com.example.bookshop.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);
    TagDTO toDTO(Tag tag);
    Tag toEntity(TagDTO tagDTO);
}
