/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hanxi
 */
import java.net.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class UDPServer{
    public static void main(String[] args){ 
        
        Map<String, String> cityLocationPairs = new HashMap();
        cityLocationPairs.put("Pittsburgh,PA", "40.44,-79.99");
        cityLocationPairs.put("Atlantic,NJ", "39.45,74.57");
        cityLocationPairs.put("Bloomington,IL", "40.48,88.93");
        cityLocationPairs.put("Charlottesvi,VA", "38.13,78.45");
        cityLocationPairs.put("Seattle,WA", "47.45,122.30");
//        String city = "Charlottesvi,VA";
//        System.out.println(cityLocationPairs.containsKey(city));
//        System.out.println("test: "+cityLocationPairs.get(city));
        
    	DatagramSocket aSocket = null;
		try{
                        aSocket = new DatagramSocket(6789);
					// create socket at agreed port
			byte[] receiveData = new byte[1000];
                        byte[] sendData = new byte[1000];
 			while(true){
 				DatagramPacket request = new DatagramPacket(receiveData, receiveData.length);
  				aSocket.receive(request);     
    			        // Get the request
                                String cityName = new String(request.getData(),0,request.getLength());
                                System.out.println("RECEIVE: "+ cityName);
                                
                                if(!cityLocationPairs.containsKey(cityName)){
                                    String replySentence = "Could not resolve '"+cityName +"'";
                                    System.out.println("Was unable to handle a request for "+cityName);
                                    sendData = replySentence.getBytes();
                                }else{
                                    String replySentence = cityName + "\n" + cityLocationPairs.get(cityName);
                                    System.out.println("Handling request for "+cityName +" "+ cityLocationPairs.get(cityName));
                                    sendData = replySentence.getBytes();
                                }
                                
                                DatagramPacket reply = new DatagramPacket(sendData, sendData.length, 
                                        request.getAddress(), request.getPort());
    			        
                                aSocket.send(reply);
    		        }
                        
		}catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e) {System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
    }
}