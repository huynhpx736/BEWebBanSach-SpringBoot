package com.example.bookshop.controller;

import com.example.bookshop.dto.OrderDetailDTO;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("get-all")
    public ResponseEntity<ResponseData> getAllOrderDetails() {
        List<OrderDetailDTO> orderDetails = orderDetailService.getAllOrderDetails();
        return ResponseEntity.ok(new ResponseData(200, "Success", orderDetails, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getOrderDetailById(@PathVariable int id) {
        OrderDetailDTO orderDetail = orderDetailService.getOrderDetailById(id);
        if (orderDetail != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", orderDetail, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "OrderDetail not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createOrderDetail(@RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailDTO createdOrderDetail = orderDetailService.createOrderDetail(orderDetailDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "OrderDetail created", createdOrderDetail, true));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData> updateOrderDetail(@PathVariable int id, @RequestBody OrderDetailDTO orderDetailDTO) {
        OrderDetailDTO updatedOrderDetail = orderDetailService.updateOrderDetail(id, orderDetailDTO);
        if (updatedOrderDetail != null) {
            return ResponseEntity.ok(new ResponseData(200, "OrderDetail updated", updatedOrderDetail, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "OrderDetail not found", null, false));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData> deleteOrderDetail(@PathVariable int id) {
        orderDetailService.deleteOrderDetail(id);
        return ResponseEntity.ok(new ResponseData(200, "OrderDetail deleted", null, true));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ResponseData> getOrderDetailsByOrderId(@PathVariable Integer orderId) {
        List<OrderDetailDTO> orderDetails = orderDetailService.getOrderDetailsByOrderId(orderId);
        return ResponseEntity.ok(new ResponseData(200, "Success", orderDetails, true));
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<ResponseData> addProductToCart(@RequestParam Integer userId, @RequestParam Integer productId, @RequestParam Integer quantity) {
        orderDetailService.addProductToCart(userId, productId, quantity);
        return ResponseEntity.ok(new ResponseData(200, "Product added to cart", null, true));
    }

//    @PostMapping("/place-order")
//    public ResponseEntity<ResponseData> placeOrder(@RequestParam Integer userId, @RequestParam String receiverPhone, @RequestParam String receiverAddress, @RequestParam String receiverName) {
//        orderDetailService.placeOrder(userId, receiverPhone, receiverAddress, receiverName);
//        return ResponseEntity.ok(new ResponseData(200, "Order placed", null, true));
//    }

    @GetMapping("/get-cart-items/{userId}")
    public ResponseEntity<ResponseData> getCartItems(@PathVariable Integer userId) {
        List<OrderDetailDTO> orderDetails = orderDetailService.getCartItems(userId);
        return ResponseEntity.ok(new ResponseData(200, "Success", orderDetails, true));
    }

    @PutMapping("/update-quantity")
    public ResponseEntity<ResponseData> updateQuantity(@RequestParam int idDetailOrder, @RequestParam int newQuantity) {
        orderDetailService.updateQuantity(idDetailOrder, newQuantity);
        return ResponseEntity.ok(new ResponseData(200, "Quantity updated", null, true));
    }
}
