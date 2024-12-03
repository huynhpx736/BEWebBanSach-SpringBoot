package com.example.bookshop.controller;

import com.example.bookshop.dto.OrderDTO;
import com.example.bookshop.dto.OrderDetailDTO;
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
//    @PostMapping("/orders/report-failed/{orderId}")
//    public ResponseEntity<String> reportFailedDelivery(
//            @PathVariable Integer orderId,
//            @RequestParam String reason,
//            @RequestParam String note
//    ) {
//        boolean isReported = shipperService.reportFailedDelivery(orderId, reason, note);
//        if (isReported) {
//            return ResponseEntity.ok("Reported failed delivery successfully.");
//        } else {
//            return ResponseEntity.badRequest().body("Failed to report delivery failure.");
//        }
//    }
    @PostMapping("cancel-by-admin/{id}")
    public ResponseEntity<ResponseData> cancelOrderByAdmin(@PathVariable int id, @RequestParam String cancelReason, @RequestParam String note) {
        orderService.cancelOrderByAdmin(id, cancelReason, note);
        return ResponseEntity.ok(new ResponseData(200, "Order is cancel because"+cancelReason, null, true));
    }
    @PostMapping("/place-order")
    public ResponseEntity<ResponseData> placeOrder(@RequestParam Integer userId, @RequestParam String receiverPhone, @RequestParam String receiverAddress, @RequestParam String receiverName, @RequestParam Float shippingFee, @RequestParam Float discount, @RequestParam Float total) {
        orderService.placeOrder(userId, receiverPhone, receiverAddress, receiverName, shippingFee, discount, total);
        return ResponseEntity.ok(new ResponseData(200, "Order placed", null, true));
    }
    //dat hang theo id nguoi dung, danh sach chi tiet don hang va thong tin don hang
//    @PostMapping("/place")
//    public ResponseEntity<ResponseData> placeOrder(@RequestParam int userId, @RequestBody List<OrderDetailDTO> orderDetails, @RequestBody OrderDTO orderDTO) {
//        OrderDTO placedOrder = orderService.placeOrder(userId, orderDetails, orderDTO);
//        return ResponseEntity.status(201).body(new ResponseData(201, "Order placed", placedOrder, true));
//    }

    @GetMapping("get-all-by-status")
    public ResponseEntity<ResponseData> getAllByStatus(@RequestParam String status) {
        List<OrderDTO> orders = orderService.getAllByStatus(status);
        return ResponseEntity.ok(new ResponseData(200, "Success", orders, true));
    }
    @PutMapping("update-status/{id}")
    public ResponseEntity<ResponseData> updateOrderStatus(@PathVariable int id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(new ResponseData(200, "Order status updated", null, true));
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

}
