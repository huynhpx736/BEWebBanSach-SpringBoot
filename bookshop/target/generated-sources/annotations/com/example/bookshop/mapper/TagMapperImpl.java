package com.example.bookshop.mapper;

import com.example.bookshop.dto.TagDTO;
import com.example.bookshop.entity.Tag;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-27T19:39:32+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public TagDTO toDTO(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDTO tagDTO = new TagDTO();

        tagDTO.setId( tag.getId() );
        tagDTO.setName( tag.getName() );

        return tagDTO;
    }

    @Override
    public Tag toEntity(TagDTO tagDTO) {
        if ( tagDTO == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setId( tagDTO.getId() );
        tag.setName( tagDTO.getName() );

        return tag;
    }
}
