package com.sharedstorage.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PCinfo {

	public PCinfo(){
		
	}
	
	public static String getLocalIP(){
		String hostname="";
		try {
			InetAddress addr = InetAddress.getLocalHost();
//			byte[] ipAddr = addr.getAddress();
			hostname = addr.getHostAddress();
//			System.out.println("hostname=" + hostname);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
//		if(hostname.equals("")){
			//
//		}
		
		return hostname;
	}
	
	public static String getHostName(){
		String hostname="";
		try {
			InetAddress addr = InetAddress.getLocalHost();
//			byte[] ipAddr = addr.getAddress();
			hostname = addr.getHostName();
			System.out.println("hostname=" + hostname);
		} catch (UnknownHostException e) {
		e.printStackTrace();
        }
		
//		if(hostname.equals("")){
			//
//		}
		
		return hostname;
	}
	
}
