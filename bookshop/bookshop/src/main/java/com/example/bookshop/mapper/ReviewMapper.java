package com.example.bookshop.mapper;

import com.example.bookshop.dto.ReviewDTO;
import com.example.bookshop.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);
    @Mapping(target = "productId", source = "review.product.id")
    @Mapping(target = "userId", source = "review.user.id")
    @Mapping(target = "userName", source = "review.user.fullname")
    @Mapping(target = "userAvatar", source = "review.user.avatar")
    ReviewDTO toDTO(Review review);
    Review toEntity(ReviewDTO reviewDTO);
}
