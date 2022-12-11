package com.NetworkInterface;
import java.net.InetAddress;

public class InetAddressSample {

	public static void main(String[] args) {

		try {
			// InetAddress 틀래스의 인스턴스 를 생성
			InetAddress myIP = InetAddress.getLocalHost();
			
			// getHostAddress() 사용중인 PC의 IP주소를 얻어온다.
			String strIPAddress = myIP.getHostAddress();
			System.out.println("MY PC IPADDRESS : " + strIPAddress);
			
			// getHostName() 사용중인 PC의 이름을 얻어온다.
			//(이름은 네트워크공유등에서 사용되는 이름이다.)
			String strName = myIP.getHostName();
			System.out.println("myIP.getHostName() : " + strName);

			//getAllByName DNS를 통한 원격지 사이트의 IP 주소를 가지고 온다.
			InetAddress[] IPAList = InetAddress.getAllByName("www.google.com");
			for(InetAddress i : IPAList) {
				System.out.println("www.google.com IPADDRESS : " + i.getHostAddress());
			}
		
		} catch (Exception e) {
			System.out.println("예외 발생 : "+e.getLocalizedMessage());
		}

		
	}
}
//결과는 :

//MY PC IPADDRESS : 192.168.219.104
//myIP.getHostName() : DESKTOP-25N6037
//www.google.com IPADDRESS : 216.58.199.4