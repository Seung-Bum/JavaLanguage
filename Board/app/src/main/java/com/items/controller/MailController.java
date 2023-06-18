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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.items.Util.LoginUtil;
import com.items.service.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	MailService mailService;
	
	private static final Logger log = LogManager.getLogger(MailController.class);
	
	LoginUtil loginUtil = new LoginUtil();
	
	// 메일 페이지
	@GetMapping("/write")
	public String mailPage(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {		
		log.info("mail 페이지 진행");
		if (loginUtil.loginValidation(session, request, response) == "login") {return "login";}
		else {
			String user_id = (String) session.getAttribute("user_id");
	        model.addAttribute("sendMailUser", user_id);
			return "mailPage";
		}
	}
	
	// 메일 발송
	@PostMapping("/sendmail")
	public String sendPlainTextEmail(Model model, HttpSession sentSession, String email, String subject, String message) {
    	
    	String user_id = (String) sentSession.getAttribute("user_id"); // 발신자 메일    	
    	log.info("수신자 메일 : " + email + ", 발신자 메일 : " + user_id);
    	
        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");     // gmail은 true 고정
        p.put("mail.smtp.host", "smtp.naver.com");      // smtp 서버 주소
        p.put("mail.smtp.auth","true");                 // gmail은 true 고정
        p.put("mail.smtp.port", "587");                 // 네이버 포트
        log.info("smtp 설정");
        
        class MyAuthentication extends Authenticator {            
            PasswordAuthentication pa;
            
            public MyAuthentication(String idPram){
                String id = idPram;  //네이버 이메일 아이디
                String pw = mailService.loginMailAuth(idPram).getPassword();  //네이버 비밀번호
         
                // ID와 비밀번호를 입력한다.
                pa = new PasswordAuthentication(id, pw);
            }
         
            // 시스템에서 사용하는 인증정보
            public PasswordAuthentication getPasswordAuthentication() {
                return pa;
            }
        }
                
        Authenticator auth = new MyAuthentication(user_id);
        log.info("auth 생성");
        
        //session 생성 및  MimeMessage생성
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);        
        log.info("session 생성");
        
        try{
            //편지보낸시간
            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress();
            from = new InternetAddress(user_id); //발신자 아이디            
            log.info("발신자 설정");
            
            // 이메일 발신자
            msg.setFrom(from);
            
            // 이메일 수신자
            InternetAddress to = new InternetAddress(email);
            msg.setRecipient(Message.RecipientType.TO, to);
            
            // 이메일 제목
            msg.setSubject(subject, "UTF-8");
            
            // 이메일 내용
            msg.setText(message, "UTF-8");
            
            // 이메일 헤더
            msg.setHeader("content-Type", "text/html");
            
            //메일보내기
            javax.mail.Transport.send(msg, msg.getAllRecipients());
            
            log.info("mail 발송 완료");
            model.addAttribute("mailSuccessYn", "Y"); // 메일전송 과정이 모두 끝나면 트리거를 Y로 설정
        }catch (AddressException addr_e) {
        	model.addAttribute("mailSuccessYn", "N"); // 메일전송 과정에 문제가 생겼을때 N으로 설정
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) {
        	model.addAttribute("mailSuccessYn", "N");
            msg_e.printStackTrace();
        }catch (Exception msg_e) {
        	model.addAttribute("mailSuccessYn", "N");
            msg_e.printStackTrace();
        }        
        return "mailPage";
    }   

}
