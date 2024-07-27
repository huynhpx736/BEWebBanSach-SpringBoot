package com.example.bookshop.mapper;

import com.example.bookshop.dto.OrderDetailDTO;
import com.example.bookshop.entity.Order;
import com.example.bookshop.entity.OrderDetail;
import com.example.bookshop.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-27T19:39:32+0700",
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
        orderDetailDTO.setId( orderDetail.getId() );
        orderDetailDTO.setQuantity( orderDetail.getQuantity() );
        orderDetailDTO.setPrice( orderDetail.getPrice() );

        return orderDetailDTO;
    }

    @Override
    public OrderDetail toEntity(OrderDetailDTO orderDetailDTO) {
        if ( orderDetailDTO == null ) {
            return null;
        }

        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setId( orderDetailDTO.getId() );
        orderDetail.setQuantity( orderDetailDTO.getQuantity() );
        orderDetail.setPrice( orderDetailDTO.getPrice() );

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
}
