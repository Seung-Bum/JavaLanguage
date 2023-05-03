package com.items.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // thymeleaf는 @RestController가 아니라 @Controller로
public class ExamController {
	@GetMapping("/th-map")
    public String thMap(Model model) {
        Map<String, Integer> score = new HashMap<>();
        score.put("userA", 100);
        score.put("userB", 90);
        score.put("userC", 50);
        model.addAttribute("score", score);
        return "thmap";
    }
}
