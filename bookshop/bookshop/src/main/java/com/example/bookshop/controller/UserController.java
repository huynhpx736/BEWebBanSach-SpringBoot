package com.example.bookshop.controller;

import com.example.bookshop.dto.UserDTO;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/update-classifications")
    public ResponseEntity<ResponseData> updateClassifications(@RequestParam int id, @RequestParam String classifications) {
        userService.updateClassifications(id, classifications);
        return ResponseEntity.ok(new ResponseData(200, "Classifications updated", null, true));
    }
    @PutMapping("/change-password/{id}")
    public ResponseEntity<ResponseData> changePassword(@PathVariable int id, @RequestParam String oldPassword, @RequestParam String newPassword) {
        userService.changePassword(id, oldPassword, newPassword);
        return ResponseEntity.ok(new ResponseData(200, "Password changed", null, true));
    }
    @GetMapping("/get-all")
    public ResponseEntity<ResponseData> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(new ResponseData(200, "Success", users, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData> getUserById(@PathVariable int id) {
        UserDTO user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(new ResponseData(200, "Success", user, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "User not found", null, false));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseData> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return ResponseEntity.status(201).body(new ResponseData(201, "User created", createdUser, true));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ResponseData> updateUser(@PathVariable int id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUser = userService.updateUser(id, userDTO);
        if (updatedUser != null) {
            return ResponseEntity.ok(new ResponseData(200, "User updated", updatedUser, true));
        } else {
            return ResponseEntity.status(404).body(new ResponseData(404, "User not found", null, false));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseData> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ResponseData(200, "User deleted", null, true));
    }
}
