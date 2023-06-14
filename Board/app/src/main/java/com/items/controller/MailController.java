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

	// ���� ������
	@RequestMapping("/mail")
	public String mailPage(Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		log.info("mail ������ ����");
		String result = loginController.loginValidation(session, request, response);
		if (result == "login") {return "login";}
		else {return "mail";}		
	}	
	
	// ���� �߼�
    @RequestMapping("/sendmail")
    public String sendPlainTextEmail(Model model, String name, String mail, String subject, String message) {
        
    	log.info(mail);
    	
        Properties p = System.getProperties();
        p.put("mail.smtp.starttls.enable", "true");     // gmail�� true ����
        p.put("mail.smtp.host", "smtp.naver.com");      // smtp ���� �ּ�
        p.put("mail.smtp.auth","true");                 // gmail�� true ����
        p.put("mail.smtp.port", "587");                 // ���̹� ��Ʈ   
        log.info("smtp ����");
        
        class MyAuthentication extends Authenticator {            
            PasswordAuthentication pa;
            public MyAuthentication(){             
                String id = "pkapka_@naver.com";  //���̹� �̸��� ���̵�
                String pw = "";        //���̹� ��й�ȣ
         
                // ID�� ��й�ȣ�� �Է��Ѵ�.
                pa = new PasswordAuthentication(id, pw);
            }
         
            // �ý��ۿ��� ����ϴ� ��������
            public PasswordAuthentication getPasswordAuthentication() {
                return pa;
            }
        }
                
        Authenticator auth = new MyAuthentication();
        log.info("auth ����");
        
        //session ���� ��  MimeMessage����
        Session session = Session.getDefaultInstance(p, auth);
        MimeMessage msg = new MimeMessage(session);        
        log.info("session ����");
        
        try{
            //���������ð�
            msg.setSentDate(new Date());
            InternetAddress from = new InternetAddress() ;
            from = new InternetAddress("pkapka_@naver.com"); //�߽��� ���̵�            
            log.info("�߽��� ����");
            
            // �̸��� �߽���
            msg.setFrom(from);
            
            // �̸��� ������
            InternetAddress to = new InternetAddress("pkapka_@naver.com"); //hlpark0209@naver.com
            msg.setRecipient(Message.RecipientType.TO, to);
            
            // �̸��� ����
            msg.setSubject("��������", "UTF-8");
            
            // �̸��� ����
            msg.setText("��������", "UTF-8");
            
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
        return "mail";
    }   
    	

}
