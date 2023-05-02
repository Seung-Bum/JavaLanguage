package com.items.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Exam3 {
	// http://localhost:8081/test/m1
	@RequestMapping("test/m1")
	public String m1() {
		return "test/m1 ½ÇÇàµÊ!";
	}
}
