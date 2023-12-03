package IO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {

	public static void main(String[] args) throws IOException {
		
		try {
			// 크롤링
			URL url = new URL("https://www.starbucks.co.kr/index.do"); // 주소
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // 주소찾고 연결
			InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream()); // 노드 생성 (빨대 꽂기)
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8); // 필터 생성(호스 연결)
			
			StringBuilder sb = new StringBuilder();
			String line = "";
			while ((line = reader.readLine()) != null) { sb.append(line + "\n"); }

			String jsonString = sb.toString();
			System.out.println(jsonString);		
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
