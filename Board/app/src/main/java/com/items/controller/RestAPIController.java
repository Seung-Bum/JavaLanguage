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
import com.items.domain.Airport;
import com.items.service.RestAPIService;

@Controller
public class RestAPIController {

	@Value("${restapi.Service.call}")
	private String SERVICE_CALL;

	@Value("${restapi.token}")
	private String TOKEN;

	@Autowired
	RestAPIService restAPIService;
	
	static final Logger log = LogManager.getLogger(RestAPIController.class);
	
	Airport airport;

	/**
	 * ���� ���� �̷����� API ȣ��, Domestic airport take-off forecast api call�� curl�� ���� ���� ��
	 * ����� �޴´�.
	 * 
	 * @param model
	 * @return String @ author yang @ version 1.0
	 */
	@GetMapping("/takeOffForecast/runTimeCall")
	public String TakeOffForecastRunTimeCall(Model model) {

		
		//HashMap<String, Object> result = new HashMap<String, Object>();
		//String jsonInString = "";

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
					// log.info(node.getNodeName() + " " + node.getTextContent());
					// log.info(nodeName + " " + nodeValue);

					if ((nodeName != null || nodeName != "") && (nodeValue != null || nodeValue != "")) {
						map.put(nodeName, nodeValue);
						// log.info(nodeName + " " + nodeValue);
					} else {
						map.put(nodeName, "null");
						// restAPIService.insertAirInfo(map);
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
	 * 
	 * @param model
	 * @return String @ author yang @ version 1.0
	 */
	@GetMapping("/restTemplateAPI")
	public String restTemplateAPI(Model model) throws Exception {

		String url = "http://apis.data.go.kr/1360000/AirInfoService/getAirInfo?pageNo=1&numOfRows=10&fctm=202306300000&icaoCode=RKSI";
		// String url =
		// "http://apis.data.go.kr/1360000/AirInfoService/getAirInfo?serviceKey=kry52Qun7PJGODw51SGulaC5UitRsf1%2Bhts8gSWXpb7zYRfruRDZIB%2F5cXiWZk0oGSClTajuFU9bOul9kuYP5g%3D%3D&pageNo=1&numOfRows=10&fctm=202306290000&icaoCode=RKSI";
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
	 * ���� ���� �̷�����, Domestic airport take-off forecast restAPI, XMLtoJson
	 * 
	 * @param model
	 * @return String @ author yang @ version 1.0
	 */
	@GetMapping("/TakeOffForecast")
	public String TakeOffForecast(Model model) throws Exception {

		String url = "https://apis.data.go.kr/1360000/AirInfoService/getAirInfo?serviceKey=" + TOKEN
				+ "&pageNo=1&numOfRows=10&fctm=202306290000&icaoCode=RKSI";
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("content-type", "application/json; charset=utf-8");

		CloseableHttpResponse response = httpClient.execute(httpGet);
		String resXml = EntityUtils.toString(response.getEntity(), "UTF-8");
		// System.out.println(resXml);

		// xml ���¸� json���� �����ؼ� �ٷ�°��� ���ϴٰ��� (json simple �ƴ�)
		JSONObject jsonObject = XML.toJSONObject(resXml);
		JSONObject responseJson = (JSONObject) jsonObject.get("response");
		JSONObject body = (JSONObject) responseJson.get("body");
		JSONObject items = (JSONObject) body.get("items");
		JSONArray item = (JSONArray) items.get("item");

		HashMap<String, Object> map = new HashMap<String, Object>();
		for (int i = 0; i < item.length(); i++) {
			JSONObject jsonItem = (JSONObject) item.get(i);
			// System.out.println(jsonItem.toString());

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
	 * �װ��������, Aviation weather information restAPI, XMLtoJson
	 * 
	 * @param model
	 * @return String @ author yang @ version 1.0
	 */
	@GetMapping("/aviationWeatherAPI")
	public String aviationWeatherAPI(Model model) throws Exception {

		// * ICAO �����ڵ带 ����� ���� ������ ��ȿ�� METAR/SPECI ������ ��ȸ
		// String url = "http://amoapi.kma.go.kr/amoApi/metar?icao=RKSI";
		
		// * ���� ��ȿ���� ���װ溸 ������ ����Ѵ�.
		//String url_wrng = "http://amoapi.kma.go.kr/amoApi/wrng";
		
		// * ICAO �����ڵ带 ����� ���� ������ ��ȿ�� TAF ������ ��ȸ
		//   (6~30 �ð��� ��ȿ �ð��� ���� ������ 1�� 4ȸ �����Ѵ�.)
		String url_TAF = "http://amoapi.kma.go.kr/amoApi/taf?icao=RKSI";
		
		String resXml ="";
		try {
			// API RESULT
			resXml = httpGetApiCall(url_TAF);
			log.info("API CALL RESULT : " + resXml);
		}catch(Exception e) {
			log.error("API CALL ERROR : " + e.getMessage());
		}

		// xml ���¸� json���� ����
		JSONObject jsonObject = XML.toJSONObject(resXml);
		JSONObject responseJson = (JSONObject) jsonObject.get("response");
		JSONObject body = (JSONObject) responseJson.get("body");
		JSONObject items = (JSONObject) body.get("items");
		JSONObject item = (JSONObject) items.get("item");
		
		// TAF ����
		String tafMsg = (String) item.get("tafMsg");
		
		// TAF Line
		String[] tafMsg_str = tafMsg.split("\n");
		
		// ���� �ʱ�ȭ
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		//HashMap<String, Object> map1 = new HashMap<String, Object>();
		//HashMap<String, Object> map2 = new HashMap<String, Object>();
		//HashMap<String, Object> map3 = new HashMap<String, Object>();
		
		String[] line0;
		String[] line1;
		String[] line2;
		String[] line3;

		// TAF Line Loop
		log.info("TAF Line Loop Start");
		for (int i = 0; i < tafMsg_str.length; i++) {
			
			// Line0
			if (i == 0) {
				log.info("Line0 Start");
				line0 = tafMsg_str[i].trim().split(" ");
				HashMap<String, Object> result0 = aviationWeatherInfo(line0, map);				
				
				// ���� ��ȿ�ð� - 30�ð� ��ȿ
				if(line0[3].toString().indexOf("/") != -1) {
					String[] available_day = line0[3].toString().split("/");
					map.put("availableDay", available_day[0].substring(0, 2) + "�� " + available_day[0].substring(2, 4) + "�� ~ " + 
							available_day[1].substring(0, 2) + "�� " + available_day[1].substring(2, 4) + "��"); }
				else { result0.put("availableDay", " "); }
				
				model.addAttribute("resMap0", result0);				
				//log.info("line0 : result0 - " + result0.toString());
			}
			
			// line1
			if (i == 1) { 
				log.info("Line1 Start");
				line1 = tafMsg_str[i].trim().split(" ");
				HashMap<String, Object> result1 = aviationWeatherInfo(line1, map);
				model.addAttribute("resMap1", result1);
				//log.info("line1 : result1 - " + result1.toString());
			} 
			
			// line2
			if (i == 2) {
				log.info("Line2 Start");
				line2 = tafMsg_str[i].trim().split(" ");
				HashMap<String, Object> result2 = aviationWeatherInfo(line2, map);
				model.addAttribute("resMap2", result2);
				//log.info("line2 : result2 - " + result2.toString());
			}
			
			// line3
//			if (i == 3) {
//				log.info("Line3 Start");
//				line3 = tafMsg_str[i].trim().split(" ");
//				model.addAttribute("resMap3", aviationWeatherInfo(line3, map3));
//			}			
		}
		log.info("TAF Line Loop End");
		return "aviationWeather";
	}

	/**
	 * httpGetApiCall
	 * 
	 * @param String url
	 * @return String Xml @ author yang @ version 1.0
	 */
	public String httpGetApiCall(String url) throws Exception {

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("content-type", "application/json; charset=utf-8");

		CloseableHttpResponse response = httpClient.execute(httpGet);
		String resXml = EntityUtils.toString(response.getEntity(), "UTF-8");

		return resXml;
	}
	
	/**
	 * isNumeric
	 * ���ڸ� �ִ��� Ȯ��
	 * @param String url
	 * @return String Xml @ author yang @ version 1.0
	 */
    public static boolean isNumeric(String s)
    {
        try {
        	Integer.parseInt(s);
        } catch (NumberFormatException ex) {
        	return false;
        }
    	return true;
    }

	/**
	 * aviationWeatherInfo
	 * ForeCastDay, ������, ǳ��, ���ðŸ�, �� �ణ, �ڹ�, ������ȣ, NSC
	 * @param int index, String taf, String[] tafMsg_str_array, HashMap<String, Object> map
	 * @return HashMap<String, Object>
	 */
    public HashMap<String, Object> aviationWeatherInfo(String[] strArray, HashMap<String, Object> map) {
    	
    	for (int i=0; i<strArray.length; i++) {
    		
	    	// Array�� str �� �ܾ parse
			String taf = strArray[i].toString();
			log.info("taf: " + taf );
			
			
//			// �������
//			if(taf.indexOf("TN") == -1) { map.put("minimumTemper", " "); }
//			else { map.put("minimumTemper", taf.substring(2, 4) + "��C"); } 
//			
//			// �ְ���
//			if(taf.indexOf("TX") == -1) { map.put("highestTemper", " "); }
//			else { map.put("highestTemper", taf.substring(2, 4) + "��C"); } 
//			
//			// airport
//			if(taf.indexOf("RKSI") == -1) { map.put("RKSI", " "); }
//			else { map.put("airport", "��õ����"); } 
//			
//			// ���ʿ���
//			if(taf.indexOf("Z") == -1 && taf.indexOf("/") != -1) { map.put("createTime", " "); }
//			else {
//				String createDay = taf.substring(0,2); // day
//				String createTime = taf.substring(2,4); // time
//				String createMinute = taf.substring(4,6); // minute
//				map.put("createTime", createDay + "�� " + createTime + ":" + createMinute); 
//			}
//			
//			// ForeCastDay
//			if(taf.indexOf("/") == -1) { map.put("forecastDay", " "); }
//			else {  
//				String forecastDay = taf.substring(0, 2);
//				String forecastTime = taf.substring(2, 4);
//				String forecastUntilDay = taf.substring(5, 7);
//				String forecastUntilTime = taf.substring(7, 9);
//				map.put("forecastDay", forecastDay + "�� " + forecastTime + "�� ~ " + forecastUntilDay + "�� " + forecastUntilTime + "��");			
//			}
//			
//			// ������, ǳ��
//			if(taf.indexOf("KT") == -1) { 
//				map.put("azimuth", " "); 
//				map.put("Knots", " "); 
//			}
//			else {  
//				// ������
//				map.put("azimuth", taf.substring(0, 3)); 
//				// Knots
//				String Knots = taf.substring(3, 5);
//				map.put("Knots", Knots);
//			}
//			
//			// ���ðŸ� - ���ڸ� ���� ��� ���ðŸ��� �Ǵ�
//			if (isNumeric(taf)) { map.put("sight", taf); }
//			else { map.put("sight", " "); }
//			
//			// �� �ణ
//			if(taf.indexOf("-RA") == -1) { map.put("littleRA", " "); }
//			else { map.put("littleRA", "�� �ణ"); }
//			
//			// �� ����
//			if(taf.indexOf("RA") == -1) { map.put("RA", " "); }
//			else { map.put("RA", "�� ����"); }
//
//			// �ڹ� : �Ȱ����� ������ ���� ����
//			if(taf.indexOf("BR") == -1) { map.put("BR", " "); }
//			else { map.put("lightRai", "�Ȱ����� ������ ���� ����"); }
//
//			// ������ȣ : ������ ���쵵 ���� ��Ÿ Ư���� �ϱ��Ȳ�� ���� ��
//			if(taf.indexOf("CAVOK") == -1) { map.put("CAVOK", " "); }
//			else { map.put("CAVOK", "*������ ���쵵 ���� ��Ÿ Ư���� �ϱ� ��Ȳ�� ����");}
//
//			// ��� �߿��� ���� ����, ���������� ���� ����
//			if(taf.indexOf("NSC") == -1) {  map.put("NSC", " "); }
//			else { map.put("NSC", "��� �߿��� ���� ����, ���������� ���� ����"); }
//			
//			// ��, ����
//			if(taf.indexOf("FEW") == -1) { map.put("FEW", " "); }
//			else { map.put("FEW", taf.substring(4, 6) + "00ft" + " ���� ����"); }
//			
//			if(taf.indexOf("SCT") == -1) { map.put("SCT", " "); }
//			else { map.put("SCT", taf.substring(4, 6) + "00ft" + " ���� ����"); }
    	}
    	
    	for( String key : map.keySet() ){ if(map.get(key).equals(" ")) { map.put(key, "none"); } }	
		return map;
    }

}
