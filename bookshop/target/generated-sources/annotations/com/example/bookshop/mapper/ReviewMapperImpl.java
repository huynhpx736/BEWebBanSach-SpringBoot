package com.example.bookshop.mapper;

import com.example.bookshop.dto.ReviewDTO;
import com.example.bookshop.entity.Product;
import com.example.bookshop.entity.Review;
import com.example.bookshop.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-29T06:55:10+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ReviewMapperImpl implements ReviewMapper {

    @Override
    public ReviewDTO toDTO(Review review) {
        if ( review == null ) {
            return null;
        }

        ReviewDTO reviewDTO = new ReviewDTO();

        reviewDTO.setProductId( reviewProductId( review ) );
        reviewDTO.setUserId( reviewUserId( review ) );
        reviewDTO.setId( review.getId() );
        reviewDTO.setRating( review.getRating() );
        reviewDTO.setComment( review.getComment() );

        return reviewDTO;
    }

    @Override
    public Review toEntity(ReviewDTO reviewDTO) {
        if ( reviewDTO == null ) {
            return null;
        }

        Review review = new Review();

        review.setId( reviewDTO.getId() );
        if ( reviewDTO.getRating() != null ) {
            review.setRating( reviewDTO.getRating() );
        }
        review.setComment( reviewDTO.getComment() );

        return review;
    }

    private Integer reviewProductId(Review review) {
        if ( review == null ) {
            return null;
        }
        Product product = review.getProduct();
        if ( product == null ) {
            return null;
        }
        Integer id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer reviewUserId(Review review) {
        if ( review == null ) {
            return null;
        }
        User user = review.getUser();
        if ( user == null ) {
            return null;
        }
        Integer id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
