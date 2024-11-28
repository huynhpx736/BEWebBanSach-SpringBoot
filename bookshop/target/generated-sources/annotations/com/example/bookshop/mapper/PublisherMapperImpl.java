package com.example.bookshop.mapper;

import com.example.bookshop.dto.PublisherDTO;
import com.example.bookshop.entity.Publisher;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-29T00:00:42+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class PublisherMapperImpl implements PublisherMapper {

    @Override
    public PublisherDTO toDTO(Publisher publisher) {
        if ( publisher == null ) {
            return null;
        }

        PublisherDTO publisherDTO = new PublisherDTO();

        publisherDTO.setId( publisher.getId() );
        publisherDTO.setName( publisher.getName() );
        publisherDTO.setAddress( publisher.getAddress() );
        publisherDTO.setEmail( publisher.getEmail() );

        return publisherDTO;
    }

    @Override
    public Publisher toEntity(PublisherDTO publisherDTO) {
        if ( publisherDTO == null ) {
            return null;
        }

        Publisher publisher = new Publisher();

        publisher.setId( publisherDTO.getId() );
        publisher.setName( publisherDTO.getName() );
        publisher.setAddress( publisherDTO.getAddress() );
        publisher.setEmail( publisherDTO.getEmail() );

        return publisher;
    }
}
