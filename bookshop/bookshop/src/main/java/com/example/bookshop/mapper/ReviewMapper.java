package com.example.bookshop.mapper;

import com.example.bookshop.dto.ReviewDTO;
import com.example.bookshop.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);
    ReviewDTO toDTO(Review review);
    Review toEntity(ReviewDTO reviewDTO);
}
