package com.items.controller;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MailController {
	
	private static final Logger log = LogManager.getLogger(BoardController.class);
	
	LoginController loginController = new LoginController();
	
	// ���� ������
	@RequestMapping("/mail")
	public String mailPage(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {		
		loginController.loginValidation(model, session, request, response);
		log.info("���� ������");
		return "mail";
	}	
	
	// google Mail API
	//@RequestMapping("/sendmail")
	//public void sendMail (String Template, String mailData) {
	//	
	//}
	
	@RequestMapping("/sendmail")
	public static String gmailSend(Model model) {
        String user = "sb910126@gmail.com"; 
        String password = "sb1272037!";   // �н�����
        
        // SMTP ���� ������ �����Ѵ�.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com"); 
        prop.put("mail.smtp.port", 465); // SMTP Port gmail 465, naver 587
        prop.put("mail.smtp.auth", "true"); 
        prop.put("mail.smtp.ssl.enable", "true"); 
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        // 3. SMTP ���������� ����� ������ ������� Session Ŭ������ �ν��Ͻ� ����
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));

            //�����ڸ����ּ�
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("pkapka_@naver.com")); 

            // Subject
            message.setSubject("�Ƴ��ϼ���"); //���� ������ �Է�

            // Text
            message.setText("�ȳ��ϼ���");    //���� ������ �Է�

            // send the message
            Transport.send(message); ////����
            System.out.println("message sent successfully...");
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        return "mail";
	}
}
