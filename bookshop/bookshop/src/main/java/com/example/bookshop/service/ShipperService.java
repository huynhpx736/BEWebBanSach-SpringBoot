package com.example.bookshop.service;

import com.example.bookshop.dto.OrderDTO;
import com.example.bookshop.dto.UserDTO;
import com.example.bookshop.entity.Order;

import java.util.List;



public interface ShipperService {
//    List<UserDTO> getAllShippers();
    //sửa ghi chú shipper cho don hang
    boolean updateNoteShipper(Integer orderId, String shipperNote);
    List<OrderDTO> getAvailableOrders();
    boolean acceptOrder(Integer orderId, Integer shipperId);
    List<OrderDTO> getOrdersByShipperAndStatus(Integer shipperId, String status);
//    List<OrderDTO> getOrdersByShipperAndStatus(Integer shipperId, String status);
    boolean reportFailedDelivery(Integer orderId, String reason, String note);

    List<OrderDTO> getAllOrdersByShipper(Integer shipperId);
}
