package com.NetworkInterface;

import java.net.InetAddress;
import java.net.UnknownHostException;


// https://bcuts.tistory.com/m/41
// 자바에서 IP주소 얻기 Window
public class WindowAddress {

	public static void main(String[] args) {
		try {
			getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static String getHostAddress() throws UnknownHostException {		
		InetAddress inetAddress = InetAddress.getLocalHost();
		
		String hostName =  inetAddress.getHostName();
		String hostAddress = inetAddress.getHostAddress();		
		
		System.out.println("==============Host Information===============");
		System.out.println("hostName: " + hostName);
		System.out.println("hostAddress: " + hostAddress);
		System.out.println("=============================================");

		return hostAddress;
	}
}
