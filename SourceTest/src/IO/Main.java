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
			// ũ�Ѹ�
			URL url = new URL("https://www.starbucks.co.kr/index.do"); // �ּ�
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); // �ּ�ã�� ����
			InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream()); // ��� ���� (���� �ȱ�)
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 8); // ���� ����(ȣ�� ����)
			
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
