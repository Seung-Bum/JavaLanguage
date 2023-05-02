package com.items.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam1")
public class Exam1 {
	
	@GetMapping("/test1")
	public String test1() {
		return "홍길동";
	}
	
    //변수 선언
    //- 값을 저장할 메모리를 준비시키는 명령문
    //테스트 URL:
    //=> http://localhost:8081/exam1/test2?name=pikachu&tel=010-1111-2222&gender=w
    @GetMapping("/test2")
    public String test2(String name, String tel, String gender) {
    	return "클라이언트에서 받은 값 = " + name + "," + tel + "," + gender;
    }
    
    // => http://localhost:8081/exam1/test3
    @GetMapping("/test3")
    public Object test3() {
      String[] names = new String[] {"홍길동", "임꺽정", "유관순", "안중근", "윤봉길"};
      return names;
    }
}
