package com.example.bookshop.controller;


import com.example.bookshop.dto.UserDTO;
import com.example.bookshop.payload.Request.SignInRequest;
import com.example.bookshop.payload.Request.SignUpRequest;
import com.example.bookshop.payload.ResponseData;
import com.example.bookshop.service.EmailService;
import com.example.bookshop.service.UserService;

import com.example.bookshop.util.OTPUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private OTPUtil otpUtil;
    @Autowired
    private EmailService emailService;

    @PostMapping("/register")
    public ResponseData registerUser(@RequestBody SignUpRequest signUpRequest) {
        try {
            UserDTO userDTO = userService.registerUser(signUpRequest);
            return new ResponseData(200, "User registered succes sfully", userDTO, true);
        } catch (Exception e) {
            return new ResponseData(400, e.getMessage(), null, false);
        }
    }

    @PostMapping("/login")
    public ResponseData loginUser(@RequestBody SignInRequest signInRequest) {
        try {
            UserDTO userDTO = userService.loginUser(signInRequest);
            return new ResponseData(200, "Login successfully", userDTO, true);
        } catch (Exception e) {
            return new ResponseData(400, e.getMessage(), null, false);
        }
    }

    @PostMapping("/verify-otp-register")
    public ResponseData verifyAccount(@RequestParam String email) {
        try {
            String otp = otpUtil.generateOtp();
            emailService.sendMailOTPTRegester(email, otp);
            return new ResponseData(200, "OTP verify register sent to email", otp, true);
        } catch (Exception e) {
            return new ResponseData(400, e.getMessage(), null, false);
        }
    }

    @PostMapping("/verify-otp-forgot-password")
    public ResponseData verifyForgotPassword(@RequestParam String email) {
        try {
            String otp = otpUtil.generateOtp();
            emailService.sendMailOTPForgotPassword(email, otp);
            return new ResponseData(200, "OTP verify fotgot password sent to email", otp, true);
        } catch (Exception e) {
            return new ResponseData(400, e.getMessage(), null, false);
        }
    }

}
