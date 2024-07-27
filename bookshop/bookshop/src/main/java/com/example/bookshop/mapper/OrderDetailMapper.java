package com.example.bookshop.mapper;

import com.example.bookshop.dto.OrderDetailDTO;
import com.example.bookshop.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);
    @Mapping(target = "orderId", source = "orderDetail.order.id")
    @Mapping(target = "productId", source = "orderDetail.product.id")
    OrderDetailDTO toDTO(OrderDetail orderDetail);
    OrderDetail toEntity(OrderDetailDTO orderDetailDTO);
}
