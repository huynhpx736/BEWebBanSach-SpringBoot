//package com.example.bookshop.mapper;
//
//import com.example.bookshop.dto.OrderDetailDTO;
//import com.example.bookshop.entity.OrderDetail;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//import org.mapstruct.factory.Mappers;
//
//@Mapper(componentModel = "spring")
//public interface OrderDetailMapper {
//    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);
//    @Mapping(target = "orderId", source = "orderDetail.order.id")
//    @Mapping(target = "productId", source = "orderDetail.product.id")
//    OrderDetailDTO toDTO(OrderDetail orderDetail);
//    OrderDetail toEntity(OrderDetailDTO orderDetailDTO);
//}

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
    @Mapping(target = "image", source = "orderDetail.product.image")
    @Mapping(target = "productName", source = "orderDetail.product.title")
    @Mapping(target = "weight", source = "orderDetail.product.weight")
    @Mapping(target = "stock", source = "orderDetail.product.salesVolume")
    OrderDetailDTO toDTO(OrderDetail orderDetail);

    @Mapping(target = "order.id", source = "orderDetailDTO.orderId")
    @Mapping(target = "product.id", source = "orderDetailDTO.productId")
    OrderDetail toEntity(OrderDetailDTO orderDetailDTO);
}
