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
public class UDPClient{
    public static void main(String[] args){ 
		// args give message contents(args[0]) and destination hostname (args[1])
		DatagramSocket aSocket = null;
                byte[] sendData = new byte[1000];
                byte[] receiveData = new byte[1000];
		
                try {
                        System.out.println("java UDPClient");
                        System.out.println("Enter city name and we will find its coordinates.");
			aSocket = new DatagramSocket();
			InetAddress aHost = InetAddress.getByName(args[1]);
			int serverPort = 6789;		                                                 
			String city = args[0];
                        //System.out.println(city);
                        sendData = city.getBytes();
                        
                        DatagramPacket request =
			 	new DatagramPacket(sendData, sendData.length, aHost, serverPort);
			
                        aSocket.send(request);	
			
                        DatagramPacket reply = new DatagramPacket(receiveData, receiveData.length);	
			aSocket.receive(reply);
                        System.out.println(new String(reply.getData()));	
		
                }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
		}catch (IOException e){System.out.println("IO: " + e.getMessage());
		}finally {if(aSocket != null) aSocket.close();}
	}		      	
}