package com.example.bookshop.mapper;

import com.example.bookshop.dto.OrderDTO;
import com.example.bookshop.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
//    @Mapping(target = "userId", source = "order.user.id")
    OrderDTO toDTO(Order order);
    Order toEntity(OrderDTO orderDTO);
}
