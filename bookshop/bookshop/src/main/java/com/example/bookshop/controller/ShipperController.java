package com.example.bookshop.controller;

import com.example.bookshop.dto.OrderDTO;
import com.example.bookshop.entity.Order;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.ShipperService;
import com.example.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/shipper")
public class ShipperController {
    @Autowired
    private ShipperService shipperService;

    @Autowired
    private UserService userService;

    @PutMapping("/orders/update-shipperNote")
    public ResponseEntity<ResponseData> updateNoteShipper(
            @RequestParam Integer orderId,
            @RequestParam String shipperNote) {
        boolean isUpdated = shipperService.updateNoteShipper(orderId, shipperNote);
        if (isUpdated) {
            return ResponseEntity.ok(new ResponseData(200, "Note updated successfully.", null, true));
        } else {
            return ResponseEntity.badRequest().body(new ResponseData(400, "Failed to update the note.", null, false));
        }
    }
    @GetMapping("/orders/available")
    public ResponseEntity<ResponseData> getAvailableOrders() {
        List<OrderDTO> availableOrders = shipperService.getAvailableOrders();
        return ResponseEntity.ok(new ResponseData(200, "Success", availableOrders, true));
    }


    @PutMapping("/orders/accept")
    public ResponseEntity<ResponseData> acceptOrder(
            @RequestParam Integer orderId,
            @RequestParam Integer shipperId) {
        boolean isAccepted = shipperService.acceptOrder(orderId, shipperId);
        if (isAccepted) {
            return ResponseEntity.ok(new ResponseData(200, "Order accepted successfully.", null, true));
        } else {
            return ResponseEntity.badRequest().body(new ResponseData(400, "Failed to accept the order.", null, false));
        }
//        if (isAccepted) {
//            return ResponseEntity.ok("Order accepted successfully.");
//        } else {
//            return ResponseEntity.badRequest().body("Failed to accept the order.");
//        }
    }


    @GetMapping("/all-orders-by-shipperid ")
    public ResponseEntity<ResponseData> getOrdersByShipper(@RequestParam Integer shipperId) {
        List<OrderDTO> orders = shipperService.getAllOrdersByShipper(shipperId);
        return ResponseEntity.ok(new ResponseData(200, "Success", orders, true));
    }
    @GetMapping("/orders-by-shipperid-and-status")
    public ResponseEntity<ResponseData> getOrdersByShipperAndStatus(
            @RequestParam Integer shipperId,
            @RequestParam String status) {
        List<OrderDTO> orders = shipperService.getOrdersByShipperAndStatus(shipperId, status);
        return ResponseEntity.ok(new ResponseData(200, "Success", orders, true));
    }

    @PostMapping("/orders/report-failed/{orderId}")
    public ResponseEntity<String> reportFailedDelivery(
            @PathVariable Integer orderId,
            @RequestParam String reason,
            @RequestParam String note
    ) {
        boolean isReported = shipperService.reportFailedDelivery(orderId, reason, note);
        if (isReported) {
            return ResponseEntity.ok("Reported failed delivery successfully.");
        } else {
            return ResponseEntity.badRequest().body("Failed to report delivery failure.");
        }
    }
}
