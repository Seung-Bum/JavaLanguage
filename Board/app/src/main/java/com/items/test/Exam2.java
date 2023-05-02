package com.items.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam2")
public class Exam2 {
	
	  // 테스트:
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
	      default: return "해당 연산을 수행할 수 없습니다.";
	    }

	    // 이렇게 서버에서 웹브라우저가 출력할 화면을 
	    // HTML로 만들어 보내는 것을 
	    // "서버측 렌더링(server-side rendering)"이라 부른다.
	    //
	    String html = "<!DOCTYPE html>"
	        + "<html>"
	        + "<head>"
	        + "<meta charset=\"UTF-8\">"
	        + "<title>변수 활용</title>"
	        + "</head>"
	        + "<body>"
	        + "<h1>계산 결과</h1>"
	        + "<p>" + v1 + " " + op + " " + v2 + " = " + result + "</p>"
	        + "</body>" 
	        + "</html>";

	    return html;
	  }
}
