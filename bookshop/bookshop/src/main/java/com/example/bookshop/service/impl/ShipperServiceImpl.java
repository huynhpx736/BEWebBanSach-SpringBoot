package com.example.bookshop.service.impl;


import com.example.bookshop.entity.Order;
import com.example.bookshop.entity.User;
import com.example.bookshop.exception.ResourceNotFoundException;
import com.example.bookshop.repository.OrderRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShipperServiceImpl implements ShipperService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Order> getAvailableOrders() {
        // Fetch orders with status "PENDING" or "READY_TO_SHIP" and no assigned shipper
        return orderRepository.findByStatusInAndShipperIsNull(List.of("PENDING", "READY_TO_SHIP"));
    }

    @Override
    public boolean acceptOrder(Integer orderId, Integer shipperId) {
        // Find the order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        // Find the shipper
        User shipper = userRepository.findById(shipperId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipper not found with ID: " + shipperId));

        // Ensure the user is a shipper
        if (!"SHIPPER".equals(shipper.getRole())) {
            throw new IllegalArgumentException("User with ID: " + shipperId + " is not a shipper.");
        }

        // Assign the shipper to the order and update status to "ASSIGNED"
        order.setShipper(shipper);
        order.setStatus("ASSIGNED");
        orderRepository.save(order);
        return true;
    }
    @Override
    public boolean reportFailedDelivery(Integer orderId, String reason) {
        // Tìm order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        // Kiểm tra trạng thái order có phù hợp để báo cáo
        if (!"ASSIGNED".equals(order.getStatus()) && !"SHIPPING".equals(order.getStatus())) {
            throw new IllegalArgumentException("Cannot report failed delivery for this order.");
        }

        // Cập nhật trạng thái và lý do thất bại
        order.setStatus("FAILED");
        order.setFailedReason(reason);
        orderRepository.save(order);
        return true;
    }

    @Override
    public List<Order> getOrdersByShipper(Integer shipperId) {
        return orderRepository.findByShipperIdAndStatus(shipperId, "ASSIGNED");
    }

}
