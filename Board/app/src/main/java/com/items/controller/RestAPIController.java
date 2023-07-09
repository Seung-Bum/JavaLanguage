package com.items.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.items.service.RestAPIService;

@Controller
public class RestAPIController {
	
    @Value("${restapi.Service.call}")
    private String SERVICE_CALL;
    
	@Autowired
	RestAPIService restAPIService;
	
	@GetMapping("/restapicall")
	public String callAPI(Model model) {
		
		final Logger log = LogManager.getLogger(RestAPIController.class);
		HashMap<String, Object> result = new HashMap<String, Object>();		
		String jsonInString = "";
		
		try {
			// ** restTemplate api 호출해서 응답 받기 **
			// 실행 안되서 주석함 -> 키관련 에러 발생함
			// serviceKey와 값을 map에 담고 jsonStr로 변경후에 header혹은 request에 담으면 될것 같음
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
            //ResponseEntity<String> resultMap = restTemplate.exchange(SERVICE_CALL, HttpMethod.GET, entity, String.class);
			
            // HTTP POST 요청에 대한 응답 확인
            //System.out.println("status : " + resultMap.getStatusCode());
            //System.out.println("body : " + resultMap.getBody());
			
            // ** json 형태일 경우 parsing 방법 **
            // ObjectMapper mapper = new ObjectMapper();
            // jsonInString = mapper.writeValueAsString(resultMap.getBody());
            
			// Curl 프로세스 실행
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
			
			// XML 형태의 String 데이터 parsing		
			// StringBuffer 만들어서 사용 할 것이기에 StringBuffer 선언
			StringBuffer sb = new StringBuffer();
	        
			// 선언한 StringBuffer안에 xml형식 String 파일을 삽입
			sb.append(outString);
			
			// Document로 파싱하여 사용 할 것이기에 DocumentBuilderFactory 선언
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();			
	        
			// DocumentBuilderFactory로 DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			// sb.toString을 Document 형식으로 저장
			Document document = builder.parse(new InputSource(new StringReader(sb.toString())));
	        
			// document 안에서 찾고자 하는 태그값을 가져 와서 NodeList로 저장
	        NodeList taglist = document.getElementsByTagName("response");	        
	        NodeList response = (NodeList) taglist.item(0); // response
	        NodeList body = (NodeList) response.item(1); // body
	        NodeList items = (NodeList) body.item(1); // items
	        
	        HashMap<String, String> map = new HashMap<String, String>();
	        for (int i=0; i < items.getLength(); i++) {
	        	NodeList item = (NodeList) items.item(i);
	        	
	        	for (int j=0; j < item.getLength(); j++) {
	        		Node node = item.item(j);
	        		map.put(node.getNodeName(), node.getTextContent());
	        		restAPIService.insertAirInfo(map);
	        		log.info(node.getNodeName() + " " + node.getTextContent());	        		
	        	}
	        }
	        
        } catch (HttpClientErrorException | HttpServerErrorException e) {
        	log.info(e.getRawStatusCode());
        	log.info(e.getStatusText());
        } catch (Exception e) {
        	log.info(e.toString());
        }		
		return "login";
	}
	
	
}
