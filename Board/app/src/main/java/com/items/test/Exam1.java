package com.items.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exam1")
public class Exam1 {
	
	@GetMapping("/test1")
	public String test1() {
		return "ȫ�浿";
	}
	
    //���� ����
    //- ���� ������ �޸𸮸� �غ��Ű�� ��ɹ�
    //�׽�Ʈ URL:
    //=> http://localhost:8081/exam1/test2?name=pikachu&tel=010-1111-2222&gender=w
    @GetMapping("/test2")
    public String test2(String name, String tel, String gender) {
    	return "Ŭ���̾�Ʈ���� ���� �� = " + name + "," + tel + "," + gender;
    }
    
    // => http://localhost:8081/exam1/test3
    @GetMapping("/test3")
    public Object test3() {
      String[] names = new String[] {"ȫ�浿", "�Ӳ���", "������", "���߱�", "������"};
      return names;
    }
}
