package com.items.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
			// Curl ���μ��� ����
    		StringBuffer output = new StringBuffer();
    		Process p = Runtime.getRuntime().exec(SERVICE_CALL); // curl ������
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
				System.out.println(line.toString());
			}
			//p.waitFor();
			String outString = output.toString();          
			
			// XML ������ String ������ parsing		
			// StringBuffer ���� ��� �� ���̱⿡ StringBuffer ����
			StringBuffer sb = new StringBuffer();
	        
			// ������ StringBuffer�ȿ� xml���� String ������ ����
			sb.append(outString);
			
			// Document�� �Ľ��Ͽ� ��� �� ���̱⿡ DocumentBuilderFactory ����
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();			
	        
			// DocumentBuilderFactory�� DocumentBuilder
			DocumentBuilder builder = factory.newDocumentBuilder();
			
			// sb.toString�� Document �������� ����
			Document document = builder.parse(new InputSource(new StringReader(sb.toString())));
	        
			// document �ȿ��� ã���� �ϴ� �±װ��� ���� �ͼ� NodeList�� ����
	        NodeList taglist = document.getElementsByTagName("response");	        
	        NodeList response = (NodeList) taglist.item(0); // response
	        NodeList body = (NodeList) response.item(1); // body
	        NodeList items = (NodeList) body.item(1); // items
	        
	        String nodeName;
	        String nodeValue;
	        
	        HashMap<String, Object> map = new HashMap<String, Object>();
	        for (int i=0; i < items.getLength(); i++) {
	        	NodeList item = (NodeList) items.item(i);
	        	
	        	for (int j=0; j < item.getLength(); j++) {
	        		Node node = item.item(j);
	        		nodeName = node.getNodeName();
	        		nodeValue = node.getTextContent();
	        		//log.info(node.getNodeName() + " " + node.getTextContent());
	        		//log.info(nodeName + " " + nodeValue);
	        		
	        		if ((nodeName != null || nodeName != "") && (nodeValue != null || nodeValue != "")) {
		        		map.put(nodeName, nodeValue);		        		
		        		//log.info(nodeName + " " + nodeValue);	  
	        		} else {
	        			map.put(nodeName, "null");
	        			restAPIService.insertAirInfo(map);
	        			//log.info(nodeName + " " + "null");
	        		}
	        	}
	        	log.info(map.get("airportName") + " ");
	        	log.info(map.get("icaoCode") + " ");
	        	restAPIService.insertAirInfo(map);
	        }
	        
        } catch (HttpClientErrorException | HttpServerErrorException e) {
        	log.info(e.getRawStatusCode());
        	log.info(e.getStatusText());
        } catch (Exception e) {
        	log.info(e.toString());
        }		
		return "login";
	}
	
	@GetMapping("/restcall")
	public String restTemplateAPI(Model model) throws ClientProtocolException, IOException {
		
		String url = "http://apis.data.go.kr/1360000/AirInfoService/getAirInfo?pageNo=1&numOfRows=10&fctm=202306300000&icaoCode=RKSI";
		//String url = "http://apis.data.go.kr/1360000/AirInfoService/getAirInfo?serviceKey=kry52Qun7PJGODw51SGulaC5UitRsf1%2Bhts8gSWXpb7zYRfruRDZIB%2F5cXiWZk0oGSClTajuFU9bOul9kuYP5g%3D%3D&pageNo=1&numOfRows=10&fctm=202306290000&icaoCode=RKSI";
		final Logger log = LogManager.getLogger(RestAPIController.class);
		RestTemplate rt = new RestTemplate();
		
		// �ش� ����� 
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
		//headers.add("Content-type", "text/plain; charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("serviceKey", "kry52Qun7PJGODw51SGulaC5UitRsf1%2Bhts8gSWXpb7zYRfruRDZIB%2F5cXiWZk0oGSClTajuFU9bOul9kuYP5g%3D%3D");
		
		// �ش��� �ٵ� �ϳ��� ������Ʈ�� �����
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);
        
		// Http ��û�ϰ� ���ϰ��� response ������ �ޱ�
		ResponseEntity<String> response = rt.exchange(
				url, // Host
				HttpMethod.POST, // Request Method
				kakaoTokenRequest,	// RequestBody
				String.class);	// return Object
		
        // HTTP POST ��û�� ���� ���� Ȯ��
        System.out.println("status : " + response.getStatusCode());
        System.out.println("body : " + response.getBody());
		
        // ** json ������ ��� parsing ��� **
         //ObjectMapper mapper = new ObjectMapper();
         //try {
			//jsonInString = mapper.writeValueAsString(resultMap.getBody());
		//} catch (JsonProcessingException e) {
		//	log.error(e.getMessage());
		//}
		
		return "login";
	}

}
