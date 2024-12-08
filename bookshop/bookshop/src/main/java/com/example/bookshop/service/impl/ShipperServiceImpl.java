package com.example.bookshop.service.impl;


import com.example.bookshop.dto.OrderDTO;
import com.example.bookshop.dto.UserDTO;
import com.example.bookshop.entity.Order;
import com.example.bookshop.entity.User;
import com.example.bookshop.exception.ResourceNotFoundException;
import com.example.bookshop.mapper.OrderMapper;
import com.example.bookshop.repository.OrderRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipperServiceImpl implements ShipperService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderMapper orderMapper;

//    @Override
//    public List<User> getAllShippers() {
//        return userRepository.findAllByRole(3).stream()
//                .map(UserDTO::toDTO)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<OrderDTO> getAvailableOrders() {
        // Fetch orders with status "PENDING" or "READY_TO_SHIP" and no assigned shipper
        return orderRepository.findByStatusInAndShipperIsNull(List.of("PENDING", "READY_TO_SHIP")).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
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
//        if (shipper.getRole() != 3) {
//            throw new IllegalArgumentException("User with ID: " + shipperId + " is not a shipper.");
//        }

        // Assign the shipper to the order and update status to "ASSIGNED"
        order.setShipper(shipper);
        order.setStatus("SHIPPING");
        orderRepository.save(order);
        return true;
    }

//    @Override
//    public List<OrderDTO> getOrdersByShipperAndStatus(Integer shipperId, String status) {
//        return orderRepository.findByShipperIdAndStatus(shipperId, status);
//    }


        @Override
    public List<OrderDTO> getOrdersByShipperAndStatus(Integer shipperId, String status) {
//        return orderRepository.findAllByShipperIdAndStatus(shipperId, status).stream()
//                .map(OrderDTO::toDTO)
        return orderRepository.findAllByShipperIdAndStatus(shipperId, status).stream()

                .map(orderMapper::toDTO)
                .collect(Collectors.toList());

    }
    @Override
    public boolean reportFailedDelivery(Integer orderId, String reason, String note) {
        // Tìm order
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        // Cập nhật trạng thái và lý do thất bại
        order.setStatus("FAILED");
        order.setFailureReason(reason);
        if (note != null && !note.isEmpty()) {
            order.setShipperNote(note);
        }
        orderRepository.save(order);
        return true;
    }

    @Override
    public List<OrderDTO> getAllOrdersByShipper(Integer shipperId) {
        return orderRepository.findAllByShipperId(shipperId).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

}
