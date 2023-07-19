package com.items.controller;

//import org.json.simple.JSONArray;
//import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
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

import com.items.service.RestAPIService;

@Controller
public class RestAPIController {

	@Value("${restapi.Service.call}")
	private String SERVICE_CALL;
	
	@Value("${restapi.token}")
	private String TOKEN;

	@Autowired
	RestAPIService restAPIService;
	
   /**
	 * ���� ���� �̷����� API ȣ��, Domestic airport take-off forecast
	 * api call�� curl�� ���� ���� �� ����� �޴´�.
	 * @param model
	 * @return String
	 * @ author yang
	 * @ version 1.0
	 */
	@GetMapping("/takeOffForecast/runTimeCall") 
	public String TakeOffForecastRunTimeCall(Model model) {

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
			// p.waitFor();
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
			for (int i = 0; i < items.getLength(); i++) {
				NodeList item = (NodeList) items.item(i);

				for (int j = 0; j < item.getLength(); j++) {
					Node node = item.item(j);
					nodeName = node.getNodeName();
					nodeValue = node.getTextContent();
					 //log.info(node.getNodeName() + " " + node.getTextContent());
					 //log.info(nodeName + " " + nodeValue);

					if ((nodeName != null || nodeName != "") && (nodeValue != null || nodeValue != "")) {
						map.put(nodeName, nodeValue);
						// log.info(nodeName + " " + nodeValue);
					} else {
						map.put(nodeName, "null");
						//restAPIService.insertAirInfo(map);
						// log.info(nodeName + " " + "null");
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
		return "successPage";
	}
	
   /**
	 * restTemplate ����, �����߻�
	 * @param model
	 * @return String
	 * @ author yang
	 * @ version 1.0
	 */
	@GetMapping("/restTemplateAPI")
	public String restTemplateAPI(Model model) throws Exception {

		String url = "http://apis.data.go.kr/1360000/AirInfoService/getAirInfo?pageNo=1&numOfRows=10&fctm=202306300000&icaoCode=RKSI";
		// String url =
		// "http://apis.data.go.kr/1360000/AirInfoService/getAirInfo?serviceKey=kry52Qun7PJGODw51SGulaC5UitRsf1%2Bhts8gSWXpb7zYRfruRDZIB%2F5cXiWZk0oGSClTajuFU9bOul9kuYP5g%3D%3D&pageNo=1&numOfRows=10&fctm=202306290000&icaoCode=RKSI";
		final Logger log = LogManager.getLogger(RestAPIController.class);
		RestTemplate rt = new RestTemplate();

		// �ش� �����
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		// headers.add("Content-type", "application/x-www-form-urlencoded;
		// charset=utf-8");
		// headers.add("Content-type", "text/plain; charset=utf-8");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("serviceKey",
				"kry52Qun7PJGODw51SGulaC5UitRsf1%2Bhts8gSWXpb7zYRfruRDZIB%2F5cXiWZk0oGSClTajuFU9bOul9kuYP5g%3D%3D");

		// �ش��� �ٵ� �ϳ��� ������Ʈ�� �����
		HttpEntity<MultiValueMap<String, String>> TokenRequest = new HttpEntity<>(params, headers);

		// Http ��û�ϰ� ���ϰ��� response ������ �ޱ�
		ResponseEntity<String> response = rt.exchange(url, // Host
				HttpMethod.POST, // Request Method
				TokenRequest, // RequestBody
				String.class); // return Object

		// HTTP POST ��û�� ���� ���� Ȯ��
		System.out.println("status : " + response.getStatusCode());
		System.out.println("body : " + response.getBody());

		return "successPage";
	}

   /**
	 * ���� ���� �̷�����, Domestic airport take-off forecast
	 * restAPI, XMLtoJson
	 * @param model
	 * @return String
	 * @ author yang
	 * @ version 1.0
	 */
	@GetMapping("/TakeOffForecast")
	public String TakeOffForecast(Model model) throws Exception {

		String url = "https://apis.data.go.kr/1360000/AirInfoService/getAirInfo?serviceKey=" + TOKEN + "&pageNo=1&numOfRows=10&fctm=202306290000&icaoCode=RKSI";
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("content-type", "application/json; charset=utf-8");

		CloseableHttpResponse response = httpClient.execute(httpGet);
		String resXml = EntityUtils.toString(response.getEntity(), "UTF-8");
		//System.out.println(resXml);

		// xml ���¸� json���� �����ؼ� �ٷ�°��� ���ϴٰ��� (json simple �ƴ�)
		JSONObject jsonObject = XML.toJSONObject(resXml);
		JSONObject responseJson = (JSONObject) jsonObject.get("response");
		JSONObject body = (JSONObject) responseJson.get("body");
		JSONObject items = (JSONObject) body.get("items");
		JSONArray item = (JSONArray) items.get("item");

		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < item.length(); i++) {
			JSONObject jsonItem = (JSONObject) item.get(i);
			//System.out.println(jsonItem.toString());	
			
			map.put("tmFc", jsonItem.get("tmFc"));
			
			System.out.println(jsonItem.get("airportName"));
			System.out.println(jsonItem.get("icaoCode"));
			System.out.println(jsonItem.get("qnh"));
			System.out.println(jsonItem.get("ws"));
			System.out.println(jsonItem.get("wd"));
			System.out.println(jsonItem.get("ta"));
			
		}
		return "successPage";
	}
	
   /**
	 * �װ��������, Aviation weather information
	 * restAPI, XMLtoJson
	 * @param model
	 * @return String
	 * @ author yang
	 * @ version 1.0
	 */
	@GetMapping("/xmlToJsonParse")
	public String xmlToJsonParse(Model model) throws Exception {
		
		// ICAO �����ڵ带 ����� ���� ������ ��ȿ�� METAR/SPECI ������ ��ȸ
		//String url = "http://amoapi.kma.go.kr/amoApi/metar?icao=RKSI";
		
		// ICAO �����ڵ带 ����� ���� ������ ��ȿ�� TAF ������ ��ȸ
		// 6~30 �ð��� ��ȿ �ð��� ���� ������ 1�� 4ȸ �����Ѵ�.
		String url_TAF = "http://amoapi.kma.go.kr/amoApi/taf?icao=RKSI";
		
		// ���� ��ȿ���� ���װ溸 ������ ����Ѵ�.
		String url_wrng = "http://amoapi.kma.go.kr/amoApi/wrng";
		
		String resXml = httpGetApiCall(url_TAF);		
		//System.out.println(resXml);

		// xml ���¸� json���� ����
		JSONObject jsonObject = XML.toJSONObject(resXml);
		JSONObject responseJson = (JSONObject) jsonObject.get("response");
		JSONObject body = (JSONObject) responseJson.get("body");
		JSONObject items = (JSONObject) body.get("items");
		JSONObject item = (JSONObject) items.get("item");

		HashMap<String, Object> map = new HashMap<String, Object>();

		System.out.println("icaoCode" + item.get("icaoCode"));
		System.out.println("airportName" + item.get("airportName"));
		System.out.println("tafMsg" + item.get("tafMsg"));
		map.put("icaoCode", item.get("icaoCode"));		
		map.put("airportName", item.get("airportName"));
		map.put("tafMsg", item.get("tafMsg"));
		
		model.addAttribute("resMap", map); 
		return "content2";
	}
	
   /**
	 * httpGetApiCall
	 * @param String url
	 * @return String Xml
	 * @ author yang
	 * @ version 1.0
	 */
	public String httpGetApiCall(String url) throws Exception {		
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("content-type", "application/json; charset=utf-8");

		CloseableHttpResponse response = httpClient.execute(httpGet);
		String resXml = EntityUtils.toString(response.getEntity(), "UTF-8");
		
		return resXml;
	}
}
