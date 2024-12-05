package com.example.bookshop.mapper;

import com.example.bookshop.dto.OrderDTO;
import com.example.bookshop.entity.Order;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-05T11:44:49+0700",
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
        orderDTO.setShipper( order.getShipper() );
        orderDTO.setOrderDate( order.getOrderDate() );
        orderDTO.setStatus( order.getStatus() );
        orderDTO.setTotal( order.getTotal() );
        orderDTO.setShippingFee( order.getShippingFee() );
        orderDTO.setDiscount( order.getDiscount() );
        orderDTO.setReceiverPhone( order.getReceiverPhone() );
        orderDTO.setReceiverAddress( order.getReceiverAddress() );
        orderDTO.setReceiverName( order.getReceiverName() );
        orderDTO.setCreatedAt( order.getCreatedAt() );
        orderDTO.setUpdatedAt( order.getUpdatedAt() );
        orderDTO.setCancelReason( order.getCancelReason() );
        orderDTO.setFailureReason( order.getFailureReason() );
        orderDTO.setShipperNote( order.getShipperNote() );
        orderDTO.setNote( order.getNote() );

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
        order.setShipper( orderDTO.getShipper() );
        order.setOrderDate( orderDTO.getOrderDate() );
        order.setStatus( orderDTO.getStatus() );
        order.setTotal( orderDTO.getTotal() );
        order.setShippingFee( orderDTO.getShippingFee() );
        order.setDiscount( orderDTO.getDiscount() );
        order.setReceiverPhone( orderDTO.getReceiverPhone() );
        order.setReceiverAddress( orderDTO.getReceiverAddress() );
        order.setReceiverName( orderDTO.getReceiverName() );
        order.setCreatedAt( orderDTO.getCreatedAt() );
        order.setUpdatedAt( orderDTO.getUpdatedAt() );
        order.setCancelReason( orderDTO.getCancelReason() );
        order.setFailureReason( orderDTO.getFailureReason() );
        order.setShipperNote( orderDTO.getShipperNote() );
        order.setNote( orderDTO.getNote() );

        return order;
    }
}
