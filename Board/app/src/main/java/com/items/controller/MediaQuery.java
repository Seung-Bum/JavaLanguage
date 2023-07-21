package com.items.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MediaQuery {
	
	private static final Logger log = LogManager.getLogger(MediaQuery.class);
	
	// Media Query Test
	@RequestMapping("/mediaquery")
	public String mediaquery(Model model) {
		log.info("mediaquery test");
		return "mediaquery";
	}

}
