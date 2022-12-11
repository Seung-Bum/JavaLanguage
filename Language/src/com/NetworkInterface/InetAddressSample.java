package com.NetworkInterface;
import java.net.InetAddress;

public class InetAddressSample {

	public static void main(String[] args) {

		try {
			// InetAddress Ʋ������ �ν��Ͻ� �� ����
			InetAddress myIP = InetAddress.getLocalHost();
			
			// getHostAddress() ������� PC�� IP�ּҸ� ���´�.
			String strIPAddress = myIP.getHostAddress();
			System.out.println("MY PC IPADDRESS : " + strIPAddress);
			
			// getHostName() ������� PC�� �̸��� ���´�.
			//(�̸��� ��Ʈ��ũ������� ���Ǵ� �̸��̴�.)
			String strName = myIP.getHostName();
			System.out.println("myIP.getHostName() : " + strName);

			//getAllByName DNS�� ���� ������ ����Ʈ�� IP �ּҸ� ������ �´�.
			InetAddress[] IPAList = InetAddress.getAllByName("www.google.com");
			for(InetAddress i : IPAList) {
				System.out.println("www.google.com IPADDRESS : " + i.getHostAddress());
			}
		
		} catch (Exception e) {
			System.out.println("���� �߻� : "+e.getLocalizedMessage());
		}

		
	}
}
//����� :

//MY PC IPADDRESS : 192.168.219.104
//myIP.getHostName() : DESKTOP-25N6037
//www.google.com IPADDRESS : 216.58.199.4