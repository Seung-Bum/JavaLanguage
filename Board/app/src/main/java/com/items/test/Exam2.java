package com.items.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam2")
public class Exam2 {
	
	  // �׽�Ʈ:
	  // => http://localhost:8081/exam2/test1?v1=2&v2=3&op=*
	  @GetMapping("/test1")
	  public String test1(int v1, int v2, String op) {
	    int result = 0;
	    switch (op) {
	      case "+": result = v1 + v2; break;
	      case "-": result = v1 - v2; break;
	      case "*": result = v1 * v2; break;
	      case "/": result = v1 / v2; break;
	      case "%": result = v1 % v2; break;
	      default: return "�ش� ������ ������ �� �����ϴ�.";
	    }

	    // �̷��� �������� ���������� ����� ȭ���� 
	    // HTML�� ����� ������ ���� 
	    // "������ ������(server-side rendering)"�̶� �θ���.
	    //
	    String html = "<!DOCTYPE html>"
	        + "<html>"
	        + "<head>"
	        + "<meta charset=\"UTF-8\">"
	        + "<title>���� Ȱ��</title>"
	        + "</head>"
	        + "<body>"
	        + "<h1>��� ���</h1>"
	        + "<p>" + v1 + " " + op + " " + v2 + " = " + result + "</p>"
	        + "</body>" 
	        + "</html>";

	    return html;
	  }
}
