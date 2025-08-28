package com.app.pdf.access.service;

import com.app.pdf.access.dao.UserDAO;
import com.app.pdf.access.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    public void registerUser(User user) {
        userDAO.save(user);
    }

    public User findByEmail(String email) {
        return userDAO.findByEmail(email).orElse(null);
    }

    public boolean verifyOtp(String email, String otp) {
        Optional<User> user = userDAO.findByEmail(email);
        return user.isPresent() && user.get().getOtp().equals(otp);
    }

}

