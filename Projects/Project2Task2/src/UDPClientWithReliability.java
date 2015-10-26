
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hanxi
 */
public class UDPClientWithReliability {

    public static void main(String[] args) throws UnknownHostException, IOException {
        // args give message contents(args[0]) and destination hostname (args[1])

        System.out.println("java UDPClient");
        System.out.println("Enter city name and we will find its coordinates.");
        String reply = getLocation(args[0]);
        System.out.println(reply);
    }

    static String getLocation(String city) {
        DatagramSocket aSocket = null;
        DatagramPacket request = null;
        String replySentence =null;
        try {
            
            byte[] sendData = new byte[1000];
            byte[] receiveData = new byte[1000];
            
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 6789;
            //System.out.println(city);
            aSocket = new DatagramSocket();
            sendData = city.getBytes();
            
            request = new DatagramPacket(sendData, sendData.length, aHost, serverPort);
            request.setLength(sendData.length);
            aSocket.send(request);
            
            DatagramPacket reply = new DatagramPacket(receiveData, receiveData.length);
            reply.setLength(receiveData.length);
            aSocket.setSoTimeout(1000);
            boolean continueSending = true;
            while (continueSending){
                try{
                    aSocket.receive(reply);
                    continueSending = false;
                } catch(SocketTimeoutException e){
                    aSocket.send(request);
                    continue;
                }
            }
            
            replySentence = new String(reply.getData(), reply.getOffset(), reply.getLength());
            
        } catch (UnknownHostException ex) {
            System.out.println("UnknowHost: " + ex.getMessage());
        } catch (SocketException ex) {
            System.out.println("Socket: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IO: " + ex.getMessage());
        }finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
        aSocket.close();
        return replySentence;
    }
}
