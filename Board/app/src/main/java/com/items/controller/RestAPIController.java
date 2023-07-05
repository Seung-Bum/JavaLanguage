package com.items.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

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
			// ** restTemplate api ȣ���ؼ� ���� �ޱ� **
			// ���� �ȵǼ� �ּ��� -> Ű���� ���� �߻���
            //HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            //factory.setConnectTimeout(5000); //Ÿ�Ӿƿ� ���� 5��
            //factory.setReadTimeout(5000);//Ÿ�Ӿƿ� ���� 5��
            //RestTemplate restTemplate = new RestTemplate(factory);
            
            //HttpHeaders headers = new HttpHeaders();
            //headers.setContentType(MediaType.APPLICATION_JSON);
            //headers.add("Content-Type", "application/json"); 
            //headers.add("Accept-Charset", "UTF-8"); 
            //HttpEntity<?> entity = new HttpEntity<>(headers);
            
			//�� ������ �ڵ�� API�� ȣ���� MAPŸ������ ���� �޴´�.
            //ResponseEntity<String> resultMap = restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
			
            // HTTP POST ��û�� ���� ���� Ȯ��
            //System.out.println("status : " + resultMap.getStatusCode());
            //System.out.println("body : " + resultMap.getBody());
                             
    		StringBuffer output = new StringBuffer();
    		Process p = Runtime.getRuntime().exec(SERVICE_CALL); // curl ������
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = "";
			
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
				//System.out.println(line.toString());
			}
			//p.waitFor();
			String outString = output.toString();
            
			System.out.println(outString);
			
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
	        
	        // NodeList�� List ���� �̱⿡ Node�� ��ȯ �Ͽ� ����
			//Node tagtext = taglist.item(1).getChildNodes().item(1); // items
	        //String tagtext = taglist.item(0).getChildNodes().item(0).getFirstChild().getNodeValue(); // items
	        NodeList response = (NodeList) taglist.item(0); // response
	        NodeList body = (NodeList) response.item(1); // body
	        NodeList items = (NodeList) body.item(1); // items
	        
	        for (int i=0; i < items.getLength(); i++) {
	        	NodeList item = (NodeList) items.item(i);
	        	
	        	for (int j=0; j < item.getLength(); j++) {
	        		Node node = item.item(j);
	        		String nodeName = node.getNodeName();
	        		String nodeString = node.toString();
	        		String nodeLocal = node.getLocalName();
	        		System.out.println("nodeString" + " " + nodeString);
	        		System.out.println("nodeLocal" + " " + nodeLocal);
	        	}
	        }	       
	        
	        
			// tagtext�� �ִ� ���� Node�� ���� �Ǿ� �־ getNodeValue()�� String���� ��ȯ �Ͽ� ����
	        //String Tag =tagtext.getNodeValue();
	        //System.out.println("Tag : " + Tag.toString());
	        

			
		
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
