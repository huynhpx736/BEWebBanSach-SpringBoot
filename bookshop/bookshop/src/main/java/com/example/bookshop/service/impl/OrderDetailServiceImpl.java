package com.example.bookshop.service.impl;

import com.example.bookshop.dto.OrderDetailDTO;
import com.example.bookshop.entity.Order;
import com.example.bookshop.entity.OrderDetail;
import com.example.bookshop.entity.Product;
import com.example.bookshop.entity.User;
import com.example.bookshop.mapper.OrderDetailMapper;
import com.example.bookshop.repository.OrderDetailRepository;
import com.example.bookshop.repository.OrderRepository;
import com.example.bookshop.repository.ProductRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public List<OrderDetailDTO> getAllOrderDetails() {
        return orderDetailRepository.findAll().stream()
                .map(orderDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDetailDTO getOrderDetailById(Integer id) {
        return orderDetailRepository.findById(id)
                .map(orderDetailMapper::toDTO)
                .orElse(null);
    }

    @Override
    public OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = orderDetailMapper.toEntity(orderDetailDTO);
        return orderDetailMapper.toDTO(orderDetailRepository.save(orderDetail));
    }

    @Override
    public OrderDetailDTO updateOrderDetail(Integer id, OrderDetailDTO orderDetailDTO) {
        if (orderDetailRepository.existsById(id)) {
            OrderDetail orderDetail = orderDetailMapper.toEntity(orderDetailDTO);
            orderDetail.setId(id);
            return orderDetailMapper.toDTO(orderDetailRepository.save(orderDetail));
        }
        return null;
    }

    @Override
    public void deleteOrderDetail(Integer id) {
        orderDetailRepository.deleteById(id);
    }

    @Override
    public List<OrderDetailDTO> getOrderDetailsByOrderId(Integer orderId) {
        return orderDetailRepository.findByOrderId(orderId).stream()
                .map(orderDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addProductToCart(Integer userId, Integer productId, Integer quantity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));

        Order order = orderRepository.findActiveOrderByUserId(userId).orElse(new Order());
        order.setUser(user);
//        order.setOrderDate(new Date()   );
        order.setStatus("PENDING");

        order = orderRepository.save(order);

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(quantity);
        orderDetail.setPrice(product.getPrice() * quantity);

        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void placeOrder(Integer userId, String receiverPhone, String receiverAddress, String receiverName) {
        Order order = orderRepository.findActiveOrderByUserId(userId)
                .orElseThrow(() -> new RuntimeException("No active order found for user"));

        order.setReceiverPhone(receiverPhone);
        order.setReceiverAddress(receiverAddress);
        order.setReceiverName(receiverName);
        order.setStatus("PLACED");
        order.setTotal((float) order.getOrderDetails().stream().mapToDouble(OrderDetail::getPrice).sum())  ;
        orderRepository.save(order);
    }
}
