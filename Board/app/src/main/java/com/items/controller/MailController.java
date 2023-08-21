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
import org.springframework.scheduling.annotation.Scheduled;
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
	
	private static final Logger log = LogManager.getLogger(MailController.class);
	
	@Autowired
	MailService mailService;
	
	LoginUtil loginUtil = new LoginUtil();
	
	// ���� ������
	@GetMapping("/write")
	public String mailPage(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {		
		log.info("mail ������ ����");
		if (loginUtil.loginValidation(session, request, response) == "login") {return "login";}
		else {
			String user_id = (String) session.getAttribute("user_id");
	        model.addAttribute("sendMailUser", user_id);
			return "mailPage";
		}
	}
	
	// ���� �߼�
	@PostMapping("/sendmail")
	public String sendPlainTextEmail(Model model, HttpSession sentSession, String email, String subject, String message) {
    	
    	String user_id = (String) sentSession.getAttribute("user_id"); // �߽��� ����    	
    	log.info("������ ���� : " + email + ", �߽��� ���� : " + user_id);
    	
        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");     // gmail�� true ����
        p.put("mail.smtp.host", "smtp.naver.com");      // smtp ���� �ּ�
        p.put("mail.smtp.auth","true");                 // gmail�� true ����
        p.put("mail.smtp.port", "587");                 // ���̹� ��Ʈ
        log.info("smtp ����");
        
        class MyAuthentication extends Authenticator {            
            PasswordAuthentication pa;
            
            public MyAuthentication(String idPram){
                String id = idPram;  //���̹� �̸��� ���̵�
                String pw = mailService.loginMailAuth(idPram).getPassword();  //���̹� ��й�ȣ
         
                // ID�� ��й�ȣ�� �Է��Ѵ�.
                pa = new PasswordAuthentication(id, pw);
            }
         
            // �ý��ۿ��� ����ϴ� ��������
            public PasswordAuthentication getPasswordAuthentication() {
                return pa;
            }
        }
                
        Authenticator auth = new MyAuthentication(user_id);
        log.info("auth ����");
        
        //session ���� ��  MimeMessage����
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);        
        log.info("session ����");
        
        try{
            //���������ð�
            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress();
            from = new InternetAddress(user_id); //�߽��� ���̵�            
            log.info("�߽��� ����");
            
            // �̸��� �߽���
            msg.setFrom(from);
            
            // �̸��� ������
            InternetAddress to = new InternetAddress(email);
            msg.setRecipient(Message.RecipientType.TO, to);
            
            // �̸��� ����
            msg.setSubject(subject, "UTF-8");
            
            // �̸��� ����
            msg.setText(message, "UTF-8");
            
            // �̸��� ���
            msg.setHeader("content-Type", "text/html");
            
            //���Ϻ�����
            javax.mail.Transport.send(msg, msg.getAllRecipients());
            
            log.info("mail �߼� �Ϸ�");
            model.addAttribute("mailSuccessYn", "Y"); // �������� ������ ��� ������ Ʈ���Ÿ� Y�� ����
        }catch (AddressException addr_e) {
        	model.addAttribute("mailSuccessYn", "N"); // �������� ������ ������ �������� N���� ����
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
	
	// ���� ������ �߼�
	@PostMapping("/sendmailScheduled")
	@Scheduled(cron = "0 58 22 * * ?") // �� �� �� �� �� ����
	public void sendPlainTextEmailScheduled() { 	
		
    	String user_id = "pkapka_@naver.com"; // �߽��� ���� 
    	String email = "hlpark0209@naver.com";
    	String subject = "������� �߼�";
    	String message = "��������� �߼��մϴ�.";
    	
    	log.info("���� �����ٷ� - ������ ���� : " + email + "�߽��� ���� : " + user_id);
    	
        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");     // gmail�� true ����
        p.put("mail.smtp.host", "smtp.naver.com");      // smtp ���� �ּ�
        p.put("mail.smtp.auth","true");                 // gmail�� true ����
        p.put("mail.smtp.port", "587");                 // ���̹� ��Ʈ
        log.info("smtp ����");
        
        class MyAuthentication extends Authenticator {            
            PasswordAuthentication pa;
            
            public MyAuthentication(String idPram){
                String id = idPram;  //���̹� �̸��� ���̵�
                String pw = mailService.loginMailAuth(idPram).getPassword();  // DB���� ��й�ȣ ��������
         
                // ID�� ��й�ȣ�� �Է��Ѵ�.
                pa = new PasswordAuthentication(id, pw);
            }
         
            // �ý��ۿ��� ����ϴ� ��������
            public PasswordAuthentication getPasswordAuthentication() {
                return pa;
            }
        }
                
        Authenticator auth = new MyAuthentication(user_id);
        log.info("auth ����");
        
        //session ���� �� MimeMessage����
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);        
        log.info("session ����");
        
        try{
            //���������ð�
            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress();
            from = new InternetAddress(user_id); //�߽��� ���̵�            
            log.info("�߽��� ����");
            
            // �̸��� �߽���
            msg.setFrom(from);
            
            // �̸��� ������
            InternetAddress to = new InternetAddress(email);
            msg.setRecipient(Message.RecipientType.TO, to);
            
            // �̸��� ����
            msg.setSubject(subject, "UTF-8");
            
            // �̸��� ����
            msg.setText(message, "UTF-8");
            
            // �̸��� ���
            msg.setHeader("content-Type", "text/html");
            
            //���Ϻ�����
            javax.mail.Transport.send(msg, msg.getAllRecipients());
            
            log.info("mail �߼� �Ϸ�");
        }catch (AddressException addr_e) {
            addr_e.printStackTrace();
        }catch (MessagingException msg_e) {
            msg_e.printStackTrace();
        }catch (Exception msg_e) {
            msg_e.printStackTrace();
        }        
    } 

}
