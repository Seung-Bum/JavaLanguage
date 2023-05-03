package com.items.Util;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MailUtil {
	
	@Autowired
	private TemplateEngine templateEngine; // html을 출력하는 소프트웨어
	
	public String getTemplateToHtml (String Template, String mailData) throws IOException {
		final ObjectMapper objectMapper = new ObjectMapper();
		final Map<String, Object> map = objectMapper.readValue(mailData, Map.class);
		return templateEngine.process(Template, new Context(Locale.getDefault(), map));
	}
}
