package com.app.pdf.access.controller;

import com.app.pdf.access.entity.User;
import com.app.pdf.access.service.EmailService;
import com.app.pdf.access.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    // POST: Register user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.registerUser(user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    // GET: Send OTP by email
    @GetMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            emailService.sendEmail(user);
            return new ResponseEntity<>("OTP sent to email: " + email, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // POST: Verify OTP
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = userService.verifyOtp(email, otp);
        if (isValid) {
            return new ResponseEntity<>("./resume.pdf", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Invalid or expired OTP", HttpStatus.UNAUTHORIZED);
        }
    }
}
