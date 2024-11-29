package com.example.bookshop.service.impl;

import com.example.bookshop.dto.OrderDTO;
import com.example.bookshop.dto.OrderDetailDTO;
import com.example.bookshop.entity.Order;
import com.example.bookshop.entity.OrderDetail;
import com.example.bookshop.entity.Product;
import com.example.bookshop.entity.User;
import com.example.bookshop.mapper.OrderDetailMapper;
import com.example.bookshop.mapper.OrderMapper;
import com.example.bookshop.repository.OrderDetailRepository;
import com.example.bookshop.repository.OrderRepository;
import com.example.bookshop.repository.ProductRepository;
import com.example.bookshop.repository.UserRepository;
import com.example.bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private ProductRepository productRepository;

//    Order o = orderRepository.findById(1).orElse(null);
//    System.out.println("Order: " + o);

    @Override
    public void cancelOrder(int id, String status) {
        orderRepository.findById(id).ifPresent(order -> {
            order.setStatus(status);
            orderRepository.save(order);
        });
    }

    @Override
    public void placeOrder(Integer userId, String receiverPhone, String receiverAddress, String receiverName, Float shippingFee, Float discount, Float total) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

//        Order order = orderRepository.findActiveOrderByUserId(userId)
//        Order order = orderRepository.findByUserIdAndStatus(userId, "PENDING")
        Order order = orderRepository.FindOrderIsPending(userId);
//        //cap nhật lai gia cua tung oderdetail ngay tai luc dặt hàng
//        for (OrderDetail orderDetail : order.getOrderDetails()) {
//            orderDetail.setPrice(orderDetail.getProduct().getPrice());
//            orderDetailRepository.save(orderDetail);
//        }
        //neu khong tim thay thi throw exception
        if (order == null) {
            throw new RuntimeException("No active order found for user");
        }

//            .orElseThrow(() -> new RuntimeException("No active order found for user"));

        order.setUser(user);
        order.setShippingFee(shippingFee);
        order.setDiscount(discount);
        order.setReceiverPhone(receiverPhone);
        order.setReceiverAddress(receiverAddress);
        order.setReceiverName(receiverName);
        order.setStatus("PLACED");
        order.setOrderDate(LocalDateTime.now());
        order.setTotal(total);
        //ban tong tien cua cac order detail =sum( price * quantity) cua tung order detail
//            order.setTotal((float)(order.getOrderDetails().stream().mapToDouble(orderDetail -> orderDetail.getPrice() * orderDetail.getQuantity()).sum())+order.getShippingFee()-order.getDiscount());
//
//        order.setReceiverPhone(receiverPhone);
//        order.setReceiverAddress(receiverAddress);
//        order.setReceiverName(receiverName);
//        order.setStatus("PLACED");
//        order.setOrderDate(LocalDateTime.now());
//        //ban tong tien cua cac order detail =sum( price * quantity) cua tung order detail
//        order.setTotal((float)(order.getOrderDetails().stream().mapToDouble(orderDetail -> orderDetail.getPrice() * orderDetail.getQuantity()).sum())+order.getShippingFee()-order.getDiscount());

//        order.setTotal((float) order.getOrderDetails().stream().mapToDouble(OrderDetail::getPrice).sum())  ;

        orderRepository.save(order);
        //cập nhat lai so luong cua product sau khi dat hang va luu lai
        for (OrderDetail orderDetail : order.getOrderDetails()) {
//            Product product = orderDetail.getProduct();
//            product.setSalesVolume(product.getSalesVolume() -  orderDetail.getQuantity());
//            productRepository.save(product);
            System.out.println("Con lai truoc:" +orderDetail.getProduct().getSalesVolume());

            orderDetail.getProduct().setSalesVolume(orderDetail.getProduct().getSalesVolume() - orderDetail.getQuantity());
            System.out.println("Con lai sau:" +orderDetail.getProduct().getSalesVolume());
            productRepository.save(orderDetail.getProduct());
        }


    }

    @Override
    public List<OrderDTO> getAllByStatus(String status) {
        return orderRepository.findByStatus(status).stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public List<OrderDTO> getOrderByUserAndStatus(Integer userId, String status) {
//        return orderRepository.findByUserIdAndStatus(userId, status).stream()
        return orderRepository.findAllByUserIdAndStatus(userId, status).stream()

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

//    @Override
//    public OrderDTO placeOrder(int userId, List<OrderDetailDTO> orderDetails, OrderDTO orderDTO) {
//        // Create a new order with "Pending" status
//        User user = userRepository.findById(userId).orElse(null);
//        orderDTO.setUser(user);
//        OrderDTO createdOrder = createOrder(orderDTO);
//
//        // Add order details
//        for (OrderDetailDTO detailDTO : orderDetails) {
//            OrderDetail orderDetail = orderDetailMapper.toEntity(detailDTO);
//            orderDetail.setOrder(orderMapper.toEntity(createdOrder));
//            orderDetailRepository.save(orderDetail);
//        }
//
//        // Update status to "Placed"
//        createdOrder.setStatus("Placed");
//        return updateOrder(createdOrder.getId(), createdOrder);
//    }

    @Override
    public void updateOrderStatus(int id, String status) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);


//        OrderDTO orderDTO = getOrderById(id);
//        if (orderDTO != null) {
//            orderDTO.setStatus(status);
//            updateOrder(id, orderDTO);
//        }

        //nếu đơn hàng đã huy thì cập nhật lại so luong
        if (status.equals("CANCELLED")) {
//            Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
//            System.out.println("Order: " + order);
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                orderDetail.getProduct().setSalesVolume(orderDetail.getProduct().getSalesVolume() + orderDetail.getQuantity());
                productRepository.save(orderDetail.getProduct());
            }
        }



        //Trở thành thành viên Thân thiết sau 10 đơn hàng hoặc tổng giá trị mua hàng đạt 10 triệu đồng.
        //Trở thành thành viên VIP sau 25 đơn hàng hoặc tổng giá trị mua hàng đạt 25 triệu đồng.
        if (status.equals("COMPLETED")) {
//            Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                //cập nhat lai so luong còn lại sale_volume cua product sau khi hoàn thành đơn hàng
                orderDetail.getProduct().setSalesVolume(orderDetail.getProduct().getSalesVolume() - orderDetail.getQuantity());

                //cập nhat lai so luong đã bán quantity_sold cua product sau khi hoàn thành đơn hàng
                orderDetail.getProduct().setQuantity_sold(orderDetail.getProduct().getQuantity_sold() + orderDetail.getQuantity());
                productRepository.save(orderDetail.getProduct());
            }

            //cập nhật lại classification của user
//            User user = orderDTO.getUser();
            User user = order.getUser();
            int totalOrders = orderRepository.countByUserId(user.getId());
            float totalAmount = orderRepository.sumTotalByUserId(user.getId());
            if (totalOrders >= 10 || totalAmount >= 10000000) {
                if (user.getClassification() == null || user.getClassification().equals("NORMAL"))
                    user.setClassification("LOYAL");
                user.setClassification("LOYAL");
            }
            if (totalOrders >= 25 || totalAmount >= 25000000) {
                if (user.getClassification() == null || !user.getClassification().equals("VIP"))
                user.setClassification("VIP");
            }
            userRepository.save(user);
        }

    }



}

