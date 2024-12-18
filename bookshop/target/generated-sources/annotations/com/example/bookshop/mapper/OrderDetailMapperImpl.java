package com.example.bookshop.mapper;

import com.example.bookshop.dto.OrderDetailDTO;
import com.example.bookshop.entity.Order;
import com.example.bookshop.entity.OrderDetail;
import com.example.bookshop.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-15T15:13:40+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Override
    public OrderDetailDTO toDTO(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        orderDetailDTO.setOrderId( orderDetailOrderId( orderDetail ) );
        orderDetailDTO.setProductId( orderDetailProductId( orderDetail ) );
        orderDetailDTO.setImage( orderDetailProductImage( orderDetail ) );
        orderDetailDTO.setProductName( orderDetailProductTitle( orderDetail ) );
        orderDetailDTO.setWeight( orderDetailProductWeight( orderDetail ) );
        orderDetailDTO.setStock( orderDetailProductSalesVolume( orderDetail ) );
        orderDetailDTO.setId( orderDetail.getId() );
        orderDetailDTO.setQuantity( orderDetail.getQuantity() );
        orderDetailDTO.setPrice( orderDetail.getPrice() );
        orderDetailDTO.setHasReview( orderDetail.getHasReview() );

        return orderDetailDTO;
    }

    @Override
    public OrderDetail toEntity(OrderDetailDTO orderDetailDTO) {
        if ( orderDetailDTO == null ) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setOrder( orderDetailDTOToOrder( orderDetailDTO ) );
        orderDetail.setProduct( orderDetailDTOToProduct( orderDetailDTO ) );
        orderDetail.setId( orderDetailDTO.getId() );
        orderDetail.setQuantity( orderDetailDTO.getQuantity() );
        orderDetail.setPrice( orderDetailDTO.getPrice() );
        orderDetail.setHasReview( orderDetailDTO.getHasReview() );

        return orderDetail;
    }

    private Integer orderDetailOrderId(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        Order order = orderDetail.getOrder();
        if ( order == null ) {
            return null;
        }
        Integer id = order.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Integer orderDetailProductId(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        Product product = orderDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        Integer id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String orderDetailProductImage(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        Product product = orderDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String image = product.getImage();
        if ( image == null ) {
            return null;
        }
        return image;
    }

    private String orderDetailProductTitle(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        Product product = orderDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String title = product.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }

    private Float orderDetailProductWeight(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        Product product = orderDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        Float weight = product.getWeight();
        if ( weight == null ) {
            return null;
        }
        return weight;
    }

    private Integer orderDetailProductSalesVolume(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        Product product = orderDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        Integer salesVolume = product.getSalesVolume();
        if ( salesVolume == null ) {
            return null;
        }
        return salesVolume;
    }

    protected Order orderDetailDTOToOrder(OrderDetailDTO orderDetailDTO) {
        if ( orderDetailDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderDetailDTO.getOrderId() );

        return order;
    }

    protected Product orderDetailDTOToProduct(OrderDetailDTO orderDetailDTO) {
        if ( orderDetailDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( orderDetailDTO.getProductId() );

        return product;
    }
}
