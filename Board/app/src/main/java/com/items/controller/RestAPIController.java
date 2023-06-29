package com.items.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Controller
public class RestAPIController {
	
    @Value("${restapi.Service.call}")
    private String SERVICE_CALL;
	
	@GetMapping("/restapicall")
	public String callAPI(Model model) {
		
		final Logger log = LogManager.getLogger(RestAPIController.class);
		HashMap<String, Object> result = new HashMap<String, Object>();		
		String jsonInString = "";
		
		try {
			// ** restTemplate api 호출해서 응답 받기 **
			// 실행 안되서 주석함 -> 키관련 에러 발생함
            //HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            //factory.setConnectTimeout(5000); //타임아웃 설정 5초
            //factory.setReadTimeout(5000);//타임아웃 설정 5초
            //RestTemplate restTemplate = new RestTemplate(factory);
            
            //HttpHeaders headers = new HttpHeaders();
            //headers.setContentType(MediaType.APPLICATION_JSON);
            //headers.add("Content-Type", "application/json"); 
            //headers.add("Accept-Charset", "UTF-8"); 
            //HttpEntity<?> entity = new HttpEntity<>(headers);
            
			//이 한줄의 코드로 API를 호출해 MAP타입으로 전달 받는다.
            //ResponseEntity<String> resultMap = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
			
            // HTTP POST 요청에 대한 응답 확인
            //System.out.println("status : " + resultMap.getStatusCode());
            //System.out.println("body : " + resultMap.getBody());
                             
    		StringBuffer output = new StringBuffer();
    		Process p = Runtime.getRuntime().exec(SERVICE_CALL); // curl 실행결과
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
				//System.out.println(line.toString());
			}
			//p.waitFor();
			String outString = output.toString();
            
			System.out.println(outString);
			
            //데이터를 제대로 전달 받았는지 확인 string형태로 파싱해줌
            //ObjectMapper mapper = new ObjectMapper();
            //jsonInString = mapper.writeValueAsString(resultMap.getBody());
			
			
			
			
			
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println(e.getRawStatusCode());
            System.out.println(e.getStatusText()); 
        } catch (Exception e) {
            System.out.println(e.toString());
        }		
		return "login";
	}
	
	
}
