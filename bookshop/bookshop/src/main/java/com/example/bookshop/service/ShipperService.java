package com.example.bookshop.service;

import com.example.bookshop.entity.Order;

import java.util.List;



public interface ShipperService {
    List<Order> getAvailableOrders();
    boolean acceptOrder(Integer orderId, Integer shipperId);
    boolean reportFailedDelivery(Integer orderId, String reason);

    List<Order> getOrdersByShipper(Integer shipperId);
}
