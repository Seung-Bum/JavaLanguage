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
	
	// 메일 페이지
	@RequestMapping("/mail")
	public String mailPage(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {		
		loginController.loginValidation(model, session, request, response);
		log.info("메일 페이지");
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
        String password = "sb1272037!";   // 패스워드
        
        // SMTP 서버 정보를 설정한다.
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com"); 
        prop.put("mail.smtp.port", 465); // SMTP Port gmail 465, naver 587
        prop.put("mail.smtp.auth", "true"); 
        prop.put("mail.smtp.ssl.enable", "true"); 
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        // 3. SMTP 서버정보와 사용자 정보를 기반으로 Session 클래스의 인스턴스 생성
        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
        
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));

            //수신자메일주소
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("pkapka_@naver.com")); 

            // Subject
            message.setSubject("아녕하세요"); //메일 제목을 입력

            // Text
            message.setText("안녕하세요");    //메일 내용을 입력

            // send the message
            Transport.send(message); ////전송
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
