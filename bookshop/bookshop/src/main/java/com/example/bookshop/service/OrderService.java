package com.example.bookshop.service;

import com.example.bookshop.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(int id);
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(int id, OrderDTO orderDTO);
    void deleteOrder(int id);
}
