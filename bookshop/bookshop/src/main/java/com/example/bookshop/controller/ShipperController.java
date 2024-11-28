package com.example.bookshop.controller;

import com.example.bookshop.entity.Order;
import com.example.bookshop.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/shipper")
public class ShipperController {
    @Autowired
    private ShipperService shipperService;


    @GetMapping("/orders/available")
    public ResponseEntity<List<Order>> getAvailableOrders() {
        List<Order> availableOrders = shipperService.getAvailableOrders();
        return ResponseEntity.ok(availableOrders);
    }


    @PutMapping("/orders/accept")
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

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrdersByShipper(@RequestParam Integer shipperId) {
        List<Order> orders = shipperService.getOrdersByShipper(shipperId);
        return ResponseEntity.ok(orders);
    }

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
