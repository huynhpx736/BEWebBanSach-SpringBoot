package com.example.bookshop.controller;

import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.AdminService;
import com.example.bookshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
//    @Autowired
//    private AdminService adminService;
    @Autowired
    private OrderService orderService;
    @GetMapping("cancel/{id}")
    public ResponseEntity<ResponseData> cancelOrder(@PathVariable int id, @RequestParam String status, @RequestParam String note) {
        orderService.cancelOrderByAdmin(id, status, note);
        return ResponseEntity.ok(new ResponseData(200, "Order is "+status, null, true));
    }
}
