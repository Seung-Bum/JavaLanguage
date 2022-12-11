package com.NetworkInterface;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

/*
  ESB���� �����Ͽ� �����IP�� Ȯ���Ҷ� �ʿ�
 
  NetworkInterface Ŭ���� Ư¡
  ��Ʈ��ũ ����̽��� ������ ���� �� �ִ�.

 - �ý��� �� �����ϴ� ��� ��Ʈ��ũ ����̽��� ����Ʈ ȹ��

 - Ư�� ��Ʈ��ũ ����̽� ã��

 - ��Ʈ��ũ ����̽� �� ������ IP�ּҷκ��� InetAddress ��ü ���

 - ��Ʈ��ũ ����̽� �� ������ IP�ּҷκ��� InterfaceAddress ��ü�� ������ ����Ʈ�� ��´�.
 
 https://choidev-1.tistory.com/m/64  
 https://blog.silentsoft.org/archives/27
 */

public class NetworkParameter {
    public static void main(String args[]) throws Exception{
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();

        while(en.hasMoreElements()){
            NetworkInterface ni = en.nextElement();
            printParameter(ni);
        }
        
        System.out.println("-----------------");
        System.out.println( getLocalAddress() ); // /172.30.1.70
    }
    
    // ���ð��� ���
    public static void printParameter(NetworkInterface ni) throws SocketException{
        System.out.println("Name = " +ni.getName()); // lo
        System.out.println("Display Name = " + ni.getDisplayName()); // Software Loopback Interface 1
        System.out.println("Is up = " + ni.isUp()); // true
        System.out.println("Support multicast =" + ni.supportsMulticast()); // true
        System.out.println("Is loopback" + ni.isLoopback()); // true
        System.out.println("Is virtual = " + ni.isVirtual()); // false
        System.out.println("is point to point = " + ni.isPointToPoint()); // false
        System.out.println("Hardware address = " + ni.getHardwareAddress()); // null
        System.out.println("MTU" + ni.getMTU()); // MTU-1
        System.out.println("\nList of Interface Address:");
        
        List<InterfaceAddress> list = ni.getInterfaceAddresses();
        Iterator<InterfaceAddress> it = list.iterator();

        while(it.hasNext()){
            InterfaceAddress ia = it.next();
            System.out.println("Address = " + ia.getAddress()); // /127.0.0.1
            System.out.println("Broadcast = " + ia.getBroadcast()); // /127.255.255.255
            System.out.println("Network prefix length = " + ia.getNetworkPrefixLength()); // 8
            System.out.println("");
        }
    }
    
    public static String getHostAddress() {
        InetAddress localAddress = getLocalAddress();
        if (localAddress == null) {
            try {
                return Inet4Address.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                ;
            }
        } else {
            return localAddress.getHostAddress();
        }
        return "";
    }
    
    private static InetAddress getLocalAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces(); // �������̽� ����
            while (networkInterfaces.hasMoreElements()) {
                List<InterfaceAddress> interfaceAddresses = networkInterfaces.nextElement().getInterfaceAddresses(); // ��巹�� ����
                for (InterfaceAddress interfaceAddress : interfaceAddresses) {
                    InetAddress address = interfaceAddress.getAddress();
                    if (address.isSiteLocalAddress()) {
                        return address;
                    }
                }
            }
        } catch (Exception e) {
            ;
        }
        return null;
    }
    

    
}