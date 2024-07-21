package com.example.bookshop.mapper;

import com.example.bookshop.dto.OrderDetailDTO;
import com.example.bookshop.entity.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);
    OrderDetailDTO toDTO(OrderDetail orderDetail);
    OrderDetail toEntity(OrderDetailDTO orderDetailDTO);
}
