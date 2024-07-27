package com.example.bookshop.mapper;

import com.example.bookshop.dto.OrderDTO;
import com.example.bookshop.entity.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-27T19:39:32+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO toDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( order.getId() );
        orderDTO.setUser( order.getUser() );
        orderDTO.setOrderDate( order.getOrderDate() );
        orderDTO.setStatus( order.getStatus() );
        orderDTO.setTotal( (int) order.getTotal() );
        orderDTO.setShippingFee( (int) order.getShippingFee() );
        orderDTO.setDiscount( (int) order.getDiscount() );

        return orderDTO;
    }

    @Override
    public Order toEntity(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( orderDTO.getId() );
        order.setUser( orderDTO.getUser() );
        order.setOrderDate( orderDTO.getOrderDate() );
        order.setStatus( orderDTO.getStatus() );
        if ( orderDTO.getTotal() != null ) {
            order.setTotal( orderDTO.getTotal() );
        }
        if ( orderDTO.getShippingFee() != null ) {
            order.setShippingFee( orderDTO.getShippingFee() );
        }
        if ( orderDTO.getDiscount() != null ) {
            order.setDiscount( orderDTO.getDiscount() );
        }

        return order;
    }
}
