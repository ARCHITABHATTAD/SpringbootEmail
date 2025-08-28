package com.app.pdf.access.service;

import com.app.pdf.access.dao.UserDAO;
import com.app.pdf.access.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(User user){
        try {
            String otp = String.valueOf(new Random().nextInt(900000) + 100000);
            user.setOtp(otp);
            userDAO.save(user); // SAVE the OTP to DB

            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(user.getEmail());
            mail.setSubject("Your One Time Password For Access");
            mail.setText(otp);
            javaMailSender.send(mail);
        } catch(Exception e){
            log.error("Exception while sending mail: " + e);
        }
    }
}

//@Autowired
//private EmailService emailService;
//
//@Test
//void testSendMail(){
//    emailService.sendEmail("","Testing java mail sender","Hi, not send pasword for now");
//}