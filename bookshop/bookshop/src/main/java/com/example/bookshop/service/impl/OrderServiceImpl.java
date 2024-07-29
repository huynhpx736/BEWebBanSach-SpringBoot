package com.example.bookshop.service.impl;

import com.example.bookshop.dto.OrderDTO;
import com.example.bookshop.entity.Order;
import com.example.bookshop.mapper.OrderMapper;
import com.example.bookshop.repository.OrderRepository;
import com.example.bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void cancelOrder(int id, String status) {
        orderRepository.findById(id).ifPresent(order -> {
            order.setStatus(status);
            orderRepository.save(order);
        });
    }

    @Override
    public List<OrderDTO> getOrderByUserAndStatus(Integer userId, String status) {
        return orderRepository.findByUserIdAndStatus(userId, status).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrdersByUserId(Integer userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO getOrderById(int id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDTO)
                .orElse(null);
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO updateOrder(int id, OrderDTO orderDTO) {
        if (orderRepository.existsById(id)) {
            Order order = orderMapper.toEntity(orderDTO);
            order.setId(id);
            return orderMapper.toDTO(orderRepository.save(order));
        }
        return null;
    }

    @Override
    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }
}

