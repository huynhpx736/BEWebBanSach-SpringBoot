package com.example.bookshop.service;

import com.example.bookshop.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    //chuyển trạng thái đơn hàng sang đã hủy theo id ơn hàng và trạng thái
    void cancelOrder(int id, String status);
    List<OrderDTO> getOrderByUserAndStatus(Integer userId, String status);

    List<OrderDTO> getAllOrdersByUserId(Integer userId);
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(int id);
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(int id, OrderDTO orderDTO);
    void deleteOrder(int id);
}
