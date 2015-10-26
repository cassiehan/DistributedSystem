
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hanxi
 */
public class UDPServerThatIgnoresYou {

    public static void main(String[] args) {

        Map<String, String> cityLocationPairs = new HashMap();
        cityLocationPairs.put("Pittsburgh,PA", "40.44,-79.99");
        cityLocationPairs.put("Atlantic,NJ", "39.45,74.57");
        cityLocationPairs.put("Bloomington,IL", "40.48,88.93");
        cityLocationPairs.put("Charlottesvi,VA", "38.13,78.45");
        cityLocationPairs.put("Seattle,WA", "47.45,122.30");

        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(6789);
            // create socket at agreed port
            byte[] receiveData = new byte[1000];
            byte[] sendData = new byte[1000];
            Random rnd = new Random();
            while (true) {
                DatagramPacket request = new DatagramPacket(receiveData, receiveData.length);
                request.setLength(receiveData.length);
                aSocket.receive(request);
                String cityName = new String(request.getData(), request.getOffset(), request.getLength());
                
                if (rnd.nextInt(10) < 9) {
                    System.out.println("Got request " + cityName + " but ignoring it.");
                } else {
                    System.out.println("Got request " + cityName);
                    System.out.println("And making a reply");

                    if (!cityLocationPairs.containsKey(cityName)) {
                        String replySentence = "Could not resolve '" + cityName + "'";
                        System.out.println("Was unable to handle a request for " + cityName);
                        sendData = replySentence.getBytes();
                    } else {
                        String replySentence = cityName + "\n" + cityLocationPairs.get(cityName);
                        System.out.println("Handling request for " + cityName + " " + cityLocationPairs.get(cityName));
                        sendData = replySentence.getBytes();
                    }
                    DatagramPacket reply = new DatagramPacket(sendData, sendData.length,
                            request.getAddress(), request.getPort());
                    aSocket.send(reply);
                    //aSocket.close();
                }
            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null) {
                aSocket.close();
            }
        }
    }
}
