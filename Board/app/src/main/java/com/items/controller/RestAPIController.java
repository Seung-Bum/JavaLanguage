package com.items.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
			// Curl 프로세스 실행
    		StringBuffer output = new StringBuffer();
    		Process p = Runtime.getRuntime().exec(SERVICE_CALL); // curl 실행결과
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
				System.out.println(line.toString());
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
	public String restTemplateAPI(Model model) throws Exception {
		
		String url = "http://apis.data.go.kr/1360000/AirInfoService/getAirInfo?pageNo=1&numOfRows=10&fctm=202306300000&icaoCode=RKSI";
		//String url = "http://apis.data.go.kr/1360000/AirInfoService/getAirInfo?serviceKey=kry52Qun7PJGODw51SGulaC5UitRsf1%2Bhts8gSWXpb7zYRfruRDZIB%2F5cXiWZk0oGSClTajuFU9bOul9kuYP5g%3D%3D&pageNo=1&numOfRows=10&fctm=202306290000&icaoCode=RKSI";
		final Logger log = LogManager.getLogger(RestAPIController.class);
		RestTemplate rt = new RestTemplate();
		
		// 해더 만들기 
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		//headers.add("Content-type", "application/x-www-form-urlencoded; charset=utf-8");		
		//headers.add("Content-type", "text/plain; charset=utf-8");
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("serviceKey", "kry52Qun7PJGODw51SGulaC5UitRsf1%2Bhts8gSWXpb7zYRfruRDZIB%2F5cXiWZk0oGSClTajuFU9bOul9kuYP5g%3D%3D");
		
		// 해더와 바디를 하나의 오브젝트로 만들기
		HttpEntity<MultiValueMap<String, String>> TokenRequest = new HttpEntity<>(params, headers);
        
		// Http 요청하고 리턴값을 response 변수로 받기
		ResponseEntity<String> response = rt.exchange(
			url, // Host
			HttpMethod.POST, // Request Method
			TokenRequest,	// RequestBody
			String.class);	// return Object
		
        // HTTP POST 요청에 대한 응답 확인
        System.out.println("status : " + response.getStatusCode());
        System.out.println("body : " + response.getBody());
		
		return "login";
	}
	
	@GetMapping("/apicall")
	public String restTemplateAPIv2(Model model) throws Exception {
		
		//String url = "http://apis.data.go.kr/1360000/AirInfoService/getAirInfo?pageNo=1&numOfRows=10&fctm=202306300000&icaoCode=RKSI";
		String url = "https://apis.data.go.kr/1360000/AirInfoService/getAirInfo?serviceKey=kry52Qun7PJGODw51SGulaC5UitRsf1%2Bhts8gSWXpb7zYRfruRDZIB%2F5cXiWZk0oGSClTajuFU9bOul9kuYP5g%3D%3D&pageNo=1&numOfRows=10&fctm=202306290000&icaoCode=RKSI";
		
		ObjectMapper mapper = new ObjectMapper();
		HashMap<String, Object> map = new HashMap<String, Object>();	
		String jsonStr = "";
		
		// 인증키 같은 파라미터 넘길때
		//map.put("serviceKey", "kry52Qun7PJGODw51SGulaC5UitRsf1%2Bhts8gSWXpb7zYRfruRDZIB%2F5cXiWZk0oGSClTajuFU9bOul9kuYP5g%3D%3D");
		//jsonStr = mapper.writeValueAsString(map);
		//StringEntity param = new StringEntity(jsonStr);
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("content-type", "application/json; charset=utf-8");
		
		// post 요청시
		//HttpPost httpPost = new HttpPost(url);
		//response.addHeader("Content-Type", "application/json; charset=utf-8"); // 응답헤더 설정
		//response.addHeader("Authorization", "Bearer " + token);
		//headers.add("Content-Type", "application/json; charset=utf-8");
		//httpPost.setHeader("content-type", "application/json; charset=utf-8");
		//httpPost.setEntity(param);
		
		CloseableHttpResponse response = httpClient.execute(httpGet);
		String resXml = EntityUtils.toString(response.getEntity(), "UTF-8");
		
		//System.out.println(response.getStatusLine());
		//System.out.println(resJson);
		
		JSONObject jObject = XML.toJSONObject(resXml);
		
		
		
		/*
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(resJson);
		NodeList nodes = doc.getElementsByTagName("items");
		
        for (int k = 0; k < nodes.getLength(); k++) {
            Node node = nodes.item(k);
            
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                
                System.out.println(element.getTextContent().indexOf(k));           
                System.out.println("Stock Symbol: " + getValue("symbol", element));
                System.out.println("Stock Price: " + getValue("price", element));
                System.out.println("Stock Quantity: " + getValue("quantity", element));
            }
        }*/
		
		// json 형태가 아니라 jsonObject로 변경이 어려울듯함
		//JSONParser jsonParser = new JSONParser();
		//JSONArray jsonArray = new JSONArray();
		//HashMap<String, Object> map = new HashMap<String, Object>();
		//JSONObject jsonObject = (JSONObject) jsonParser.parse(resJson);
		//jsonArray.add(jsonObject);
		
		//System.out.println(jsonArray.get(0).toString());
		
		return "login";
	}
}
