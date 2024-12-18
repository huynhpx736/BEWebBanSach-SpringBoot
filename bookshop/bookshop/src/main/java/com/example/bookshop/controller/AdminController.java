package com.example.bookshop.controller;

import com.example.bookshop.payload.EmailDetails;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.AdminService;
import com.example.bookshop.service.EmailService;
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
    @Autowired
    private EmailService emailService;

    @PutMapping("/orders/update-adminNote")
    public ResponseEntity<ResponseData> updateNoteAdmin(
            @RequestParam Integer orderId,
            @RequestParam String adminNote) {
        boolean isUpdated = orderService.updateNoteOrder(orderId, adminNote);
        if (isUpdated) {
            return ResponseEntity.ok(new ResponseData(200, "Note updated successfully.", null, true));
        } else {
            return ResponseEntity.badRequest().body(new ResponseData(400, "Failed to update the note.", null, false));
        }
    }
    @GetMapping("cancel/{id}")
    public ResponseEntity<ResponseData> cancelOrderByAdmin(@PathVariable int id, @RequestParam String cancelReason, @RequestParam(required = false) String note) {
        orderService.cancelOrderByAdmin(id, cancelReason, note);
        return ResponseEntity.ok(new com.example.bookshop.payload.ResponseData(200, "Order is canceled", null, true));
    }

    //gui email
    @PostMapping("send-email")
    public ResponseEntity<ResponseData> sendSimpleMail(@RequestBody EmailDetails details) {
        String result = emailService.sendSimpleMail(details);
        return ResponseEntity.ok(new ResponseData(200, result, null, true));
    }

    @PostMapping("send-email-attachment")
    public ResponseEntity<ResponseData> sendMailWithAttachment(@RequestBody EmailDetails details) {
        String result = emailService.sendMailWithAttachment(details);
        return ResponseEntity.ok(new ResponseData(200, result, null, true));
    }
    @PostMapping("send-email-to-customer")
    public ResponseEntity<ResponseData> sendMailToCustomer(@RequestParam Integer orderId, @RequestParam String subject) {
        String result = emailService.sendMailToCustomer(orderId, subject);
        if (result.equals("Order not found")) {
            return ResponseEntity.status(404).body(new ResponseData(404, result, null, false));
        } else if (result.equals("Email sending successfully")) {
            return ResponseEntity.ok(new ResponseData(200, result, null, true));
        } else {
            return ResponseEntity.status(500).body(new ResponseData(500, result, null, false));
        }
    }



}
