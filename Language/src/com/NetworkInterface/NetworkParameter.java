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
  ESB연동 관련하여 사용자IP를 확인할때 필요
 
  NetworkInterface 클래스 특징
  네트워크 디바이스의 정보를 얻을 수 있다.

 - 시스템 내 존재하는 모든 네트워크 디바이스의 리스트 획득

 - 특정 네트워크 디바이스 찾기

 - 네트워크 디바이스 내 설정된 IP주소로부터 InetAddress 객체 얻기

 - 네트워크 디바이스 내 설정된 IP주소로부터 InterfaceAddress 객체로 구성된 리스트를 얻는다.
 
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
    
    // 예시값을 적어봄
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
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces(); // 인터페이스 정보
            while (networkInterfaces.hasMoreElements()) {
                List<InterfaceAddress> interfaceAddresses = networkInterfaces.nextElement().getInterfaceAddresses(); // 어드레스 정보
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