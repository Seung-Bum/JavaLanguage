/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class App {    
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }    
    
    @RequestMapping("/hello")
    String Hello() {
      return "Hello World!";
    }

	public Object getGreeting() {
		return "Hello World!";
	}

}
