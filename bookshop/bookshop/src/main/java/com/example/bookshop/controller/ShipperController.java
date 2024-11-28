//package com.example.bookshop.controller;
//
//import com.example.bookshop.service.ShipperService;
//import com.example.bookshop.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/shipper")
//public class ShipperController {
//    @Autowired
//    UserService userService;
//    @Autowired
//    ShipperService shipperService;
//    @PostMapping("/orders/report-failed/{orderId}")
//    public ResponseEntity<String> reportFailedDelivery(
//            @PathVariable Integer orderId,
//            @RequestParam String reason) {
//        boolean isReported = shipperService.reportFailedDelivery(orderId, reason);
//        if (isReported) {
//            return ResponseEntity.ok("Reported failed delivery successfully.");
//        } else {
//            return ResponseEntity.badRequest().body("Failed to report delivery failure.");
//        }
//    }
//
//}

package com.example.bookshop.controller;

import com.example.bookshop.entity.Order;
import com.example.bookshop.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipper")
//@CrossOrigin(origins = "http://localhost:3000")
public class ShipperController {

    @Autowired
    private ShipperService shipperService;

    /**
     * Lấy danh sách đơn hàng có thể nhận
     */
    @GetMapping("/orders/available")
    public ResponseEntity<List<Order>> getAvailableOrders() {
        List<Order> availableOrders = shipperService.getAvailableOrders();
        return ResponseEntity.ok(availableOrders);
    }

    /**
     * Nhận đơn hàng
     * @param orderId ID của đơn hàng
     * @param shipperId ID của shipper
     */
    @PostMapping("/orders/accept")
    public ResponseEntity<String> acceptOrder(
            @RequestParam Integer orderId,
            @RequestParam Integer shipperId) {
        boolean isAccepted = shipperService.acceptOrder(orderId, shipperId);
        if (isAccepted) {
            return ResponseEntity.ok("Order accepted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to accept the order.");
        }
    }

    /**
     * Lấy danh sách đơn hàng của shipper
     * @param shipperId ID của shipper
     */
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrdersByShipper(@RequestParam Long shipperId) {
        List<Order> orders = shipperService.getOrdersByShipper(shipperId);
        return ResponseEntity.ok(orders);
    }

    /**
     * Báo cáo giao hàng không thành công
     * @param orderId ID của đơn hàng
     * @param reason Lý do giao hàng thất bại
     */
    @PostMapping("/orders/report-failed/{orderId}")
    public ResponseEntity<String> reportFailedDelivery(
            @PathVariable Integer orderId,
            @RequestParam String reason) {
        boolean isReported = shipperService.reportFailedDelivery(orderId, reason);
        if (isReported) {
            return ResponseEntity.ok("Reported failed delivery successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to report delivery failure.");
        }
    }
}
