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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

//import java.util.Date;
import java.time.LocalDateTime;
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
    public void markItemHasReview(int id) {
        OrderDetail orderDetail = orderDetailRepository.findById(id).orElseThrow(() -> new RuntimeException("Order detail not found"));
        orderDetail.setHasReview(1);
        orderDetailRepository.save(orderDetail);
    }

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

//        Order order = orderRepository.findActiveOrderByUserId(userId).orElse(new Order());
//        Order order = orderRepository.findByUserIdAndStatus(userId, "PENDING").orElse(new Order());
        Order order = orderRepository.FindOrderIsPending(userId);
        if (order == null) {
            order = new Order();
        }
        order.setUser(user);
//        order.setOrderDate(new Date());
        order.setStatus("PENDING");

        order = orderRepository.save(order);
        //kiem tra xem san pham da co trong order chua, neu da co thi tang so luong, neu chua co thi tao moi
        OrderDetail orderDetail = orderDetailRepository.findByOrderIdAndProductId(order.getId(), productId);
        if (orderDetail == null) {
            orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(product.getPrice());
            orderDetail.setHasReview(0);
        } else {
            orderDetail.setQuantity(orderDetail.getQuantity() + quantity);
        }

//
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setOrder(order);
//        orderDetail.setProduct(product);
//        orderDetail.setQuantity(quantity);
//        orderDetail.setPrice(product.getPrice());



        orderDetailRepository.save(orderDetail);
    }

//    @Override
//    public void placeOrder(Integer userId, String receiverPhone, String receiverAddress, String receiverName, Float shippingFee, Float discount, Float total) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//
////        Order order = orderRepository.findActiveOrderByUserId(userId)
////        Order order = orderRepository.findByUserIdAndStatus(userId, "PENDING")
//          Order order = orderRepository.FindOrderIsPending(userId);
//            //neu khong tim thay thi throw exception
//         if (order == null) {
//            throw new RuntimeException("No active order found for user");
//        }
//
////            .orElseThrow(() -> new RuntimeException("No active order found for user"));
//
//            order.setUser(user);
//            order.setShippingFee(shippingFee);
//            order.setDiscount(discount);
//            order.setReceiverPhone(receiverPhone);
//            order.setReceiverAddress(receiverAddress);
//            order.setReceiverName(receiverName);
//            order.setStatus("PLACED");
//            order.setOrderDate(LocalDateTime.now());
//            order.setTotal(total);
//            //ban tong tien cua cac order detail =sum( price * quantity) cua tung order detail
////            order.setTotal((float)(order.getOrderDetails().stream().mapToDouble(orderDetail -> orderDetail.getPrice() * orderDetail.getQuantity()).sum())+order.getShippingFee()-order.getDiscount());
////
////        order.setReceiverPhone(receiverPhone);
////        order.setReceiverAddress(receiverAddress);
////        order.setReceiverName(receiverName);
////        order.setStatus("PLACED");
////        order.setOrderDate(LocalDateTime.now());
////        //ban tong tien cua cac order detail =sum( price * quantity) cua tung order detail
////        order.setTotal((float)(order.getOrderDetails().stream().mapToDouble(orderDetail -> orderDetail.getPrice() * orderDetail.getQuantity()).sum())+order.getShippingFee()-order.getDiscount());
//
////        order.setTotal((float) order.getOrderDetails().stream().mapToDouble(OrderDetail::getPrice).sum())  ;
//        orderRepository.save(order);
//    }

    @Override
    public List<OrderDetailDTO> getCartItems(Integer userId) {
//        Order order = orderRepository.findActiveOrderByUserId(userId)
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        Order order = orderRepository.findByUserIdAndStatus(userId, "PENDING").orElse(new Order());
        Order order = orderRepository.FindOrderIsPending(userId);
        if (order == null) {
            order = new Order();
//            return null;
        }
        //cap nhật lai gia va so luong cua tung oderdetail ngay tai luc dặt hàng so voi product
        for (OrderDetail orderDetail : order.getOrderDetails()) {
            orderDetail.setPrice(orderDetail.getProduct().getPrice());
            if (orderDetail.getQuantity() > orderDetail.getProduct().getSalesVolume()) {
                orderDetail.setQuantity(orderDetail.getProduct().getSalesVolume());
            }
            orderDetailRepository.save(orderDetail);
        }
        order.setUser(user);

//        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PENDING");

        order = orderRepository.save(order);

//                .orElseThrow(() -> new RuntimeException("No active order found for user"));

        return orderDetailRepository.findByOrderId(order.getId()).stream()
                .map(orderDetail -> new OrderDetailDTO(
                        orderDetail.getId(),
                        orderDetail.getOrder().getId(),
                        orderDetail.getProduct().getId(),
                        orderDetail.getQuantity(),
                        orderDetail.getPrice(),
                        orderDetail.getHasReview(),

//                        orderDetail.getProduct().getPrice(),
                        orderDetail.getProduct().getImage(),
                        orderDetail.getProduct().getWeight(),
                        orderDetail.getProduct().getTitle(),
                        orderDetail.getPrice()*orderDetail.getQuantity(),
                        orderDetail.getProduct().getSalesVolume()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void updateQuantity(int idDetailOrder, int newQuantity) {
        OrderDetail orderDetail = orderDetailRepository.findById(idDetailOrder).orElseThrow(() -> new RuntimeException("Order detail not found"));
        orderDetail.setQuantity(newQuantity);
        orderDetailRepository.save(orderDetail);
    }

}
