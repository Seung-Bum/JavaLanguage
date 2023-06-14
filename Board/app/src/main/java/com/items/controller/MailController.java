package com.items.controller;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
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

import com.items.Util.MailUtil;

@Controller
public class MailController {
	
	private static final Logger log = LogManager.getLogger(MailController.class);
	
	LoginController loginController = new LoginController();
	MailUtil mailUtil = new MailUtil();

	// 메일 페이지
	@RequestMapping("/mail")
	public String mailPage(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		log.info("mail 페이지 진행");
		String result = loginController.loginValidation(session, request, response);
		if (result == "login") {return "login";}
		else {return "mail";}		
	}	
	
	// 메일 발송
    @RequestMapping("/sendmail")
    public String sendPlainTextEmail(Model model, String name, String mail, String subject, String message) {
        
    	log.info(mail);
    	
        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");     // gmail은 true 고정
        p.put("mail.smtp.host", "smtp.naver.com");      // smtp 서버 주소
        p.put("mail.smtp.auth","true");                 // gmail은 true 고정
        p.put("mail.smtp.port", "587");                 // 네이버 포트   
        log.info("smtp 설정");
        
        class MyAuthentication extends Authenticator {            
            PasswordAuthentication pa;
            public MyAuthentication(){             
                String id = "pkapka_@naver.com";  //네이버 이메일 아이디
                String pw = "";        //네이버 비밀번호
         
                // ID와 비밀번호를 입력한다.
                pa = new PasswordAuthentication(id, pw);
            }
         
            // 시스템에서 사용하는 인증정보
            public PasswordAuthentication getPasswordAuthentication() {
                return pa;
            }
        }
                
        Authenticator auth = new MyAuthentication();
        log.info("auth 생성");
        
        //session 생성 및  MimeMessage생성
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);        
        log.info("session 생성");
        
        try{
            //편지보낸시간
            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress() ;
            from = new InternetAddress("pkapka_@naver.com"); //발신자 아이디            
            log.info("발신자 설정");
            
            // 이메일 발신자
            msg.setFrom(from);
            
            // 이메일 수신자
            InternetAddress to = new InternetAddress("pkapka_@naver.com"); //hlpark0209@naver.com
            msg.setRecipient(Message.RecipientType.TO, to);
            
            // 이메일 제목
            msg.setSubject("메일제목", "UTF-8");
            
            // 이메일 내용
            msg.setText("ㅎㅇㅎㅇ", "UTF-8");
            
            // 이메일 헤더
            msg.setHeader("content-Type", "text/html");
            
            //메일보내기
            javax.mail.Transport.send(msg, msg.getAllRecipients());
            
            log.info("mail 발송 완료");           
        }catch (AddressException addr_e) {
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) {
            msg_e.printStackTrace();
        }catch (Exception msg_e) {
            msg_e.printStackTrace();
        }
        return "mail";
    }   
    	

}
