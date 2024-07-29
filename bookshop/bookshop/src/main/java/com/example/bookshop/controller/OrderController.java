package com.example.bookshop.controller;

import com.example.bookshop.dto.OrderDTO;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.OrderDetailService;
import com.example.bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("update-status/{id}")
    public ResponseEntity<ResponseData> cancelOrder(@PathVariable int id, @RequestParam String status) {
        orderService.cancelOrder(id, status);
        return ResponseEntity.ok(new ResponseData(200, "Order is "+status, null, true));
    }

    @GetMapping("get-by-user-and-status")
    public ResponseEntity<ResponseData> getOrderByUserAndStatus(@RequestParam Integer userId, @RequestParam String status) {
        List<OrderDTO> orders = orderService.getOrderByUserAndStatus(userId, status);
        return ResponseEntity.ok(new ResponseData(200, "Success", orders, true));
    }

    @GetMapping("get-all-by-userid")
    public ResponseEntity<ResponseData> getAllOrdersByUserId(@RequestParam Integer userId) {
        List<OrderDTO> orders = orderService.getAllOrdersByUserId(userId);
        return ResponseEntity.ok(new ResponseData(200, "Success", orders, true));
    }
    @GetMapping("get-all")
    public ResponseEntity<ResponseData> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(new ResponseData(200, "Success", orders, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getOrderById(@PathVariable int id) {
        OrderDTO order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", order, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Order not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createOrder(@RequestBody OrderDTO orderDTO) {
        OrderDTO createdOrder = orderService.createOrder(orderDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "Order created", createdOrder, true));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData> updateOrder(@PathVariable int id, @RequestBody OrderDTO orderDTO) {
        OrderDTO updatedOrder = orderService.updateOrder(id, orderDTO);
        if (updatedOrder != null) {
            return ResponseEntity.ok(new ResponseData(200, "Order updated", updatedOrder, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "Order not found", null, false));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(new ResponseData(200, "Order deleted", null, true));
    }

    @PostMapping("/place-order")
    public ResponseEntity<ResponseData> placeOrder(@RequestParam Integer userId, @RequestParam String receiverPhone, @RequestParam String receiverAddress, @RequestParam String receiverName) {
        orderDetailService.placeOrder(userId, receiverPhone, receiverAddress, receiverName);
        return ResponseEntity.ok(new ResponseData(200, "Order placed", null, true));
    }
}
