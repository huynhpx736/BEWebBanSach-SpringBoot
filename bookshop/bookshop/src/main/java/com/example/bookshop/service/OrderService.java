package com.example.bookshop.service;

import com.example.bookshop.dto.OrderDTO;
import com.example.bookshop.dto.OrderDetailDTO;

import java.util.List;

public interface OrderService {
    boolean updateNoteOrder(int id, String note);
    void cancelOrderByAdmin(int id, String cancelReason, String note);
    void placeOrder(Integer userId, String receiverPhone, String receiverAddress, String receiverName, Float shippingFee, Float discount, Float total);

    List<OrderDTO> getAllByStatus(String status);
    List<OrderDTO> getOrderByUserAndStatus(Integer userId, String status);

    List<OrderDTO> getAllOrdersByUserId(Integer userId);
    List<OrderDTO> getAllOrders();
    OrderDTO getOrderById(int id);
    OrderDTO createOrder(OrderDTO orderDTO);
    OrderDTO updateOrder(int id, OrderDTO orderDTO);
    void deleteOrder(int id);
//    OrderDTO placeOrder(int userId, List<OrderDetailDTO> orderDetails, OrderDTO orderDTO);
    void updateOrderStatus(int id, String status);
}
