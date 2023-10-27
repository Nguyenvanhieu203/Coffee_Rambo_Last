/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import Models.Data.ModelMessage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


/**
 *
 * @author nguye
 */
public class ServiceMail {
    public ModelMessage sendMain(String toEmail, String code) {
        ModelMessage ms = new ModelMessage(false, "");
        //Email người gửi
        String from = "coffeerambo2023@gmail.com";
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");

        String username = "coffeerambo2023@gmail.com";//Email nhà hàng
        //Mật khẩu :thedreamers123456
        //App password :zklylvvnbkptvrhc
        String password = "unjctbittphqexcy";    //  Mật khẩu email nhà hàng
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject("Verify Code");
            message.setText(code + " là mã xác minh tài khoản"
                            + " CoffeeRambo của bạn.");
            Transport.send(message);
            ms.setSuccess(true);
        } catch (MessagingException e) {
            if (e.getMessage().equals("Invalid Addresses")) {
                ms.setMessage("Email không chính xác");
            } else {
                ms.setMessage("Lỗi");
                e.printStackTrace();
            }
        }
        return ms;
    }
}
