package com.NetworkInterface;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class MultiOsAddress {

	public static void main(String[] args) {
		try {
			getHostAddress();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public static String getHostAddress() throws SocketException {
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces(); // 인터페이스 정보
		while(networkInterfaces.hasMoreElements()) {
			NetworkInterface networkInterface = networkInterfaces.nextElement();
			String name = networkInterface.getName();
			String displayName = networkInterface.getDisplayName();			
			boolean isLoopback = networkInterface.isLoopback(); // 127.0.0.1
			boolean isVirtual = networkInterface.isVirtual();
			if(!isLoopback) {
				Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses(); // 어드레스 정보
				while(inetAddresses.hasMoreElements()) {
					InetAddress inetAddress = inetAddresses.nextElement();
					if(!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && !inetAddress.isSiteLocalAddress()) {
						String hostName = inetAddress.getHostName();
						String hostAddress = inetAddress.getHostAddress();

						System.out.println("==============Host Information===============");
						System.out.println("NetworkInterface Name: " + name);
						System.out.println("NetworkInterface Display Name: " + displayName);
						System.out.println("NetworkInterface Loopback: " + isLoopback);
						System.out.println("NetworkInterface Virtual: " + isVirtual);
						System.out.println("Host Name: " + hostName);
						System.out.println("Host Address: " + hostAddress);
						System.out.println("=============================================");

						return hostAddress;
					}					
				}
			}
		}
		return null;
	}
}
