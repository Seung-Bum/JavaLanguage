package com.items.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import org.springframework.http.*;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class RestAPIController {
	
    @Value("${restapi.Service.Key}")
    private String SERVICE_KEY;
	
	@GetMapping("/restapicall")
	public String callAPI(Model model) {
		
		final Logger log = LogManager.getLogger(RestAPIController.class);
		HashMap<String, Object> result = new HashMap<String, Object>();		
		String jsonInString = "";
		
		try {
			
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000); //Ÿ�Ӿƿ� ���� 5��
            factory.setReadTimeout(5000);//Ÿ�Ӿƿ� ���� 5��
            RestTemplate restTemplate = new RestTemplate(factory);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json"); 
            headers.add("Accept-Charset", "UTF-8"); 
            headers.set("Accept", "*/*;q=0.9"); // HTTP_ERROR ����
            HttpEntity<?> entity = new HttpEntity<>(headers);                                          
         
            String frontUrl = "http://apis.data.go.kr/1360000/AirInfoService/getAirInfo?serviceKey=";
            String backUrl = "&pageNo=1&numOfRows=10&fctm=202306120000&icaoCode=RKSI"; // icaoCode �������� �ڵ�
            
            //UriComponents uri = UriComponentsBuilder.fromHttpUrl(frontUrl+SERVICE_KEY+backUrl).build();
            
            String uri = frontUrl+SERVICE_KEY+backUrl;
            
            System.out.println(uri);
            
            //�� ������ �ڵ�� API�� ȣ���� MAPŸ������ ���� �޴´�.
            ResponseEntity<String> resultMap = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            //result.put("statusCode", resultMap.getStatusCodeValue()); //http status code�� Ȯ��
            //result.put("header", resultMap.getHeaders()); //��� ���� Ȯ��
            //result.put("body", resultMap.getBody()); //���� ������ ���� Ȯ��
            
            // HTTP POST ��û�� ���� ���� Ȯ��
            System.out.println("status : " + resultMap.getStatusCode());
            System.out.println("body : " + resultMap.getBody());
            
            //�����͸� ����� ���� �޾Ҵ��� Ȯ�� string���·� �Ľ�����
            //ObjectMapper mapper = new ObjectMapper();
            //jsonInString = mapper.writeValueAsString(resultMap.getBody());
            
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            result.put("statusCode", e.getRawStatusCode());
            result.put("body"  , e.getStatusText());
            System.out.println("dfdfdfdf");
            System.out.println(e.toString());
 
        } catch (Exception e) {
            result.put("statusCode", "999");
            result.put("body"  , "excpetion����");
            System.out.println(e.toString());
        }		
		
		return "login";
	}
	
	
}
