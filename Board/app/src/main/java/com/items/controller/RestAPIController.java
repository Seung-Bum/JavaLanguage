package com.items.controller;

import java.net.URLDecoder;
import java.net.URLEncoder;
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
            //headers.set("Accept", "*/*;q=0.9"); // HTTP_ERROR ����
            HttpEntity<?> entity = new HttpEntity<>(headers);                                          
         
            String frontUrl = "http://apis.data.go.kr/1360000/AirInfoService/getAirInfo?serviceKey=";
            String backUrl = "&pageNo=1&numOfRows=10&fctm=202306270000&icaoCode=RKSI"; // icaoCode �������� �ڵ�
            
            //UriComponents uri = UriComponentsBuilder.fromHttpUrl(frontUrl+SERVICE_KEY+backUrl).build();

            //String encodingKey = URLEncoder.encode(SERVICE_KEY, "UTF-8");
            //String decodingKey = URLDecoder.decode(SERVICE_KEY, "UTF-8");           
            
            String uri = frontUrl+SERVICE_KEY+backUrl;
            
            System.out.println(uri);
            
            //�� ������ �ڵ�� API�� ȣ���� MAPŸ������ ���� �޴´�.
            ResponseEntity<String> resultMap = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            
            // HTTP POST ��û�� ���� ���� Ȯ��
            System.out.println("status : " + resultMap.getStatusCode());
            System.out.println("body : " + resultMap.getBody());
            
            //�����͸� ����� ���� �޾Ҵ��� Ȯ�� string���·� �Ľ�����
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
